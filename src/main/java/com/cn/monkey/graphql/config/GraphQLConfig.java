package com.cn.monkey.graphql.config;

import com.cn.monkey.graphql.entity.Book;
import com.cn.monkey.graphql.service.IAuthorService;
import com.cn.monkey.graphql.service.IBookService;
import com.cn.monkey.graphql.vo.BookResp;
import com.google.common.base.Preconditions;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Configuration
public class GraphQLConfig {

    private final IAuthorService authorService;

    private final IBookService bookService;

    public GraphQLConfig(IAuthorService authorService,
                         IBookService bookService) {
        Preconditions.checkNotNull(authorService);
        Preconditions.checkNotNull(bookService);
        this.authorService = authorService;
        this.bookService = bookService;
    }

    private RuntimeWiring runtimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", builder -> builder.dataFetcher("hello", new StaticDataFetcher("Query hello word")))
                .type("Mutation", builder -> builder.dataFetcher("hello", new StaticDataFetcher("Mutation hello word")))
                .type(newTypeWiring("Query").dataFetcher("author", environment -> this.authorService.findAll()))
                .type(newTypeWiring("Query").dataFetcher("authorById", environment -> this.authorService.findById(environment.getArgument("id").toString())))
                .type(newTypeWiring("Query").dataFetcher("book", environment -> {
                    List<Book> all = this.bookService.findAll();
                    List<BookResp> bookResps = new ArrayList<>(all.size());
                    boolean needFindAuthor = environment.getSelectionSet().contains("author");
                    for (Book book : all) {
                        BookResp bookResp = new BookResp();
                        bookResp.setId(book.getId().toString());
                        bookResp.setName(book.getName());
                        if (needFindAuthor) {
                            bookResp.setAuthor(this.authorService.findById(book.getAuthorId().toString()));
                        }
                        bookResps.add(bookResp);
                    }
                    return bookResps;
                }))
                .build();
    }

    private GraphQLSchema graphQLSchema(File sqlFile) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sqlFile);
        return new SchemaGenerator().makeExecutableSchema(typeRegistry, runtimeWiring());
    }

    @Bean
    GraphQL graphQL() throws FileNotFoundException {
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "schema.graphqls");
        GraphQLSchema graphQLSchema = this.graphQLSchema(file);
        return GraphQL.newGraphQL(graphQLSchema).build();
    }
}
