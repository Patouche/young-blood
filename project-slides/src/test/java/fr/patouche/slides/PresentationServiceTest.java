package fr.patouche.slides;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class PresentationServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PresentationServiceTest.class);

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @After
    public void tearDown() {
        System.setProperty(PresentationService.SLIDES_PATH, "");
    }

    private Path buildDir(final String... files) {
        try {
            final Path folder = temporaryFolder.getRoot().toPath().toAbsolutePath();
            for (String f : files) {
                try (final InputStream is = ClassLoader.getSystemResourceAsStream(f)) {
                    LOGGER.debug("File {} found => {}", f, is != null);
                    final Path name = Paths.get(f).getFileName();
                    if (is != null) {
                        Files.copy(is, folder.resolve(name), StandardCopyOption.REPLACE_EXISTING);
                    }
                }
            }
            return folder;
        } catch (IOException e) {
            throw new RuntimeException("Error during test with message : " + e.getMessage(), e);
        }
    }

    @Test
    public void initMethod_whenVmPropertyIsNotDefined() throws Exception {
        // GIVEN
        final Path path = temporaryFolder.getRoot().toPath().toAbsolutePath();
        System.setProperty(PresentationService.SLIDES_PATH, "  ");

        // WHEN
        final Throwable throwable = Assertions.catchThrowable(PresentationService::new);

        // THEN
        assertThat(throwable).as("error throw")
                .isInstanceOf(RuntimeException.class)
                .hasNoCause()
                .hasMessage("The vm property 'slides.path' is not properly defined");
    }

    @Test
    public void initMethod_whenPathDoesntExist() throws Exception {
        // GIVEN
        final Path path = temporaryFolder.getRoot().toPath().toAbsolutePath();
        final String badPath = path.resolve(UUID.randomUUID().toString()).toString();
        System.setProperty(PresentationService.SLIDES_PATH, badPath);

        // WHEN
        final Throwable throwable = Assertions.catchThrowable(PresentationService::new);

        // THEN
        assertThat(throwable).as("error throw")
                .isInstanceOf(RuntimeException.class)
                .hasNoCause()
                .hasMessage("The folder '" + badPath + "' doesn't exist or is not a directory");
    }

    @Test
    public void loadSlides() throws Exception {
        // GIVEN
        final Path path = this.buildDir("slides/sample-1.md", "slides/sample-2.md");
        System.setProperty(PresentationService.SLIDES_PATH, path.toString());

        final PresentationService service = new PresentationService();

        // WHEN
        final List<SlideSection> slideSections = service.loadSlides();

        // THEN
        assertThat(slideSections).as("loaded slides")
                .hasSize(2)
                .extracting("fileName").contains("sample-1.md", "sample-2.md");
    }

    @Test
    public void loadSlide_nominal() throws Exception {
        // GIVEN
        final Path path = this.buildDir("slides/sample-1.md", "slides/sample-2.md");
        System.setProperty(PresentationService.SLIDES_PATH, path.toString());
        final Path filePath = path.resolve("sample-2.md");

        final PresentationService service = new PresentationService();

        // WHEN
        final SlideSection slideSection = service.loadSlide(filePath.toString());

        // THEN
        assertThat(slideSection).as("slide section loaded").isNotNull();
        assertThat(slideSection.getIndex()).as("slide section index").isEqualTo(0);
        assertThat(slideSection.getFileName()).as("slide section filename").isEqualTo("sample-2.md");
        assertThat(slideSection.getFile()).as("slide section file").isEqualTo(filePath.toString());
        assertThat(slideSection.getContent()).as("slide section content")
                .isEqualTo("<h1>Title</h1>\n<h2>Subtitle 1</h2>\n<hr/>\n<h2>Subtitle 2</h2>");
        assertThat(slideSection.getPages()).as("slide section pages").hasSize(2);
        assertThat(slideSection.getPages()).as("slide section pages").extracting("index", Integer.class)
                .containsExactly(1, 2);
        assertThat(slideSection.getPages()).as("slide section pages").extracting("content", String.class)
                .containsExactly("<h1>Title</h1>\n<h2>Subtitle 1</h2>", "<h2>Subtitle 2</h2>");
    }

    @Test
    public void getSlide_nominal() throws Exception {
        // GIVEN
        final Path path = this.buildDir("slides/sample-1.md", "slides/sample-2.md");
        System.setProperty(PresentationService.SLIDES_PATH, path.toString());

        final PresentationService service = new PresentationService();

        // WHEN
        final SlideSection slideSection = service.getSlide(1);

        // THEN
        assertThat(slideSection).as("loaded slides").isNotNull();
        assertThat(slideSection.getIndex()).as("slide section index").isEqualTo(1);
        assertThat(slideSection.getFileName()).as("slide section index").isEqualTo("sample-2.md");
    }

    @Test
    public void getSlide_whenNotExist() throws Exception {
        // GIVEN
        final Path path = this.buildDir("slides/sample-1.md", "slides/sample-2.md");
        System.setProperty(PresentationService.SLIDES_PATH, path.toString());

        final PresentationService service = new PresentationService();

        // WHEN
        final Throwable throwable = Assertions.catchThrowable(() -> service.getSlide(42));

        // THEN
        assertThat(throwable)
                .isInstanceOf(RuntimeException.class)
                .hasNoCause()
                .hasMessage("Slide at index '42' cannot be found");
    }

    @Test
    public void markdown() throws Exception {
        // GIVEN
        final Path folder = temporaryFolder.getRoot().toPath();
        System.setProperty(PresentationService.SLIDES_PATH, folder.toString());

        // WHEN
        final String html = new PresentationService().markdown("# Title\n## Subtitle\n");

        // THEN
        assertThat(html).isEqualTo("<h1>Title</h1>\n<h2>Subtitle</h2>");
    }

}