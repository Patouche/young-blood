package fr.patouche.slides.dialect;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.processor.attr.AbstractTextChildModifierAttrProcessor;

/**
 * Say hello processor.
 *
 * @author patrick allain
 */
public class SayHelloProcessor extends AbstractTextChildModifierAttrProcessor implements IProcessor {

    protected SayHelloProcessor() {
        super("hello");
    }

    @Override
    protected String getText(Arguments arguments, Element element, String s) {
        return "Hello " + element.getAttributeValue(s) + " !";
    }

    @Override
    public int getPrecedence() {
        return 1000;
    }
}
