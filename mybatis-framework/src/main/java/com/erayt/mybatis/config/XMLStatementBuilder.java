package com.erayt.mybatis.config;

import com.erayt.mybatis.sqlsource.iface.SqlSource;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: MappedStatement构建者
 * @Author: Mr.Shen
 * @CreateDate: 2020/8/30 22:33
 * @Version: 1.0
 */
public class XMLStatementBuilder {

    private Configuration configuration;

    private static final Logger logger = LoggerFactory.getLogger(XMLStatementBuilder.class);

    public XMLStatementBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parseStatement(Element selectEle, String namespace) {

        String statementId = selectEle.attributeValue("id");
        if (statementId == null || statementId.trim().equals("")) {
            logger.error("读取mapper映射文件namespace={}的statementId为空!",namespace);
        }
        String globalNamespace = namespace.concat(".").concat(statementId);
        logger.debug("加载到Mapper映射文件的命名全局空间namespace={}",globalNamespace);
        String parameterType = selectEle.attributeValue("parameterType");
        Class<?> statementTypeClazz = this.resolveType(parameterType);

        String resultType = selectEle.attributeValue("resultType");
        Class<?> resultTypeClazz = this.resolveType(resultType);

        String statementType = selectEle.attributeValue("statementType");
        statementType = StringUtils.isBlank(statementType) ? null : statementType;

        XMLScriptParser xmlScriptParser = new XMLScriptParser();
        SqlSource sqlSource = xmlScriptParser.parseScriptNode(selectEle);

        MappedStatement mappedStatement = new MappedStatement(globalNamespace,statementTypeClazz,
                statementTypeClazz,statementType,sqlSource);
        configuration.getMappedStatements().add(mappedStatement);

    }

    private Class<?> resolveType(String parameterType) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(parameterType);
        } catch (ClassNotFoundException e) {
            logger.error("类型转换异常:parameterType={},错误信息={}",parameterType,e.getMessage());
            e.printStackTrace();
        }
        return clazz;
    }
}