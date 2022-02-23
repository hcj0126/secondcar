package com.hcj.dao;

import com.hcj.domain.Series;
import com.hcj.util.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * SeriesDao
 *  车型数据库操作类
 * @author hcj
 * @date 2022-02-16
 */
public class SeriesDao {

    // 创建dbutils的核心类QueryRunner
    private QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());

    /**
     * 根据车型id查询车型
     *
     * @param id 车型id
     * @return Series 车型对象
     */
    public Series selectSeriesById(String id) {
        // 创建sql语句
        String sql = "select * from series where seriesId = ?";
        // 设置实际参数
        Object[] params = {id};
        // 创建dbutils的核心接口ResultSetHandler
        BeanHandler<Series> bh = new BeanHandler<Series>(Series.class);
        // 执行查询
        Series series = null;
        try {
            series = queryRunner.query(sql, bh, params);
        } catch (SQLException throwables) {
            System.out.println("查询失败，请稍后重试！");
            throwables.printStackTrace();
        }
        return series;
    }

    /**
     * 查询车型所有列表
     *
     * @param
     * @return List<Series>集合列表
     */
    public List<Series> selectSeriesAll() {
        // 创建sql语句
        String sql = "select * from series";
        // 设置实际参数
        Object[] params = {};
        // 创建dbutils的核心接口ResultSetHandler
        BeanListHandler<Series> blh = new BeanListHandler<Series>(Series.class);
        // 执行查询
        List<Series> list = null;
        try {
            list = queryRunner.query(sql, blh, params);
        } catch (SQLException throwables) {
            System.out.println("查询失败，请稍后重试！");
            throwables.printStackTrace();
        }
        return list;
    }

    /**
     * 根据品牌id查找车型列表
     *
     * @param brandId 品牌id
     * @return List<Series>集合列表
     */
    public List<Series> selectSeriesListByBrandId(String brandId) {
        // 创建sql语句
        String sql = "select * from series where brandId = ?";
        // 设置实际参数
        Object[] params = {brandId};
        // 创建dbutils的核心接口ResultSetHandler
        BeanListHandler<Series> blh = new BeanListHandler<Series>(Series.class);
        // 执行查询
        List<Series> list = null;
        try {
            list = queryRunner.query(sql, blh, params);
        } catch (SQLException throwables) {
            System.out.println("查询失败，请稍后重试！");
            throwables.printStackTrace();
        }
        return list;
    }
}
