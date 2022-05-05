package org.hamburger.boot.api.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.util.Collections;
import java.util.Map;

public abstract class AbstractRetrofitClientFactoryBean implements FactoryBean<Object>, InitializingBean, ApplicationContextAware {

    protected Class<?> type;

    protected String name;

    protected String url;

    protected ApplicationContext applicationContext;

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    protected Retrofit.Builder retrofit(RetrofitContext context, boolean hasUrl) {
        Retrofit.Builder builder = get(context, Retrofit.Builder.class);

        Map<String, Converter.Factory> converterFactories = getInstances(context, Converter.Factory.class);
        converterFactories.values().forEach(builder::addConverterFactory);

        Map<String, CallAdapter.Factory> callAdapterFactories = getInstances(context, CallAdapter.Factory.class);
        callAdapterFactories.values().forEach(builder::addCallAdapterFactory);

        builder.validateEagerly(true); // validate retrofit annotation

        return builder;

    }

    protected <T> Map<String, T> getInstances(RetrofitContext context, Class<T> type) {
        Map<String, T> instances = context.getInstances(this.name, type);
        if (instances == null) {
            return Collections.emptyMap();
        }
        return instances;
    }

    protected <T> T getOptional(RetrofitContext context, Class<T> type) {
        return context.getInstance(this.name, type);
    }

    protected abstract Object loadBalance(Retrofit.Builder builder, RetrofitContext context, String serviceIdUrl);

    protected <T> T get(RetrofitContext context, Class<T> type) {
        T instance = context.getInstance(this.name, type);
        if (instance == null) {
            throw new IllegalStateException("No bean found of type " + type + " for " + this.name);
        }
        return instance;
    }

    @Override
    public Object getObject() throws Exception {
        RetrofitContext context = applicationContext.getBean(RetrofitContext.class);

        boolean hasUrl = StringUtils.hasText(this.url);
        Retrofit.Builder builder = retrofit(context, hasUrl);

        if (!hasUrl) {
            String serviceIdUrl;
            if (!this.name.startsWith("http")) {
                serviceIdUrl = "http://" + this.name;
            } else {
                serviceIdUrl = this.name;
            }
            builder.baseUrl(serviceIdUrl);

            return loadBalance(builder, context, serviceIdUrl);
        }

        if (hasUrl && !this.url.startsWith("http")) {
            this.url = "http://" + this.url;
        }

        builder.baseUrl(this.url);

        Retrofit retrofit = buildAndSave(context, builder);
        return retrofit.create(this.type);
    }

    protected Retrofit buildAndSave(RetrofitContext context, Retrofit.Builder builder) {
        Retrofit retrofit = builder.build();

        AnnotationConfigApplicationContext applicationContext = context.getContext(this.name);
        applicationContext.registerBean(Retrofit.class, () -> retrofit);
        return retrofit;
    }

    @Override
    public Class<?> getObjectType() {
        return this.type;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(this.name, "Name must be set");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
    }
}
