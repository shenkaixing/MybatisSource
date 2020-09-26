package com.erayt.mybatis.sqlsession;

import java.util.List;

/**
 * @Description: SqlSession核心接口
 * @Author: Mr.Shen
 * @CreateDate: 2020/9/2 23:08
 * @Version: 1.0
 */
public interface SqlSession {

    <T> List<T> selectList(String gNamespace, Object params);

    <T> T selectOne(String gNamespace, Object params);
}
