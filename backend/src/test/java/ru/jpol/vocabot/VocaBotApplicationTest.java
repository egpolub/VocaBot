package ru.jpol.vocabot;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.jpol.vocabot.dao.DaoImpl.WordDaoImpl;
import ru.jpol.vocabot.dao.repository.UserRepository;
import ru.jpol.vocabot.entity.User;

import javax.sql.DataSource;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = VocaBotApplicationTest.DockerPostgreDataSourceInitializer.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource("classpath:flyway.properties")
public abstract class VocaBotApplicationTest implements Constants {
    private static String FLYWAY_LOCATIONS;
    private static String FLYWAY_SCHEMAS;

    private Flyway flyway;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public WordDaoImpl wordDao;

    @ClassRule
    public static PostgreSQLContainer<?> postgreDBContainer = new PostgreSQLContainer<>("postgres:11-alpine");

    static {
        postgreDBContainer.start();
    }

    public static class DockerPostgreDataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {



        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            FLYWAY_LOCATIONS = configurableApplicationContext.getEnvironment().getProperty("spring.flyway.locations");
            FLYWAY_SCHEMAS = configurableApplicationContext.getEnvironment().getProperty("spring.flyway.schemas");
            
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreDBContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreDBContainer.getUsername(),
                    "spring.datasource.password=" + postgreDBContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @BeforeEach
    public void init() {
        if (flyway == null) {
            flyway = Flyway.configure()
                    .dataSource(getDataSource(postgreDBContainer))
                    .schemas(FLYWAY_SCHEMAS)
                    .locations(FLYWAY_LOCATIONS).load();
        }
        flyway.clean();
        flyway.migrate();
    }

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

            userRepository.save(user);
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

    private DataSource getDataSource(JdbcDatabaseContainer<?> container) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(container.getJdbcUrl());
        hikariConfig.setUsername(container.getUsername());
        hikariConfig.setPassword(container.getPassword());
        hikariConfig.setDriverClassName(container.getDriverClassName());
        return new HikariDataSource(hikariConfig);
    }
}

