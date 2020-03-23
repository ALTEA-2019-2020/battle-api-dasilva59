package com.miage.altea.battle_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class RestConfiguration {

    @Value("${trainer.service.password}")
    String password;

    @Value("${trainer.service.username}")
    String userName;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    @Autowired
    public void configureLocalInterceptor (){
        RestTemplate r = this.restTemplate();
        r.setInterceptors(List.of((httpRequest, bytes, clientHttpRequestExecution) -> {
            httpRequest.getHeaders().add(HttpHeaders.ACCEPT_LANGUAGE, LocaleContextHolder.getLocale().toLanguageTag());
            return clientHttpRequestExecution.execute(httpRequest,bytes);
        }));
    }

    @Bean
    RestTemplate trainerApiRestTemplate() {
        RestTemplate r = this.restTemplate();
        r.setInterceptors(List.of(new BasicAuthenticationInterceptor(userName,password)));
        return r;
    }

}