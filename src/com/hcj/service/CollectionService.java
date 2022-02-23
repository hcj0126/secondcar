package com.hcj.service;

import com.hcj.dao.CollectionDao;
import com.hcj.domain.Collection;
import com.hcj.domain.Series;

import java.util.LinkedList;
import java.util.List;

/**
 * CollectionService
 *
 * @author hcj
 * @date 2022-02-21
 */
public class CollectionService {

    private CollectionDao collectionDao;

    public CollectionService() {
        collectionDao = new CollectionDao();
    }

    /**
     * 根据用户id查找收藏列表
     *
     * @param userId 用户id
     * @return List<Collection>集合列表
     */
    public List<Collection> findMyCollectionListByUserId(String userId) {
        return collectionDao.selectMyCollectionListByUserId(userId);
    }

    /**
     * 根据用户id查找收藏列表
     *
     * @param userId 用户id
     * @return LinkedList<Collection>集合列表
     */
    public LinkedList<Collection> findMyCollectionLinkByUserId(String userId) {
        return collectionDao.selectMyCollectionLinkByUserId(userId);
    }

    /**
     * description
     *
     * @param collection 收藏对象
     * @return void
    */
    public void insertCollection(Collection collection){
        collectionDao.insertCollection(collection);
    }
}
