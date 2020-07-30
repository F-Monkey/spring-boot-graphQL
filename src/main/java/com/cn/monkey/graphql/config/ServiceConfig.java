package com.cn.monkey.graphql.config;

import com.cn.monkey.graphql.service.AuthorService;
import com.cn.monkey.graphql.service.BookService;
import com.cn.monkey.graphql.service.IAuthorService;
import com.cn.monkey.graphql.service.IBookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class ServiceConfig {

    @Bean
    IBookService bookService(MongoTemplate mongoTemplate) {
        return new BookService(mongoTemplate);
    }

    @Bean
    IAuthorService authorService(MongoTemplate mongoTemplate) {
        return new AuthorService(mongoTemplate);
    }
}
