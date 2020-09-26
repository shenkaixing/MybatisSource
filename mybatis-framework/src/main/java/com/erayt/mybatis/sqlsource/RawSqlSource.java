package com.erayt.mybatis.sqlsource;

import com.erayt.mybatis.sqlnode.DynamicContext;
import com.erayt.mybatis.sqlnode.iface.SqlNode;
import com.erayt.mybatis.sqlsource.iface.SqlSource;

/**
 * @Description: 解析过程中产生的SqlNode解析信息不包含 ${}
 * @Author: Mr.Shen
 * @CreateDate: 2020/8/31 23:02
 * @Version: 1.0
 */
public class RawSqlSource implements SqlSource {

    private SqlSource sqlSource;

    public RawSqlSource(SqlNode sqlNode) {
        DynamicContext context = new DynamicContext(null);
        sqlNode.apply(context);
        // RawSql中可能包含#{},也有可能是静态sql
        String sql = context.getSql();
        SqlSourceParser parser = new SqlSourceParser();
        // 处理为静态sql
        sqlSource = parser.parse(sql);
    }

    @Override
    public BoundSql getBoundSql(Object params) {
        return this.sqlSource.getBoundSql(params);
    }
}