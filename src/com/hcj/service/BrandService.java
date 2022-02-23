package com.hcj.service;

import com.hcj.dao.BrandDao;
import com.hcj.domain.Brand;

import java.util.List;

/**
 * BrandService
 *
 * @author hcj
 * @date 2022-02-16
 */
public class BrandService {

    private BrandDao brandDao;

    public BrandService() {
        brandDao = new BrandDao();
    }

    public Brand findBrandById(String id) {
        return brandDao.selectBrandById(id);
    }

    public List<Brand> findAllBrands() {
        return brandDao.selectBrandAll();
    }
}
