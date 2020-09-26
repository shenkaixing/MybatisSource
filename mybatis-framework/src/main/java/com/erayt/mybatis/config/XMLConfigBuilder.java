package com.erayt.mybatis.config;

import com.erayt.mybatis.io.Resources;
import com.erayt.mybatis.util.DocumentUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Description: 构建一个mybatis核心配置对象configuration
 * @Author: Mr.Shen
 * @CreateDate: 2020/8/30 19:55
 * @Version: 1.0
 */
public class XMLConfigBuilder {

    private Configuration configuration ;

    private static final Logger logger = LoggerFactory.getLogger(XMLConfigBuilder.class);

    public XMLConfigBuilder(InputStream inputStream) {
        this.configuration = new Configuration();
        this.parseXML(inputStream);
    }

    @SuppressWarnings("unchecked")
    public Configuration parseXML(InputStream inputStream) {
        Document document = DocumentUtils.readDocument(inputStream);
        Element root = document.getRootElement();
        Element envEles = root.element("environments");
        this.parseEnviromentXML(envEles);
        Element mapperEles = root.element("mappers");
        this.parseMappersXML(mapperEles);
        return configuration;
    }
     /*
      * @Description:    解析环境配置,environments标签
      * @Author:         Mr.Shen
      * @CreateDate:     2020/8/30 20:43
      * @Param:          envEles
      * @Version:        v1.0
      */
     @SuppressWarnings("unchecked")
    private void parseEnviromentXML(Element envEles) {
        logger.debug("解析myBatis核心配置文件数据源:environments");
        String defaultEnv = envEles.attributeValue("default");
        List<Element> list = envEles.elements("environment");
        List<Element> matchEnv = new ArrayList<>();
        for (Element element : list) {
            String val = element.attributeValue("id");
            if (defaultEnv.equals(val)) {
                matchEnv.add(element);
            }
        }
        if (matchEnv.size() == 0) {
            logger.error("未匹配到适应的Environment...");
            return ;
        }
        if (matchEnv.size() > 1) {
            logger.error("匹配到多个Environment...");
            return ;
        }
        this.parseDataSource(matchEnv.get(0));
    }

     /*
      * @Description:     解析数据源配置，environment标签
      * @Author:         Mr.Shen
      * @CreateDate:     2020/8/30 21:41
      * @Param:          element
      * @Version:        v1.0
      */
    private void parseDataSource(Element element) {
        Element dataSourceEle = element.element("dataSource");
        String dbType = dataSourceEle.attributeValue("type");
        switch (dbType) {
            case "DBCP" :
                this.creatBasicDataSourceFromXML(dataSourceEle);
                break ;
            case "C3P0" :
                break ;
        }

    }

    @SuppressWarnings("unchecked")
    private void creatBasicDataSourceFromXML(Element dataSourceEle) {
        List<Element> propertyEles = dataSourceEle.elements("property");
        Properties properties = new Properties();
        propertyEles.stream().forEach( p-> {
            String name = p.attributeValue("name");
            String value = p.attributeValue("value");
            properties.put(name,value);
        });
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(driver);
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        this.configuration.setDataSource(basicDataSource);
        logger.info("加载数据源成功 [DbType={}, DriverClass={}, Url={}, UserName={}, Password={}",
                dataSourceEle.attributeValue("type"), driver, url, username, password);
    }

    /*
      * @Description:    解析核心配置映射文件路径,mappers标签
      * @Author:         Mr.Shen
      * @CreateDate:     2020/8/30 20:44
      * @Param:          envEle
      * @Version:        v1.0
      */
    @SuppressWarnings("unchecked")
    private void parseMappersXML(Element mapperEles) {
        logger.debug("解析myBatis核心配置映射文件:mappers");
        List<Element> mappers = mapperEles.elements("mapper");
        mappers.stream().forEach( p-> {
            String resource = p.attributeValue("resource");
            InputStream inputStream = Resources.getResourceAsStream(resource);
            Document document = DocumentUtils.readDocument(inputStream);
            Element root = document.getRootElement();
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
            xmlMapperBuilder.parseMapper(root, resource);
        });
    }


}