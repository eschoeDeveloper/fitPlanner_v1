package com.fitplanner.core.config;

import com.fitplanner.core.filter.CORSFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public static final String ALLOWED_METHOD_NAMES = "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH";

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowCredentials(false).maxAge(3600);

    }

    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {

        FilterRegistrationBean<Filter> filterBean = new FilterRegistrationBean<>();

        try {

            CORSFilter corsFilter = new CORSFilter();

            filterBean.setFilter(corsFilter);
            filterBean.setFilter(corsFilter());
            filterBean.setOrder(1);
            filterBean.addUrlPatterns("*");

        } catch(Exception e){
            e.printStackTrace();
        }


        return filterBean;

    }

    /** Cors Filter 등록 */
    private CorsFilter corsFilter(){

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        /** CrossOrigin 허용 */
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");

        /** POST,GET,HEAD,OPTIONS Allowed */
        config.addAllowedMethod(HttpMethod.HEAD);
        config.addAllowedMethod(HttpMethod.OPTIONS);
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.GET);

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);

    }

}