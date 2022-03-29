package org.hamburger.boot.starter.config;

import org.hamburger.boot.starter.filter.HamburgerHttpFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.hamburger.boot.starter")
public class HamburgerConfiguration {

    @Bean
    public HamburgerHttpFilter hamburgerHttpFilter(){
        return new HamburgerHttpFilter();
    }
}
