package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BeanBag {
    @Bean
    public List<Tod> getDataSource(){
        return new ArrayList<>();
    }
}
