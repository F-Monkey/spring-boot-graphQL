package com.cn.monkey.graphql.config;

import com.cn.monkey.graphql.entity.Author;
import com.cn.monkey.graphql.entity.Book;
import com.google.common.base.Preconditions;
import org.bson.types.ObjectId;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataInitialize implements ApplicationRunner {

    private final MongoTemplate mongoTemplate;

    public DataInitialize(MongoTemplate mongoTemplate) {
        Preconditions.checkNotNull(mongoTemplate);
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
//        initUser();
        //initBook();
    }

    private void initUser() {
        Author user = new Author();
        user.setName("tom");
        this.mongoTemplate.save(user);
    }

    private void initBook() {
        Book book = new Book();
        book.setAuthorId(new ObjectId("5f22cc7de17e0708b59bec98"));
        book.setName("hhhhh");
        this.mongoTemplate.save(book);
    }
}
