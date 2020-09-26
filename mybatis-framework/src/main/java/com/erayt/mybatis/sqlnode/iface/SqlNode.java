package com.erayt.mybatis.sqlnode.iface;

import com.erayt.mybatis.sqlnode.DynamicContext;

/**
 * @Description: 抽象每个sql节点,比如if,where
 * @Author: Administrator
 * @CreateDate: 2020/8/31 22:39
 * @Version: 1.0
 */
public interface SqlNode {
     /*
      * @Description:    apply方法，适用于mybatis执行时机
      * @Author:         Mr.Shen
      * @CreateDate:     2020/8/31 22:41
      * @Param:
      * @Version:        v1.0
      */
    void apply(DynamicContext dynamicContext);
}
