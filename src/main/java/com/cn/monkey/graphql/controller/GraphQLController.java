package com.cn.monkey.graphql.controller;

import com.cn.monkey.graphql.dto.GraphqlRequest;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GraphQLController {

    private final GraphQL graphQL;

    public GraphQLController(GraphQL graphQL) {
        this.graphQL = graphQL;
    }

    @RequestMapping("/graphql")
    public Object graphql(@RequestBody GraphqlRequest request) {
        ExecutionInput.Builder builder = ExecutionInput.newExecutionInput()
                .query(request.getQuery())
                .operationName(request.getOperationName());
        if(!CollectionUtils.isEmpty(request.getVariables())){
            builder.variables(request.getVariables());
        }
        ExecutionResult execute = this.graphQL.execute(builder.build());
        return execute.getData();
    }
}
