package com.erayt.mybatis.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @Description: 资源文件读取工具
 * @Author: Mr.Shen
 * @CreateDate: 2020/8/30 17:19
 * @Version: 1.0
 */
public class Resources {

    private static final Logger logger = LoggerFactory.getLogger(Resources.class);
     /*
      * @Description:    读取配置文件为inputstream,默认路径为classpath
      * @Author:         Administrator
      * @CreateDate:     2020/8/30 18:16
      * @Param:          location
      * @Version:        v1.0
      */
    public static InputStream getResourceAsStream(String location) {
        logger.info("加载配置文件路径：{}",location);
        return Resources.class.getClassLoader().getResourceAsStream(location);
    }
     /*
      * @Description:    读取配置文件为InputStreamReader,默认路径为classpath
      * @Author:         Mr.Shen
      * @CreateDate:     2020/8/30 18:39
      * @Param:          location
      * @Version:        v1.0
      */
    public static Reader getResourceAsReader(String location) {
        logger.info("加载配置文件路径：{}",location);
      return new InputStreamReader(getResourceAsStream(location));
    }


}