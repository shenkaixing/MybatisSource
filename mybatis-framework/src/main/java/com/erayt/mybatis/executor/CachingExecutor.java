package com.erayt.mybatis.executor;

import com.erayt.mybatis.config.Configuration;
import com.erayt.mybatis.config.MappedStatement;
import com.erayt.mybatis.executor.iface.Executor;

import java.util.List;

/**
 * @Description: Mybatis 查询二级缓存,基本不会使用到
 * @Author: Mr.Shen
 * @CreateDate: 2020/9/4 0:17
 * @Version: 1.0
 */
public class CachingExecutor implements Executor{

    private Executor delegate;

    public CachingExecutor(Executor delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param) {
        return this.delegate.query(mappedStatement,configuration,param);
    }
}