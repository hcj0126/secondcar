package com.hcj.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Druid连接池的工具类   用于提供获得数据库连接对象
 * 跟JDBCUtil类似
 */
public class DruidUtil {
    private static DataSource dataSource;

    static {
        try {
            // 加载dbcp-config.properties文件
            InputStream is = DruidUtil.class.getClassLoader().getResourceAsStream("druid-config.properties");
            // 创建Properties对象  prop.load()：加载数据
            Properties prop = new Properties();
            prop.load(is);
            // 通过dbcp的工厂创建连接池
            dataSource = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据源
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 获取连接对象
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
