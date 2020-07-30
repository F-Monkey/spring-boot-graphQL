package com.cn.monkey.graphql.service;

import com.cn.monkey.graphql.entity.Book;
import com.google.common.base.Preconditions;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class BookService implements IBookService {

    private final MongoTemplate mongoTemplate;

    public BookService(MongoTemplate mongoTemplate) {
        Preconditions.checkNotNull(mongoTemplate);
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Book> findBookByAuthorId(String authorId) {
        return this.mongoTemplate.find(new Query(Criteria.where("authorId").is(new ObjectId(authorId))), Book.class);
    }

    @Override
    public List<Book> findAll() {
        return this.mongoTemplate.findAll(Book.class);
    }
}
