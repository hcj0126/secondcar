package com.hcj.service;

import com.hcj.dao.SeriesDao;
import com.hcj.domain.Series;

import java.util.List;

/**
 * SeriesService
 *
 * @author hcj
 * @date 2022-02-16
 */
public class SeriesService {

    private SeriesDao seriesDao;

    public SeriesService() {
        seriesDao = new SeriesDao();
    }

    /**
     * 根据车型id查询车型
     *
     * @param id 车型id
     * @return Series 车型对象
     */
    public Series findSeriesById(String id) {
        return seriesDao.selectSeriesById(id);
    }

    /**
     * 查询车型所有列表
     *
     * @param
     * @return List<Series>集合列表
     */
    public List<Series> findSeriesAll() {
        return seriesDao.selectSeriesAll();
    }

    /**
     * 根据品牌id查找车型列表
     *
     * @param brandId 品牌id
     * @return List<Series>集合列表
     */
    public List<Series> findSeriesListByBrandId(String brandId) {
        return seriesDao.selectSeriesListByBrandId(brandId);
    }
}
