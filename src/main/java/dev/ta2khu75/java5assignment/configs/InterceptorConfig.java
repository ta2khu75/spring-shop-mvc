package dev.ta2khu75.java5assignment.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import dev.ta2khu75.java5assignment.interceptors.InterceptorSecurity;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final InterceptorSecurity interceptorSecurity;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptorSecurity)
                .addPathPatterns("/admin", "/admin/**", "/crud/**", "/cart", "/cart/**", "/checkout", "checkout/**","/profile/**","profile", "/order", "/order/**")
                .excludePathPatterns();
    }
}
