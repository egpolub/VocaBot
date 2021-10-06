package ru.jpol.vocabot;


import org.flywaydb.core.Flyway;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.jpol.vocabot.dao.DaoImpl.UserDaoImpl;
import ru.jpol.vocabot.dao.DaoImpl.WordDaoImpl;
import ru.jpol.vocabot.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = VocaBotApplicationTest.DockerPostgreDataSourceInitializer.class)
@Testcontainers
public abstract class VocaBotApplicationTest implements Constants {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Flyway flyway;

    @Autowired
    public UserDaoImpl userDao;

    @Autowired
    public WordDaoImpl wordDao;

    @ClassRule
    public static PostgreSQLContainer<?> postgreDBContainer = new PostgreSQLContainer<>("postgres:11-alpine");

    static {
//        postgreDBContainer.withInitScript("src/test/resources/db/migration/V1.1.0__init_postgres.sql");
        postgreDBContainer.start();
    }

    public static class DockerPostgreDataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreDBContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreDBContainer.getUsername(),
                    "spring.datasource.password=" + postgreDBContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @BeforeEach
    public void init() {
        System.out.println("You know that i am here");
        System.out.println(Flyway.configure().getInitSql());
        flyway.clean();
        flyway.migrate();
    }

//    protected void cleanUp(String... tableName) {
//        JdbcTestUtils.deleteFromTables(jdbcTemplate, tableName);
//    }

    /**
     * Create and save to db 5 users
     */
    protected void config_01() {
        User user;
        for (long i = 0; i < defaultListSize; i++) {
            user = new User();

            user.setUserId(i);
            user.setEmail("test" + i + "@test.com");
            user.setFirstname("firstname" + i);
            user.setUsername("username" + i);

            userDao.createUser(user);
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

