package com.aimprosoft.kmb.database;

public class QueryBuilderSql {

    private StringBuilder query = new StringBuilder();

    private String sqlParams;

    public String getSqlParams() {
        return sqlParams;
    }

    public void setSqlParams(String sqlParams) {
        this.sqlParams = sqlParams;
    }

    public QueryBuilderSql insert() {
        query.append("INSERT INTO ");
        return this;
    }

    public QueryBuilderSql update() {
        query.append("UPDATE");
        return this;
    }

    public QueryBuilderSql params() {
        getSqlParams();
        return this;
    }
}

