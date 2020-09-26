package com.erayt.mybatis.executor;

import com.erayt.mybatis.config.Configuration;
import com.erayt.mybatis.config.MappedStatement;
import com.erayt.mybatis.sqlsource.BoundSql;
import com.erayt.mybatis.sqlsource.ParameterMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: JDBC执行器
 * @Author: Mr.Shen
 * @CreateDate: 2020/9/4 0:20
 * @Version: 1.0
 */
public class SimpleExecutor extends BaseExecutor {

    private static final Logger logger = LoggerFactory.getLogger(SimpleExecutor.class);

    @Override
    protected <T> List<T> queryFromDataBase(MappedStatement mappedStatement, Configuration configuration, Object param,
                                            BoundSql boundSql) {
        List<Object> queryResult = new ArrayList<>();
        try {
            // 获取连接
            Connection connection = getConnection(configuration);
            // 获取sql
            String sql = boundSql.getSql();
            ResultSet resultSet = null;
            // 创建不同类型的statement
            switch (mappedStatement.getStatementType()) {
                case "prepared" :
                    // 创建Statement
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    // 设置参数
                    parameterize(preparedStatement,boundSql,param,mappedStatement);
                    // 执行Statemen
                    resultSet = preparedStatement.executeQuery();
                    break;
                case "callable" :
                    //
                    break;
            }
            // 处理结果集
            if (resultSet != null) {
                handleResultSet(mappedStatement, resultSet, queryResult);
            }
        } catch (Exception e) {

        }

        return ( List<T> ) queryResult;
    }

    private void handleResultSet(MappedStatement mappedStatement, ResultSet resultSet, List<Object> queryResult) {
       try {
           Class<?> resutClass = mappedStatement.getResultType();
           while (resultSet.next()){
               Object result = resutClass.newInstance();
               ResultSetMetaData setMetaData = resultSet.getMetaData();
               int count = setMetaData.getColumnCount();
               for (int i = 0; i<count; i++ ) {
                   String columnName = setMetaData.getColumnName(i+1);
                   Field field = resutClass.getDeclaredField(columnName);
                   field.setAccessible(true);
                   field.set(result,resultSet.getObject(i+1));
               }
               queryResult.add(result);
           }
       } catch (Exception e) {
           logger.error("init error SimpleExecutor.handleResultSet");
       }
    }

    private void parameterize(PreparedStatement preparedStatement, BoundSql boundSql, Object param, MappedStatement mappedStatement) {

        try {
            Class<?> parameterType =  mappedStatement.getParameterType();
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
                if (parameterType == Integer.class) {
                    preparedStatement.setObject(1,String.valueOf(param));
                } else if(parameterType == String.class) {
                    preparedStatement.setObject(1,String.valueOf(param));
                } else if (parameterType == Map.class) {
                    //
                } else {
                    // 对象
                    for (int index = 0; index < parameterMappings.size(); index ++) {
                        String paramName = parameterMappings.get(index).getName();
                        Class clazz = param.getClass();
                        Field paramField = clazz.getDeclaredField(paramName);
                        paramField.setAccessible(true);
                        Object value = paramField.get(param);
                        preparedStatement.setObject(index+1,value);
                    }

                }

        } catch (Exception e){
            logger.error("init error SimpleExecutor.parameterize");
        }
    }

    private Connection getConnection(Configuration configuration) {
        try {
            return configuration.getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}