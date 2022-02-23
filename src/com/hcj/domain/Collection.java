package com.hcj.domain;

import java.util.Date;

/**
 * Collection
 *
 * @author hcj
 * @date 2022-02-21
 */
public class Collection {

    // 收藏Id
    private String collectionId;
    // 用户id
    private String userId;
    // 车辆Id
    private String carId;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date updateTime;

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "collectionId='" + collectionId + '\'' +
                ", userId='" + userId + '\'' +
                ", carId='" + carId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
