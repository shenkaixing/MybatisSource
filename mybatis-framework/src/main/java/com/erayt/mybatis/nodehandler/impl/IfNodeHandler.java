package com.erayt.mybatis.nodehandler.impl;

import com.erayt.mybatis.config.XMLScriptParser;
import com.erayt.mybatis.nodehandler.NodeHandler;
import com.erayt.mybatis.sqlnode.IfSqlNode;
import com.erayt.mybatis.sqlnode.MixedSqlNode;
import com.erayt.mybatis.sqlnode.iface.SqlNode;
import org.dom4j.Element;

import java.util.List;

/**
 * @Description: if 标签解析器
 * @Author: Mr.Shen
 * @CreateDate: 2020/8/31 23:43
 * @Version: 1.0
 */
public class IfNodeHandler implements NodeHandler {
    @Override
    public void handleNode(Element handlertoELe, List<SqlNode> contents) {
        XMLScriptParser xmlScriptParser = new XMLScriptParser();
        String testExpress = handlertoELe.attributeValue("test");
        MixedSqlNode mixedSqlNode = xmlScriptParser.parseDynamicTags(handlertoELe);
        contents.add(new IfSqlNode(testExpress,mixedSqlNode));
    }
}