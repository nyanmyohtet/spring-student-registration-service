package com.nyanmyohtet.studentregistrationservice.config;

import com.nyanmyohtet.studentregistrationservice.persistence.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public BookDao bookDao() {
        return new BookDao();
    }
}
