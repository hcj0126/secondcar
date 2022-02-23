package com.hcj.domain;

import java.util.Date;
import java.util.Objects;

/**
 * 车辆类
 * 封装了二手车辆的详细信息
 * 例如： 车辆编号
 * 车型编号
 * 里程数
 * 价格
 * 发布时间
 * 排量
 * 上牌的时间
 * 离合器类型
 */
public class Car {
    // 车辆编号
    private String carId;
    // 车型编号
    private String seriesId;
    // 上牌的时间
    private Date passDate;
    // 价格
    private Double price;
    // 离合器类型  0手动档  1自动档   2手自一体
    private int driveType;
    // 发布时间
    private Date publishDate;
    // 是否被删除(是否上架)的标识，默认车辆数据可见
    private Integer flag;
    // 里程数
    private Integer mileage;

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getFlag() {
        return flag;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Car() {
        // TODO Auto-generated constructor stub
    }

    public Car(String carId, String seriesId, Date passDate, double price, int driveType, Date publishDate) {
        super();
        this.carId = carId;
        this.seriesId = seriesId;
        this.passDate = passDate;
        this.price = price;
        this.driveType = driveType;
        this.publishDate = publishDate;
    }

    public Car(String carId, String seriesId, Date passDate, double price, int driveType) {
        super();
        this.carId = carId;
        this.seriesId = seriesId;
        this.passDate = passDate;
        this.price = price;
        this.driveType = driveType;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public Date getPassDate() {
        return passDate;
    }

    public void setPassDate(Date passDate) {
        this.passDate = passDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDriveType() {
        return driveType;
    }

    public void setDriveType(int driveType) {
        this.driveType = driveType;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Integer isFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return driveType == car.driveType && Objects.equals(carId, car.carId) && Objects.equals(seriesId, car.seriesId) && Objects.equals(passDate, car.passDate) && Objects.equals(price, car.price) && Objects.equals(publishDate, car.publishDate) && Objects.equals(flag, car.flag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, seriesId, passDate, price, driveType, publishDate, flag);
    }

    /*@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + carId;
        result = prime * result + driveType;
        result = prime * result + (flag ? 1231 : 1237);
        result = prime * result + ((passDate == null) ? 0 : passDate.hashCode());
        long temp;
        temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((publishDate == null) ? 0 : publishDate.hashCode());
        result = prime * result + seriseId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        Car other = (Car) obj;
        if (carId != other.carId)
            return false;
        return true;
    }*/
}
