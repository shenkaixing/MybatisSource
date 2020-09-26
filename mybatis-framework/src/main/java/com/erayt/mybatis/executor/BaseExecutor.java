package com.erayt.mybatis.executor;

import com.erayt.mybatis.config.Configuration;
import com.erayt.mybatis.config.MappedStatement;
import com.erayt.mybatis.executor.iface.Executor;
import com.erayt.mybatis.sqlsource.BoundSql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 一级缓存执行器
 * @Author: Mr.Shen
 * @CreateDate: 2020/9/3 23:58
 * @Version: 1.0
 */
public abstract class BaseExecutor implements Executor {

    private Map<String, List<Object>> oneLevelCache = new HashMap<>();

     /*
      * @Description:    Mybatis默认执行一级缓存
      * @Author:         Mr.Shen
      * @CreateDate:     2020/9/4 0:01
      * @Version:        v1.0
      */
    public  <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param){

        BoundSql boundSql = mappedStatement.getSqlSource().getBoundSql(param);
        String sql = boundSql.getSql();
        if (oneLevelCache.get(sql) != null) {
            return (List<T>) oneLevelCache.get(sql);
        }
        return this.queryFromDataBase( mappedStatement, configuration, param,boundSql);
    }

    protected abstract <T> List<T> queryFromDataBase(MappedStatement mappedStatement, Configuration configuration, Object param, BoundSql boundSql);

}