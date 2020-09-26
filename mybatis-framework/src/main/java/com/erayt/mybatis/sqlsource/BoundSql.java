package com.erayt.mybatis.sqlsource;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: BoundSql用于描述包含#{}的Jdbc语句，及包含list参数数据
 * @Author: Mr.Shen
 * @CreateDate: 2020/8/31 22:30
 * @Version: 1.0
 */
public class BoundSql {

    private String sql;

    private List<ParameterMapping> parameterMappings = new ArrayList<>();

    public BoundSql(String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }
}