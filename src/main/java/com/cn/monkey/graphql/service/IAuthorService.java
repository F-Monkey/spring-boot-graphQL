package com.cn.monkey.graphql.service;

import com.cn.monkey.graphql.entity.Author;
import org.bson.types.ObjectId;

import java.util.List;

public interface IAuthorService {
    List<Author> findAll();

    Author findById(String id);
}
