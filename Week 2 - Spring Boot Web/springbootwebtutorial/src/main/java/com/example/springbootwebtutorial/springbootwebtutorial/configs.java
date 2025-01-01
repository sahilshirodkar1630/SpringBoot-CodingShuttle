package com.example.springbootwebtutorial.springbootwebtutorial;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class configs {
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper() ;
    }
}
