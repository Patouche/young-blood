package fr.patouche.slides.dialect;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.thymeleaf.Arguments;
import org.thymeleaf.Configuration;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.attr.AbstractUnescapedTextChildModifierAttrProcessor;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressionExecutionContext;
import org.thymeleaf.standard.expression.StandardExpressions;

import java.util.Optional;

public class JsoupProcessor extends AbstractUnescapedTextChildModifierAttrProcessor {

    protected JsoupProcessor() {
        super("jsoup");
    }

    @Override
    public int getPrecedence() {
        return 1000;
    }

    @Override
    protected String getText(Arguments arguments, Element element, String attributeName) {

        final String attributeValue = element.getAttributeValue(attributeName);

        final Configuration configuration = arguments.getConfiguration();
        final IStandardExpressionParser expressionParser = StandardExpressions.getExpressionParser(configuration);

        final IStandardExpression expression = expressionParser.parseExpression(configuration, arguments, attributeValue);

        final Object result = expression.execute(configuration, arguments, StandardExpressionExecutionContext.UNESCAPED_EXPRESSION);

        final String out = Optional.ofNullable(result).orElse("").toString();

        return Jsoup.clean(out, "/" , Whitelist.relaxed().preserveRelativeLinks(true));
    }
}
