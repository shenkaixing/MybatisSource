package com.erayt.mybatis.sqlsession.impl;

import com.erayt.mybatis.config.Configuration;
import com.erayt.mybatis.sqlsession.SqlSession;
import com.erayt.mybatis.sqlsession.SqlSessionFactory;

/**
 * @Description: 默认SqlSessionFactory实现
 * @Author: Mr.Shen
 * @CreateDate: 2020/9/2 23:27
 * @Version: 1.0
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(this.configuration);
    }
}