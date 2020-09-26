package com.erayt.mybatis.sqlnode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 封装了sql信息、可以封装入参信息
 * @Author: Mr.Shen
 * @CreateDate: 2020/8/31 22:43
 * @Version: 1.0
 */
public class DynamicContext {
    private StringBuilder sqlBuilder = new StringBuilder();

    private Map<String, Object> bindings = new HashMap<>();

    public DynamicContext(Object parameters) {
        bindings.put("_parameter", parameters);
    }

    public Map<String, Object> getBindings() {
        return bindings;
    }

    public String getSql() {
        return  this.sqlBuilder.toString();
    }

    public void appenSql(String sql) {
        this.sqlBuilder.append(sql).append(" ");
    }
}