package com.hector.TFGranjas.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GranjasConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

