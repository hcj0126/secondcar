package com.hcj.service;

import com.hcj.dao.CarDao;
import com.hcj.domain.Car;

import java.util.LinkedList;
import java.util.List;

/**
 * 二手车交易系统平台的主业务
 */
public class CarService {

    private CarDao carDao;

    public CarService() {
        carDao = new CarDao();
    }

    /**
     * 根据车型id查找车辆列表
     *
     * @param seriesId 车型id
     * @return List<Car>集合列表
     */
    public List<Car> findCarListBySeriesId(String seriesId) {
        return carDao.selectCarListBySeriesId(seriesId);
    }

    /**
     * 查询最新发布的车辆列表
     *
     * @param
     * @return List<Car>集合列表
     */
    public List<Car> findNewPublishCarList() {
        return carDao.selectNewPublishCarList();
    }

    /**
     * 查询所有车辆列表
     *
     * @param
     * @return LinkedList<Car>集合列表
     */
    public LinkedList<Car> selectCarList() {
        return carDao.selectCarList();
    }

    /**
     * 根据车辆id查找车辆列表
     *
     * @param carId 车辆id
     * @return List<Car>集合列表
     */
    public Car findCarByCarId(String carId) {
        return carDao.selectCarByCarId(carId);
    }

    /**
     * 根据车辆id更新车辆
     *
     * @param car 车辆对象
     * @return
     */
    public void updateCar(Car car) {
        carDao.updateCar(car);
    }

    /**
     * 根据价格范围查找车辆列表
     *
     * @param minPrice 最低价格, maxPrice 最高价格
     * @return List<Car> 车辆列表
     */
    public List<Car> findCarListByPrice(double minPrice, double maxPrice) {
        return carDao.selectCarListByPrice(minPrice, maxPrice);
    }

    /**
     * 根据年份查找车辆列表
     *
     * @param
     * @return List<Car> 车辆列表
     */
    public List<Car> findCarListByYear(String year) {
        return carDao.selectCarListByYear(year);
    }

    /**
     * 根据里程数查找车辆列表
     *
     * @param
     * @return List<Car> 车辆列表
     */
    public List<Car> findCarListByMileage(Integer mileage) {
        return carDao.selectCarListByMileage(mileage);
    }
}
