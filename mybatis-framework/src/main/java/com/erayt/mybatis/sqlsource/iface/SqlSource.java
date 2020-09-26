package com.erayt.mybatis.sqlsource.iface;

import com.erayt.mybatis.sqlsource.BoundSql;

/**
 * @Description: 每个MappedStatement有多个SqlSource
 * @Author: Administrator
 * @CreateDate: 2020/8/30 22:26
 * @Version: 1.0
 */
public interface SqlSource {

    BoundSql getBoundSql(Object params);
}
