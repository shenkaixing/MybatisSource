package com.erayt.mybatis.sqlsource;

import com.erayt.mybatis.sqlnode.DynamicContext;
import com.erayt.mybatis.sqlnode.iface.SqlNode;
import com.erayt.mybatis.sqlsource.iface.SqlSource;

/**
 * @Description: 解析过程中产生的SqlNode解析信息包含 ${}
 * @Author: Mr.Shen
 * @CreateDate: 2020/8/31 22:08
 * @Version: 1.0
 */
public class DynamicSqlSource implements SqlSource {

    private SqlNode sqlNode;

    public DynamicSqlSource(SqlNode sqlNode) {
        this.sqlNode = sqlNode;
    }

    /*
      * @Description:    只是封装了解析过程中产生的SqlNode解析信息,最终产生静态sql
      * @Author:         Mr.Shen
      * @CreateDate:     2020/8/31 23:00
      * @param:          params
      * @Version:        v1.0
      */
    @Override
    public BoundSql getBoundSql(Object params) {
        // 参数封装到context,该参数中包含${}
        DynamicContext dynamicContext = new DynamicContext(params);
        sqlNode.apply(dynamicContext);
        // 该sql包含#{}
        String sql = dynamicContext.getSql();
        SqlSourceParser sqlParser = new SqlSourceParser();
        // 解析为jdbc可以直接处理的sql
        SqlSource staticSql = sqlParser.parse(sql);
        // parse之后的sqlSource为StaticSqlSource
        return staticSql.getBoundSql(params);
    }
}