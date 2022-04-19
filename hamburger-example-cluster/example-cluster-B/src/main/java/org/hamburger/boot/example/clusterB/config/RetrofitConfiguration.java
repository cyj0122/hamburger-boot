package org.hamburger.boot.example.clusterB.config;

import org.hamburger.boot.example.clusterA.api.UserApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfiguration {

    @Bean
    public UserApi userApi(final Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }

    @Bean
    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://localhost:8081")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
