package fr.patouche.slides;

import com.google.common.io.CharStreams;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Presentation service to load slides.
 *
 * @author patrick allain - 14/12/15.
 */
@Service
public class PresentationService {

    /** The slides path property. */
    public static final String SLIDES_PATH = "slides.path";

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PresentationService.class);

    /** The slides path. */
    private final Path path;

    /**
     * Class constructor.
     */
    public PresentationService() {
        final String slidesPath = System.getProperty(SLIDES_PATH, "");
        if (slidesPath.trim().isEmpty()) {
            throw new RuntimeException("The vm property 'slides.path' is not properly defined");
        }
        final Path path = Paths.get(slidesPath);
        if (!path.toFile().exists() && !path.toFile().isDirectory()) {
            throw new RuntimeException("The folder '" + path + "' doesn't exist or is not a directory");
        }
        this.path = path;
    }

    /**
     * Load the slides section.
     *
     * @return a list of slides section
     */
    public List<SlideSection> loadSlides() {
        try {
            final AtomicInteger sectionIndex = new AtomicInteger(0);
            return Files.walk(this.path)
                    .filter(Files::isRegularFile)
                    .filter(Files::isReadable)
                    .map(Path::toString)
                    .filter(Pattern.compile("(md|markdown)$").asPredicate())
                    .peek(r -> LOGGER.debug("Find resources : {}", r))
                    .sorted()
                    .map(this::loadSlide)
                    .peek(s -> s.setIndex(sectionIndex.getAndIncrement()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error during loading the slides.", e);
        }
    }

    /**
     * Load a slide section by its path.
     *
     * @param path the path of the slide section
     * @return the slide section
     */
    public SlideSection loadSlide(final String path) {
        try (final InputStream is = new FileInputStream(Paths.get(path).toFile())) {
            final String content = CharStreams.toString(new InputStreamReader(is, StandardCharsets.UTF_8));
            final AtomicInteger inc = new AtomicInteger(1);
            final List<SlidePage> pages = Stream.of(content.split("____*"))
                    .map((c) -> new SlidePage(inc.getAndIncrement(), markdown(c)))
                    .collect(Collectors.toList());
            LOGGER.debug("Page '{}' => Slides : {}", path, pages);
            return new SlideSection(path, markdown(content), pages);
        } catch (IOException e) {
            throw new RuntimeException("Error during loading file at path " + path, e);
        }
    }

    /**
     * Get a slide section by its index in all the loaded slides section list.
     *
     * @param index the index of the slide to load
     * @return the slide section
     */
    public SlideSection getSlide(final Integer index) {
        final int slideIndex = Optional.ofNullable(index).orElse(0);
        return loadSlides().stream()
                .filter(s -> s.getIndex() == slideIndex)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Slide at index '" + index + "' cannot be found"));
    }

    /**
     * Format into markdown.
     *
     * @param content the markdown content
     * @return a html content
     */
    public String markdown(final String content) {
        final int options = Extensions.HARDWRAPS
                + Extensions.AUTOLINKS
                + Extensions.ATXHEADERSPACE
                + Extensions.STRIKETHROUGH
                + Extensions.FENCED_CODE_BLOCKS
                + Extensions.RELAXEDHRULES;
        return new PegDownProcessor(options).markdownToHtml(content);
    }

}
