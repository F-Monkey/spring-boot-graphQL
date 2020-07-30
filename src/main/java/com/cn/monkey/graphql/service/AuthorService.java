package com.cn.monkey.graphql.service;

import com.cn.monkey.graphql.entity.Author;
import com.google.common.base.Preconditions;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class AuthorService implements IAuthorService {
    private final MongoTemplate mongoTemplate;

    public AuthorService(MongoTemplate mongoTemplate) {
        Preconditions.checkNotNull(mongoTemplate);
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Author> findAll() {
        return this.mongoTemplate.findAll(Author.class);
    }

    @Override
    public Author findById(String id) {
        return this.mongoTemplate.findOne(new Query(Criteria.where("_id").is(new ObjectId(id))), Author.class);
    }
}
