package ru.jpol.vocabot;


import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.DatabaseConnection;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.jpol.vocabot.entity.User;
import ru.jpol.vocabot.service.restImpl.UserServiceImpl;
import ru.jpol.vocabot.service.restImpl.WordServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = VocaBotApplicationTest.DockerPostgreDataSourceInitializer.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource("classpath:liquibase.properties")
@Testcontainers
public abstract class VocaBotApplicationTest implements Constants {
    private static String changeLogFile;
    private static String liquibaseContext;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserServiceImpl userService;

    @Autowired
    public WordServiceImpl wordService;

    @Container
    public static PostgreSQLContainer<?> postgreDBContainer = new PostgreSQLContainer<>("postgres:9.4");

    static {
        postgreDBContainer.start();
    }

    public static class DockerPostgreDataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            changeLogFile = applicationContext.getEnvironment().getProperty("liquibase.change-log");
            liquibaseContext = applicationContext.getEnvironment().getProperty("liquibase.context");

            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    applicationContext,
                    "spring.datasource.url=" + postgreDBContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreDBContainer.getUsername(),
                    "spring.datasource.password=" + postgreDBContainer.getPassword()
            );
        }
    }

    @BeforeAll
    public void init() throws SQLException, LiquibaseException {
        Connection connection = DriverManager.getConnection(postgreDBContainer.getJdbcUrl(), postgreDBContainer.getUsername(), postgreDBContainer.getPassword());
        DatabaseConnection dbConnection = new JdbcConnection(connection);

        Liquibase liquibase = new Liquibase(changeLogFile, new ClassLoaderResourceAccessor(), dbConnection);
        liquibase.update(new Contexts(liquibaseContext));
        liquibase.close();
    }

    protected void cleanUp(String... tableName) {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, tableName);
    }

    /**
     * Create and save to db 5 users
     */
    protected void config_01() {
        User user;
        for (long i = 0; i < defaultListSize; i++) {
            user = new User();

            user.setId(i);
            user.setEmail("test" + i + "@test.com");
            user.setFirstname("firstname" + i);
            user.setUsername("username" + i);

            userService.createUser(user);
        }
    }

//    /**
//     * Create and save to db 5 words with the same chatID
//     */
//    protected void config_02() {
//        for (long i = 0; i < defaultListSize; i++) {
//            wordService.createWord(new Word(defaultChatID, "word" + i, "translation" + i));
//        }
//    }
}

