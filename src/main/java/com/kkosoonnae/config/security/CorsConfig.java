package com.kkosoonnae.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;

/**
 * packageName    : com.kurly.api.config.security
 * fileName       : CorsConfig
 * author         : hagjoon
 * date           : 2024-04-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-24        hagjoon       최초 생성
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://kkosoonnae.vercel.app","https://kkosoonnae.shop:8080"));
        config.setAllowCredentials(true);

        source.registerCorsConfiguration("/**",config);
        return new CorsFilter(source);
    }
//    @Override
//    public void addCorsMappings(CorsRegistry registry){
//        registry.addMapping("/KkoSoonNae/**")
//                .allowedOrigins("http://localhost","https://kkosoonnae.vercel.app")
//                .allowedMethods("*")
//                .exposedHeaders("Location");
//    }
}
