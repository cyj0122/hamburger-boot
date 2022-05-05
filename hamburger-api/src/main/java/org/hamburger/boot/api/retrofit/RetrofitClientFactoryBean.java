package org.hamburger.boot.api.retrofit;

import okhttp3.OkHttpClient;
import org.hamburger.boot.api.core.AbstractRetrofitClientFactoryBean;
import org.hamburger.boot.api.core.RetrofitContext;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import retrofit2.Retrofit;

import java.util.Map;

public class RetrofitClientFactoryBean extends AbstractRetrofitClientFactoryBean {

    @Override
    protected Retrofit.Builder retrofit(RetrofitContext context, boolean hasUrl) {
        Retrofit.Builder builder = super.retrofit(context, hasUrl);

        OkHttpClient.Builder clientBuilder = getOptional(context, OkHttpClient.Builder.class);
        if (clientBuilder != null) {
            builder.client(clientBuilder.build());
        }
        return builder;
    }



    @Override
    protected Object loadBalance(Retrofit.Builder builder, RetrofitContext context, String serviceIdUrl) {
        Map<String, OkHttpClient.Builder> instances = context.getInstances(this.name, OkHttpClient.Builder.class);
        for (Map.Entry<String, OkHttpClient.Builder> entry: instances.entrySet()) {
            String beanName = entry.getKey();
            OkHttpClient.Builder clientBuilder = entry.getValue();

            if (applicationContext.findAnnotationOnBean(beanName, LoadBalanced.class) != null) {
                builder.client(clientBuilder.build());
                Retrofit retrofit = buildAndSave(context, builder);
                return retrofit.create(this.type);
            }
        }

        throw new IllegalStateException(
                "No Retrofit Client for loadBalancing defined. Did you forget to include spring-cloud-starter-square-okhttp?");
    }
}
