package cn.study.travel.service.impl;

import cn.study.travel.dao.CategoryDao;
import cn.study.travel.dao.impl.CategoryDaoImpl;
import cn.study.travel.domain.Category;
import cn.study.travel.service.CategoryService;
import cn.study.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Harlan
 * @date 2020/7/26 10:53
 */
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao dao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        Jedis jedis = JedisUtil.getJedis();
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
        List<Category> categoryList = null;
        if (categorys == null || categorys.size() == 0){
            categoryList = dao.findAll();
            for (Category cs : categoryList) {
                jedis.zadd("category", cs.getCid(), cs.getCname());
            }
        }else {
            categoryList = new ArrayList<>();
            for (Tuple tuple : categorys) {
                Category c = new Category();
                c.setCname(tuple.getElement());
                c.setCid((int) tuple.getScore());
                categoryList.add(c);
            }
        }
        return categoryList;
    }
}
