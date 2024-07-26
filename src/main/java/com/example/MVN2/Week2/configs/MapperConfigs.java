package com.example.MVN2.Week2.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfigs {
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}