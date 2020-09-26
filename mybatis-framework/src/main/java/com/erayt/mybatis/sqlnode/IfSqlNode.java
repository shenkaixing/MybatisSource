package com.erayt.mybatis.sqlnode;

import com.erayt.mybatis.sqlnode.iface.SqlNode;
import com.erayt.mybatis.util.OgnlUtils;

/**
 * @Description: 封装if标签的sqlnode
 * @Author: Mr.Shen
 * @CreateDate: 2020/8/31 23:30
 * @Version: 1.0
 */
public class IfSqlNode implements SqlNode {

    private String testExpress;

    private SqlNode sqlNode;

    public IfSqlNode(String testExpress, SqlNode sqlNode) {
        this.testExpress = testExpress;
        this.sqlNode = sqlNode;
    }

     /*
      * @Description:    如果if标签的test值为true才继续往下执行
      * @Author:         Mr.Shen
      * @CreateDate:     2020/9/2 23:03
      * @Param:          dynamicContext
      * @Version:        v1.0
      */
    @Override
    public void apply(DynamicContext dynamicContext) {

        if (OgnlUtils.evaluateBoolean(this.testExpress, dynamicContext.getBindings().get("_parameter"))) {
            sqlNode.apply(dynamicContext);
        }

    }
}