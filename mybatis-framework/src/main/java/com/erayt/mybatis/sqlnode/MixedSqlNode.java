package com.erayt.mybatis.sqlnode;

import com.erayt.mybatis.sqlnode.iface.SqlNode;

import java.util.List;

/**
 * @Description: 封装多层结构的sqlnode
 * @Author: Mr.Shen
 * @CreateDate: 2020/8/31 23:11
 * @Version: 1.0
 */
public class MixedSqlNode implements SqlNode {

    List<SqlNode> sqlNodes = null;//new ArrayList<>();

    public MixedSqlNode(List<SqlNode> sqlNodes) {
        this.sqlNodes = sqlNodes;
    }

    @Override
    public void apply(DynamicContext dynamicContext) {
        this.sqlNodes.stream().forEach( p -> {
            p.apply(dynamicContext);
        });
    }
}