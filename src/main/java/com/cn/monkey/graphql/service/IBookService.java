package com.cn.monkey.graphql.service;

import com.cn.monkey.graphql.entity.Book;

import java.util.List;

public interface IBookService {
    List<Book> findBookByAuthorId(String authorId);

    List<Book> findAll();
}
