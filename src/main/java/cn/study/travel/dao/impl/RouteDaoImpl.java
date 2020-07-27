package cn.study.travel.dao.impl;

import cn.study.travel.dao.RouteDao;
import cn.study.travel.domain.Route;
import cn.study.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author Harlan
 * @date 2020/7/27 13:42
 */
public class RouteDaoImpl implements RouteDao {

    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int getTotalCount(int cid) {
        String sql = "SELECT COUNT(*) FROM tab_route WHERE cid = ?";
        return template.queryForObject(sql, Integer.class, cid);
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize) {
        String sql = "SELECT * FROM tab_route WHERE cid = ? LIMIT ? , ?";
        List<Route> routeList = null;
        try {
            routeList = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), cid, start, pageSize);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return routeList;
    }
}
