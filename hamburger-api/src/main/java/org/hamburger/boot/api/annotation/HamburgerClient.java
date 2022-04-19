package org.hamburger.boot.api.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface HamburgerClient {

    @AliasFor("name")
    String value() default "";

    @AliasFor("value")
    String name() default "";

    String qualifier() default "";

    String url() default "";

    Class<?>[] configuration() default {};
}
