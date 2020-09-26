package com.erayt.mybatis.sqlsession;

/**
 * @Description: SqlSession工厂
 * @Author: Mr.Shen
 * @CreateDate: 2020/9/2 23:07
 * @Version: 1.0
 */
public interface SqlSessionFactory {

    SqlSession openSession();
}
