package com.erayt.mybatis.util.innerface;

/**
 * @Description: 工具类内部接口
 * @Author: Mr.shen
 * @CreateDate: 2020/9/2 22:30
 * @Version: 1.0
 */
public interface TokenHandler {
     /*
      * @Description:    根据传入的参数名抽取参数值
      * @Author:         Mr.Shen
      * @CreateDate:     2020/9/2 22:32
      * @Param:          params
      * @Version:        v1.0
      */
    String handleToken (String expression) ;
}
