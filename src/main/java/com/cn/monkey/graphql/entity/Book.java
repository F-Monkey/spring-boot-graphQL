package com.cn.monkey.graphql.entity;

import org.bson.types.ObjectId;

import java.io.Serializable;

public class Book implements Serializable {
    private ObjectId id;
    private String name;
    private ObjectId authorId;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObjectId getAuthorId() {
        return authorId;
    }

    public void setAuthorId(ObjectId authorId) {
        this.authorId = authorId;
    }
}
