package org.hamburger.boot.api.core;

import org.springframework.cloud.context.named.NamedContextFactory;

public class RetrofitClientSpecification implements NamedContextFactory.Specification {

    private String name;

    private Class<?>[] configuration;

    public RetrofitClientSpecification(String name, Class<?>[] configuration) {
        this.name = name;
        this.configuration = configuration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConfiguration(Class<?>[] configuration) {
        this.configuration = configuration;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Class<?>[] getConfiguration() {
        return configuration;
    }
}
