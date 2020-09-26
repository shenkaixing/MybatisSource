package com.erayt.mybatis.config;

import com.erayt.mybatis.nodehandler.NodeHandler;
import com.erayt.mybatis.nodehandler.impl.IfNodeHandler;
import com.erayt.mybatis.nodehandler.impl.WhereNodeHandler;
import com.erayt.mybatis.sqlnode.MixedSqlNode;
import com.erayt.mybatis.sqlnode.StaticTextSqlNode;
import com.erayt.mybatis.sqlnode.TextSqlNode;
import com.erayt.mybatis.sqlnode.iface.SqlNode;
import com.erayt.mybatis.sqlsource.DynamicSqlSource;
import com.erayt.mybatis.sqlsource.RawSqlSource;
import com.erayt.mybatis.sqlsource.iface.SqlSource;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 标签解析器
 * @Author: Mr.Shen
 * @CreateDate: 2020/8/30 22:33
 * @Version: 1.0
 */
public class XMLScriptParser {

    private boolean isDnamic = false;

    private Map<String, NodeHandler> handlerMap = new HashMap<>();

    public XMLScriptParser(boolean isDnamic) {
        this.isDnamic = isDnamic;
    }

    public XMLScriptParser() {
        // 注册动态标签解析器
        // 1.if 标签
        this.handlerMap.put("if",new IfNodeHandler());
        // 2.where 标签
        this.handlerMap.put("where",new WhereNodeHandler());
    }

    public SqlSource parseScriptNode(Element selectEle) {

        MixedSqlNode mixedSqlNode = this.parseDynamicTags(selectEle);
        SqlSource sqlSource = null;
        if (isDnamic) {
            sqlSource = new DynamicSqlSource(mixedSqlNode);
        } else {
            sqlSource = new RawSqlSource(mixedSqlNode);
        }
        return sqlSource;
    }

    public MixedSqlNode parseDynamicTags(Element selectEle) {

        List<SqlNode> contents = new ArrayList<>();
        int nodeCount = selectEle.nodeCount();
        for (int i = 0; i < nodeCount; i++) {
            Node node = selectEle.node(i);
            if (node instanceof Text) {
                if (StringUtils.isBlank(node.getText())) {
                    continue;
                }
                String text = node.getText();
                SqlNode textSqlNode = null;
                if (isDynamicTag(text)) {
                    isDnamic = true;
                    textSqlNode = new TextSqlNode(text);
                } else {
                    textSqlNode = new StaticTextSqlNode(text);
                }
                contents.add(textSqlNode);
            } else if (node instanceof Element) {
                Element nodeEle = (Element) node;
                String tagName = nodeEle.getName();
                // 通过标签名匹配对应的处理器
                NodeHandler ifNodeHandler = this.handlerMap.get(tagName);
                ifNodeHandler.handleNode(nodeEle,contents);
            }
        }
        return new MixedSqlNode(contents);
    }

    private boolean isDynamicTag(String text) {
        if ( text.indexOf("${") > 0 ) {
            return true;
        }
        return false;
    }
}