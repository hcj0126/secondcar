package com.hcj.domain;

/**
 * 车辆型号
 */
public class Series {

    // 车型编号
    private String seriesId;
    // 车型名称
    private String seriesName;
    // 品牌  多对一
    private String brandId;

    public Series() {
        // TODO Auto-generated constructor stub
    }

    public Series(String seriesId, String seriesName, String brandId) {
        super();
        this.seriesId = seriesId;
        this.seriesName = seriesName;
        this.brandId = brandId;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
}
