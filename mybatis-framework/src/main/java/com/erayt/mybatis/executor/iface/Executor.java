package com.erayt.mybatis.executor.iface;

import com.erayt.mybatis.config.Configuration;
import com.erayt.mybatis.config.MappedStatement;

import java.util.List;

/**
 * @Description: 执行器接口
 * @Author: Mr.Shen
 * @CreateDate: 2020/9/3 23:59
 * @Version: 1.0
 */
public interface Executor {

    /**
     * @param mappedStatement 获取SQL语句和入参出参类型信息
     * @param configuration	获取数据源连接处信息
     * @param param	获取入参类型
     * @return
     */
    <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param);
}
