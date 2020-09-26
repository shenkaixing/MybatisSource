package com.erayt.mybatis.nodehandler;

import com.erayt.mybatis.sqlnode.iface.SqlNode;
import org.dom4j.Element;

import java.util.List;

/**
 * @Description: 动态标签处理器
 * @Author: Administrator
 * @CreateDate: 2020/8/31 23:37
 * @Version: 1.0
 */
public interface NodeHandler {

    void handleNode (Element handlertoELe, List<SqlNode> contents) ;
}
