package com.erayt.mybatis.sqlsession.impl;

import com.erayt.mybatis.config.Configuration;
import com.erayt.mybatis.config.MappedStatement;
import com.erayt.mybatis.executor.CachingExecutor;
import com.erayt.mybatis.executor.SimpleExecutor;
import com.erayt.mybatis.executor.iface.Executor;
import com.erayt.mybatis.sqlsession.SqlSession;

import java.util.List;
import java.util.Optional;

/**
 * @Description: 默认session实现
 * @Author: Mr.Shen
 * @CreateDate: 2020/9/2 23:31
 * @Version: 1.0
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> List<T> selectList(String gNamespace, Object params) {

        Optional<MappedStatement> optional = configuration.getMappedStatements().stream().filter(p->
            p.getNamespace().equals(gNamespace)
            ).findFirst();
        if (!optional.isPresent()) {
            return null;
        }
        MappedStatement mappedStatement = optional.get();
        Executor delegate = new CachingExecutor(new SimpleExecutor());
        return delegate.query(mappedStatement,configuration,params);
    }

    @Override
    public <T> T selectOne(String gNamespace, Object params) {
        List<T> list = this.selectList(gNamespace,params);
        if (list != null & list.size() == 1) {
            return list.get(0);
        }
        return null;
    }
}