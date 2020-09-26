package com.erayt.mybatis.config;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: mybatis核心配置对象
 * @Author: Mr.Shen
 * @CreateDate: 2020/8/30 19:50
 * @Version: 1.0
 */
public class Configuration {

    private DataSource dataSource;

    private List<MappedStatement> mappedStatements = new ArrayList<>();

    public Configuration() {
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<MappedStatement> getMappedStatements() {
        return mappedStatements;
    }

    public void setMappedStatements(List<MappedStatement> mappedStatements) {
        this.mappedStatements = mappedStatements;
    }
}