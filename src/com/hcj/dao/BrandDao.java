package com.hcj.dao;

import com.hcj.domain.Brand;
import com.hcj.util.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * BrandDao
 *   品牌数据库操作类
 * @author hcj
 * @date 2022-02-16
 */
public class BrandDao {
    // 创建dbutils的核心类QueryRunner
    private static QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());

    /**
     * 根据品牌id查询
     *
     * @param id 品牌id
     * @return Brand
     */
    public Brand selectBrandById(String id) {
        // 创建sql语句
        String sql = "select * from brand where brandId = ?";
        // 设置实际参数
        Object[] params = {id};
        // 创建dbutils的核心接口ResultSetHandler
        BeanHandler<Brand> bh = new BeanHandler<Brand>(Brand.class);
        // 执行查询
        Brand brand = null;
        try {
            brand = queryRunner.query(sql, bh, params);
        } catch (SQLException throwables) {
            System.out.println("查询失败，请稍后重试！");
            throwables.printStackTrace();
        }
        return brand;
    }

    /**
     * 查询品牌所有列表
     *
     * @param
     * @return List<Brand>集合列表
     */
    public List<Brand> selectBrandAll() {
        // 创建sql语句
        String sql = "select * from brand";
        // 设置实际参数
        Object[] params = {};
        // 创建dbutils的核心接口ResultSetHandler
        BeanListHandler<Brand> blh = new BeanListHandler<Brand>(Brand.class);
        // 执行查询
        List<Brand> list = null;
        try {
            list = queryRunner.query(sql, blh, params);
        } catch (SQLException throwables) {
            System.out.println("查询失败，请稍后重试！");
            throwables.printStackTrace();
        }
        return list;
    }
}
