package com.erayt.mybatis.sqlsource;

import com.erayt.mybatis.sqlsource.iface.SqlSource;
import com.erayt.mybatis.util.GenericTokenParser;
import com.erayt.mybatis.util.innerface.TokenHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 将解析之后的sql语句和参数集合都封装到StaticSqlSource中
 * @Author: Mr.Shen
 * @CreateDate: 2020/9/3 22:51
 * @Version: 1.0
 */
public class SqlSourceParser {

    public SqlSource parse(String sql) {
        // 先处理#{}，将处理之后的SQL语句，追加到context中
        ParameterMappingTokenHandler PMTH = new ParameterMappingTokenHandler();
        GenericTokenParser gtp = new GenericTokenParser("#{", "}", PMTH);
        // 此处获取到的sql语句，是完全符合JDBC执行要求的语句
        sql = gtp.parse(sql);
        // 将解析之后的sql语句和参数集合都封装到StaticSqlSource中
        return new StaticSqlSource(sql,PMTH.getParameterMappings());
    }

    private static  class ParameterMappingTokenHandler implements TokenHandler {

        private List<ParameterMapping> parameterMappings = new ArrayList<>();

         /*
          * @Author:         Mr.Shen
          * expression是参数名称
          */
        @Override
        public String handleToken(String expression) {
            ParameterMapping parameterMapping = buildParameterMapping(expression);
            parameterMappings.add(parameterMapping);
            return "?";
        }

        private ParameterMapping buildParameterMapping(String expression) {
            return new ParameterMapping(expression);
        }

        public List<ParameterMapping> getParameterMappings() {
            return parameterMappings;
        }
    }
}