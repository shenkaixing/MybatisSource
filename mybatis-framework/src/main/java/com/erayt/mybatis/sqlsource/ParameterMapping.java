package com.erayt.mybatis.sqlsource;

/**
 * @Description: 参数名#{}中name,以及对应参数类型
 * @Author: Mr.Shen
 * @CreateDate: 2020/8/31 22:35
 * @Version: 1.0
 */
public class ParameterMapping {

    private String name;

    private Class<?> parameterType;

    public ParameterMapping(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getParameterType() {
        return parameterType;
    }

    public void setParameterType(Class<?> parameterType) {
        this.parameterType = parameterType;
    }
}