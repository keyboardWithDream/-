package cn.study.travel.service;

import cn.study.travel.domain.Category;

import java.util.List;

/**
 * @author Harlan
 * @date 2020/7/26 10:51
 */
public interface CategoryService {

    /**
     * 查询所有类别
     * @return 类别列表
     */
    public List<Category> findAll();
}
