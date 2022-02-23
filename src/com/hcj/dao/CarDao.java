package com.hcj.dao;

import com.hcj.constant.BaseConstants;
import com.hcj.domain.Car;
import com.hcj.util.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * CarDao
 * 车辆数据库操作类
 * 封装业务所需要所有的存储数据的类
 */
public class CarDao {

    // 创建dbutils的核心类QueryRunner
    private QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());

    /**
     * 根据品牌id查找车型列表
     *
     * @param seriesId 车型id
     * @return List<Series>集合列表
     */
    public List<Car> selectCarListBySeriesId(String seriesId) {
        // 创建sql语句(一并考虑车辆是否上架)
        String sql = "select * from car where seriesId = ? and flag = ?";
        // 设置实际参数
        Object[] params = {seriesId, BaseConstants.CAR_FLAG_NORMAL};
        // 创建dbutils的核心接口ResultSetHandler
        BeanListHandler<Car> blh = new BeanListHandler<Car>(Car.class);
        // 执行查询
        List<Car> list = null;
        try {
            list = queryRunner.query(sql, blh, params);
        } catch (SQLException throwables) {
            System.out.println("查询失败，请稍后重试！");
            throwables.printStackTrace();
        }
        return list;
    }

    /**
     * 查询最新发布的车辆列表
     *
     * @param
     * @return List<Car>集合列表
     */
    public List<Car> selectNewPublishCarList() {
        // 创建sql语句(一并考虑车辆是否上架)
        String sql = "select * from car";
        // 设置实际参数
        Object[] params = {};
        // 创建dbutils的核心接口ResultSetHandler
        BeanListHandler<Car> blh = new BeanListHandler<>(Car.class);
        // 执行查询
        List<Car> list = null;
        try {
            list = queryRunner.query(sql, blh, params);
        } catch (SQLException throwables) {
            System.out.println("查询失败，请稍后重试！");
            throwables.printStackTrace();
        }
        return list;
    }

    /**
     * 查询所有车辆列表
     *
     * @param
     * @return LinkedList<Car>集合列表
     */
    public LinkedList<Car> selectCarList() {
        // 创建sql语句(一并考虑车辆是否上架)
        String sql = "select * from car";
        // 设置实际参数
        Object[] params = {};
        // 创建dbutils的核心接口ResultSetHandler
        BeanListHandler<Car> blh = new BeanListHandler<>(Car.class);
        // 执行查询
        LinkedList<Car> list = new LinkedList<>();
        try {
            for (Car car : queryRunner.query(sql, blh)) {
                list.add(car);
            }
        } catch (Exception throwables) {
            System.out.println("查询失败，请稍后重试！");
            throwables.printStackTrace();
        }
        return list;
    }

    /**
     * 根据车辆id查找车辆列表
     *
     * @param carId 车辆id
     * @return Car 车辆对象
     */
    public Car selectCarByCarId(String carId) {
        // 创建sql语句(一并考虑车辆是否上架)
        String sql = "select * from car where carId = ? and flag = ?";
        // 设置实际参数
        Object[] params = {carId, BaseConstants.CAR_FLAG_NORMAL};
        // 创建dbutils的核心接口ResultSetHandler
        BeanHandler<Car> bh = new BeanHandler<Car>(Car.class);
        // 执行查询
        Car car = null;
        try {
            car = queryRunner.query(sql, bh, params);
        } catch (SQLException throwables) {
            System.out.println("查询失败，请稍后重试！");
            throwables.printStackTrace();
        }
        return car;
    }

    /**
     * 根据车辆id更新车辆
     *
     * @param car 车辆对象
     * @return
     */
    public void updateCar(Car car) {
        // 创建sql语句
        String sql = "update car set flag = ? where carId = ?";
        // 设置实际参数
        Object[] params = {BaseConstants.CAR_FLAG_NO, car.getCarId()};
        // 执行更新
        try {
            queryRunner.update(sql, params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 根据价格范围查找车辆列表
     *
     * @param minPrice 最低价格, maxPrice 最高价格
     * @return List<Car> 车辆列表
     */
    public List<Car> selectCarListByPrice(double minPrice, double maxPrice) {
        // 创建sql语句(一并考虑车辆是否上架)
        String sql = "select * from car where flag = ? and price between ? and ?";
        // 设置实际参数
        Object[] params = {BaseConstants.CAR_FLAG_NORMAL, minPrice, maxPrice};
        // 创建dbutils的核心接口ResultSetHandler
        BeanListHandler<Car> blh = new BeanListHandler<>(Car.class);
        // 执行查询
        List<Car> list = null;
        try {
            list = queryRunner.query(sql, blh, params);
        } catch (SQLException throwables) {
            System.out.println("查询失败，请稍后重试！");
            throwables.printStackTrace();
        }
        return list;
    }

    /**
     * 根据年份查找车辆列表
     *
     * @param
     * @return List<Car> 车辆列表
     */
    public List<Car> selectCarListByYear(String year) {
        // 创建sql语句(一并考虑车辆是否上架)
        String sql = "select * from car where flag = ? and passDate >=date_format(?,'%Y-%m-%d')";
        // 设置实际参数
        Object[] params = {BaseConstants.CAR_FLAG_NORMAL, year};
        // 创建dbutils的核心接口ResultSetHandler
        BeanListHandler<Car> blh = new BeanListHandler<>(Car.class);
        // 执行查询
        List<Car> list = null;
        try {
            list = queryRunner.query(sql, blh, params);
        } catch (SQLException throwables) {
            System.out.println("查询失败，请稍后重试！");
            throwables.printStackTrace();
        }
        return list;
    }

    /**
     * 根据里程数查找车辆列表
     *
     * @param
     * @return List<Car> 车辆列表
     */
    public List<Car> selectCarListByMileage(Integer mileage) {
        // 创建sql语句(一并考虑车辆是否上架)
        String sql = "select * from car where flag = ? and mileage <= ?";
        // 设置实际参数
        Object[] params = {BaseConstants.CAR_FLAG_NORMAL, mileage};
        // 创建dbutils的核心接口ResultSetHandler
        BeanListHandler<Car> blh = new BeanListHandler<>(Car.class);
        // 执行查询
        List<Car> list = null;
        try {
            list = queryRunner.query(sql, blh, params);
        } catch (SQLException throwables) {
            System.out.println("查询失败，请稍后重试！");
            throwables.printStackTrace();
        }
        return list;
    }
}
