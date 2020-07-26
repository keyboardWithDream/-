package cn.study.travel.dao;

import cn.study.travel.domain.Category;

import java.util.List;

/**
 * @author Harlan
 * @date 2020/7/26 10:40
 */
public interface CategoryDao {

    /**
     * 查询所有类别
     * @return 类别对象List
     */
    public List<Category> findAll();
}
