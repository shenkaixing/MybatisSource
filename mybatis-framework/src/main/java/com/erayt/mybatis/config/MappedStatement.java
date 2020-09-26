package com.erayt.mybatis.config;

import com.erayt.mybatis.sqlsource.iface.SqlSource;

/**
 * @Description: MappedStatement代表一个CRUD标签
 * @Author: Mr.Shen
 * @CreateDate: 2020/8/30 19:52
 * @Version: 1.0
 */
public class MappedStatement {

    private String namespace;

    private Class<?> parameterType;

    private Class<?> resultType;

    private String statementType;

    private SqlSource sqlSource;

    public MappedStatement(String namespace, Class<?> parameterType, Class<?> resultType, String statementType, SqlSource sqlSource) {
        this.namespace = namespace;
        this.parameterType = parameterType;
        this.resultType = resultType;
        this.statementType = statementType;
        this.sqlSource = sqlSource;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public Class<?> getParameterType() {
        return parameterType;
    }

    public void setParameterType(Class<?> parameterType) {
        this.parameterType = parameterType;
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public void setResultType(Class<?> resultType) {
        this.resultType = resultType;
    }

    public String getStatementType() {
        return statementType;
    }

    public void setStatementType(String statementType) {
        this.statementType = statementType;
    }

    public SqlSource getSqlSource() {
        return sqlSource;
    }

    public void setSqlSource(SqlSource sqlSource) {
        this.sqlSource = sqlSource;
    }
}