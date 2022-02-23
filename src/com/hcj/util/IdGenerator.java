package com.hcj.util;

/**
 * 编号生成器
 */
public class IdGenerator {
    // 用户编号
    private static int userId=1;
    // 品牌编号
    private static int brandId = 2;
    // 车型编号
    private static int seriesId = 4;
    // 车辆编号
    private static int carId = 50;

    /**
     * 生成车辆编号
     *
     * @return
     */
    public static int generateCarId() {
        return ++carId;
    }

    /**
     * 生成用户编号
     *
     * @return
     */
    public static int generateUserId() {
        return ++userId;
    }

    /**
     * 生成品牌编号
     */
    public static int generateBrandId() {
        return ++brandId;
    }

    /**
     * 生成车型编号
     */
    public static int generateSeriesId() {
        return ++seriesId;
    }
}
