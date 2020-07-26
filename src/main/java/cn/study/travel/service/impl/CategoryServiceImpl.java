package cn.study.travel.service.impl;

import cn.study.travel.dao.CategoryDao;
import cn.study.travel.dao.impl.CategoryDaoImpl;
import cn.study.travel.domain.Category;
import cn.study.travel.service.CategoryService;

import java.util.List;

/**
 * @author Harlan
 * @date 2020/7/26 10:53
 */
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao dao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
       return dao.findAll();
    }
}
