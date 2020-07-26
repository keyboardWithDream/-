package cn.study.travel.dao.impl;

import cn.study.travel.dao.CategoryDao;
import cn.study.travel.domain.Category;
import cn.study.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Harlan
 * @date 2020/7/26 10:42
 */
public class CategoryDaoImpl implements CategoryDao {

    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        String sql = "SELECT * FROM tab_category";
        List<Category> categories = null;
        try {
            categories = template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
