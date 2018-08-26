package ru.slloc.voteforalunch.service;


import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
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
//    //  Check root cause in JUnit: https://github.com/junit-team/junit4/pull/778
//    public <T extends Throwable> void validateRootCause(Runnable runnable, Class<T> exceptionClass) {
//        try {
//            runnable.run();
//            Assert.fail("Expected " + exceptionClass.getName());
//        } catch (Exception e) {
//            Assert.assertThat(getRootCause(e), instanceOf(exceptionClass));
//        }
//    }
}