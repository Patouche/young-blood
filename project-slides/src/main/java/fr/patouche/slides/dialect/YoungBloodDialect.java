package fr.patouche.slides.dialect;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;

import java.util.HashSet;
import java.util.Set;

public class YoungBloodDialect extends AbstractDialect {

    public YoungBloodDialect() {
        super();
    }

    public String getPrefix() {
        return "yb";
    }

    @Override
    public Set<IProcessor> getProcessors() {
        final Set<IProcessor> processors = new HashSet<IProcessor>();
        processors.add(new JsoupProcessor());
        processors.add(new SayHelloProcessor());
        return processors;
    }

}