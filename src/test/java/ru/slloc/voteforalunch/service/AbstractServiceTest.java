package ru.slloc.voteforalunch.service;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.slloc.voteforalunch.TimingExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.slloc.voteforalunch.util.ValidationUtil.getRootCause;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})

@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ExtendWith(TimingExtension.class)
abstract public class AbstractServiceTest {
//    @ClassRule
//    public static ExternalResource summary = TimingRules.SUMMARY;
//
//    @Rule
//    public Stopwatch stopwatch = TimingRules.STOPWATCH;
//
//    @Autowired
//    public Environment env;
//
//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//
//    static {
//        // needed only for java.util.logging (postgres driver)
//        SLF4JBridgeHandler.install();
//    }
//
//    public boolean isJpaBased() {
////        return Arrays.stream(env.getActiveProfiles()).noneMatch(Profiles.JDBC::equals);
//        return env.acceptsProfiles(Profiles.JPA, Profiles.DATAJPA);
//    }
//
//  Check root cause in JUnit: https://github.com/junit-team/junit4/pull/778
<T extends Throwable> void validateRootCause(Runnable runnable, Class<T> exceptionClass) {
    assertThrows(exceptionClass, () -> {
        try {
            runnable.run();
        } catch (Exception e) {
            throw getRootCause(e);
        }
    });
}
}