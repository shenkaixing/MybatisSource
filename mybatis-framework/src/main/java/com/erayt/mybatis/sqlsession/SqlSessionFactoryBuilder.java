package com.erayt.mybatis.sqlsession;

import com.erayt.mybatis.config.Configuration;
import com.erayt.mybatis.config.XMLConfigBuilder;
import com.erayt.mybatis.sqlsession.impl.DefaultSqlSessionFactory;

import java.io.InputStream;
import java.io.Reader;

/**
 * @Description: 使用构建者模式去创建SqlSessionFactory
 * @Author:     Mr.Shen
 * @CreateDate: 2020/9/2 23:05
 * @Version: 1.0
 */
public class SqlSessionFactoryBuilder {

    private Configuration configuration;

    public SqlSessionFactory build(InputStream inputStream){
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(inputStream);
        this.configuration = xmlConfigBuilder.parseXML(inputStream);
        return this.build();
    }

    public SqlSessionFactory build(Reader reader){
        return null;
    }

    public SqlSessionFactory build () {
        return new DefaultSqlSessionFactory(this.configuration);
    }
}
