package fr.patouche.slides;

import fr.patouche.slides.dialect.YoungBloodDialect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.testing.templateengine.engine.TestExecutor;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class ThymeleafTemplateTest {

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
    public ThymeleafTemplateTest(final String file, final String testFile) {
        this.file = file;
        this.testFile = testFile;
    }

    @Parameterized.Parameters(name = "{1} : testing template {0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(
                new Object[]{"WEB-INF/thymeleaf/demo-th.html", "templates/demo-th.thtest"},
                new Object[]{"WEB-INF/thymeleaf/demo-detail-th.html", "templates/demo-detail-th.thtest"},
                new Object[]{"WEB-INF/thymeleaf/reveal-th.html", "templates/reveal-th.thtest"}
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
