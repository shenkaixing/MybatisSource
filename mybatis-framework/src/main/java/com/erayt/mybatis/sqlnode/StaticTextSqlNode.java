package com.erayt.mybatis.sqlnode;

import com.erayt.mybatis.sqlnode.iface.SqlNode;

/**
 * @Description: 可执行sql
 * @Author: Mr.Shen
 * @CreateDate: 2020/8/31 23:17
 * @Version: 1.0
 */
public class StaticTextSqlNode implements SqlNode {

    private String sql;

    public StaticTextSqlNode(String sql) {
        this.sql = sql;
    }

    @Override
    public void apply(DynamicContext dynamicContext) {
        dynamicContext.appenSql(this.sql);
    }
}