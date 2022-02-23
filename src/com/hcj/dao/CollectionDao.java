package com.hcj.dao;

import com.hcj.domain.Car;
import com.hcj.domain.Collection;
import com.hcj.util.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * CollectionDao
 *
 * @author hcj
 * @date 2022-02-21
 */
public class CollectionDao {

    // 创建dbutils的核心类QueryRunner
    private QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());

    /**
     * 根据用户查询他的收藏列表
     *
     * @param userId 用户id
     * @return List<Collection>集合列表
     */
    public List<Collection> selectMyCollectionListByUserId(String userId) {
        // 创建sql语句
        String sql = "select * from collection where userId=?";
        // 设置实际参数
        Object[] params = {userId};
        // 创建dbutils的核心接口ResultSetHandler
        BeanListHandler<Collection> blh = new BeanListHandler<>(Collection.class);
        // 执行查询
        List<Collection> list = null;
        try {
            list = queryRunner.query(sql, blh, params);
        } catch (Exception throwables) {
            System.out.println("查询失败，请稍后重试！");
            throwables.printStackTrace();
        }
        return list;
    }

    /**
     * 根据用户查询他的收藏列表
     *
     * @param userId 用户id
     * @return LinkedList<Collection>集合列表
     */
    public LinkedList<Collection> selectMyCollectionLinkByUserId(String userId) {
        // 创建sql语句
        String sql = "select * from collection where userId=?";
        // 设置实际参数
        Object[] params = {userId};
        // 创建dbutils的核心接口ResultSetHandler
        BeanListHandler<Collection> blh = new BeanListHandler<>(Collection.class);
        // 执行查询
        LinkedList<Collection> list = new LinkedList<>();
        try {
            for (Collection collection : queryRunner.query(sql, blh, params)) {
                list.add(collection);
            }
        } catch (Exception throwables) {
            System.out.println("查询失败，请稍后重试！");
            throwables.printStackTrace();
        }
        return list;
    }

    /**
     * description
     *
     * @param collection 收藏对象
     * @return void
     */
    public void insertCollection(Collection collection) {
        // 创建sql语句
        String sql = "insert into collection (collectionId,userId,carId,createTime)values (?,?,?,?)";
        // 设置实际参数
        Object[] params = {collection.getCollectionId(), collection.getUserId(), collection.getCarId(), collection.getCreateTime()};
        // 执行更新
        try {
            queryRunner.update(sql, params);
        } catch (SQLException throwables) {
            System.out.println("添加失败，请稍后重试！");
            throwables.printStackTrace();
        }
    }
}
