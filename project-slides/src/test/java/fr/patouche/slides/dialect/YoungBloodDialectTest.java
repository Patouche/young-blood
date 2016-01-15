package fr.patouche.slides.dialect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.testing.templateengine.engine.TestExecutor;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class YoungBloodDialectTest {

    /** The template under test. */
    private final String file;

    /** The test file. */
    private final String testFile;

    /**
     * Testing class constructor
     *
     * @param file     the template file under test
     * @param testFile the test file to describe the expected output
     */
    public YoungBloodDialectTest(final String file, final String testFile) {
        this.file = file;
        this.testFile = testFile;
    }

    @Parameterized.Parameters(name = "{1} : testing processor {0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(
                new Object[]{"jsoup", "dialect/jsoup.thtest"},
                new Object[]{"hello", "dialect/hello.thtest"}
        );
    }

    @Test
    public void test() {

        // GIVEN
        final TestExecutor executor = new TestExecutor();
        executor.setDialects(Arrays.asList(new StandardDialect(), new YoungBloodDialect()));

        // WHEN
        executor.execute("classpath:" + testFile);

        // THEN
        assertThat(executor.isAllOK()).as("'%s' : check '%s'", testFile, file).isTrue();
    }

}