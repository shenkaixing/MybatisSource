package com.erayt.mybatis.config;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Description: 解析核心配置映射文件,mapper文件
 * @Author: Mr.Shen
 * @CreateDate: 2020/8/30 22:18
 * @Version: 1.0
 */
public class XMLMapperBuilder {

    private Configuration configuration ;

    private static final Logger logger = LoggerFactory.getLogger(XMLMapperBuilder.class);

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parseMapper(Element root) {

    }

    /*
     * @Description:    解析核心配置映射文件,mapper文件
     * @Author:         Mr.Shen
     * @CreateDate:     2020/8/30 22:20
     * @Param:
     * @Version:        v1.0
     */
    @SuppressWarnings("unchecked")
    public void parseMapper(Element root, String resource) {
        String namespace = root.attributeValue("namespace");
        if (namespace == null || namespace.trim().equals("")) {
            logger.error("读取mapper映射文件:{}的namespace为空!",resource);
            return ;
        }
        List<Element> selectEles = root.elements("select");
        selectEles.stream().forEach( p -> {

            XMLStatementBuilder xmlStatementBuilder = new XMLStatementBuilder(configuration);
            xmlStatementBuilder.parseStatement(p,namespace);
        });
    }
}