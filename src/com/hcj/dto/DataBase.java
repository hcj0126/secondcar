package com.hcj.dto;

import com.hcj.domain.Brand;
import com.hcj.domain.Car;
import com.hcj.domain.Series;
import com.hcj.domain.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * DataBase
 *
 * @author hcj
 * @date 2022-02-16
 */
public class DataBase {
    // 所有的用户
    private List<User> userData;
    // 所有的车辆
    private List<Car> carData;
    // 所有的品牌
    private List<Brand> brandData;
    // 所有的车型
    private List<Series> seriesData;
    // 用户收藏的车辆
    private Map<String, LinkedList<Car>> collectCarData;

    public List<User> getUserData() {
        return userData;
    }

    public void setUserData(List<User> userData) {
        this.userData = userData;
    }

    public List<Car> getCarData() {
        return carData;
    }

    public void setCarData(List<Car> carData) {
        this.carData = carData;
    }

    public List<Brand> getBrandData() {
        return brandData;
    }

    public void setBrandData(List<Brand> brandData) {
        this.brandData = brandData;
    }

    public List<Series> getSeriesData() {
        return seriesData;
    }

    public void setSeriesData(List<Series> seriesData) {
        this.seriesData = seriesData;
    }

    public Map<String, LinkedList<Car>> getCollectCarData() {
        return collectCarData;
    }

    public void setCollectCarData(Map<String, LinkedList<Car>> collectCarData) {
        this.collectCarData = collectCarData;
    }
}
