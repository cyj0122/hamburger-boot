package org.hamburger.boot.api.core;

import org.springframework.cloud.context.named.NamedContextFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RetrofitContext extends NamedContextFactory<RetrofitClientSpecification> {

    public RetrofitContext(Class<?> defaultConfigType) {
        super(defaultConfigType, "retrofit", "retrofit.client.name");
    }

    @Override
    protected AnnotationConfigApplicationContext getContext(String name) {
        return super.getContext(name);
    }
}
