package com.cn.monkey.graphql.dto;

import java.io.Serializable;
import java.util.Map;

public class GraphqlRequest implements Serializable {
    private String query;
    private String operationName;
    private Map<String,Object> variables;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public Map<String,Object>  getVariables() {
        return variables;
    }

    public void setVariables(Map<String,Object>  variables) {
        this.variables = variables;
    }
}
