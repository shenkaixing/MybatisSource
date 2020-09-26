package com.erayt.mybatis.sqlsource;

import com.erayt.mybatis.sqlsource.iface.SqlSource;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 静态sqlsource,jdbc可以直接执行
 * @Author: Mr.Shen
 * @CreateDate: 2020/9/3 23:02
 * @Version: 1.0
 */
public class StaticSqlSource implements SqlSource {

    private String staticSql;

    private List<ParameterMapping> parameterMappings = new ArrayList<>();

    public StaticSqlSource(String staticSql, List<ParameterMapping> parameterMappings) {
        this.staticSql = staticSql;
        this.parameterMappings = parameterMappings;
    }

    @Override
    public BoundSql getBoundSql(Object params) {
        return new BoundSql(staticSql,parameterMappings);
    }
}