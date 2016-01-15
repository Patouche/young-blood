package fr.patouche.slides;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The presentation controller.
 *
 * @author : Patrick Allain
 */
@Controller
@RequestMapping("/")
public class PresentationController {

    /** The presentation service. */
    @Inject
    private PresentationService presentationService;

    /**
     * Show the presentation.
     *
     * @return the model and view.
     */
    @RequestMapping(value = "/slides", method = RequestMethod.GET)
    public ModelAndView slides() {
        final Map<String, Object> model = new HashMap<>();
        model.put("sections", this.presentationService.loadSlides());
        return new ModelAndView("reveal-th", model);
    }

    /**
     * Show all slides.
     *
     * @return the model and view.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView demo() {
        final Map<String, Object> model = new HashMap<>();
        model.put("user", "ParisJUG");
        model.put("slides", this.presentationService.loadSlides());
        model.put("now", LocalDateTime.now());
        return new ModelAndView("demo-th", model);
    }

    /**
     * Show one slide.
     *
     * @return the model and view.
     */
    @RequestMapping(value = "/file/{index:[0-9]+}", method = RequestMethod.GET)
    public ModelAndView demoDetail(final @PathVariable("index") Integer index) {
        final Map<String, Object> model = new HashMap<>();
        model.put("user", "ParisJUG");
        model.put("slide", this.presentationService.getSlide(index));
        return new ModelAndView("demo-detail-th", model);
    }

}
