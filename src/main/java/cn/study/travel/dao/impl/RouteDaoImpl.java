package cn.study.travel.dao.impl;

import cn.study.travel.dao.RouteDao;
import cn.study.travel.domain.Route;
import cn.study.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Harlan
 * @date 2020/7/27 13:42
 */
public class RouteDaoImpl implements RouteDao {

    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int getTotalCount(int cid, String rname) {
        //String sql = "SELECT COUNT(?) FROM tab_route WHERE cid = ?";
        String sql = "SELECT COUNT(*) FROM tab_route WHERE 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if (cid != 0){
            sb.append(" AND cid = ? ");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0){
            sb.append(" AND rame like ? ");
            params.add(" %"+rname+"% ");
        }
        sql = sb.toString();
        System.out.println(sql);
        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        String sql = "SELECT * FROM tab_route WHERE 1 = 1";
        List<Route> routeList = null;
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if (cid != 0){
            sb.append(" AND cid = ? ");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0){
            sb.append(" AND rame like ? ");
            params.add(" %"+rname+"% ");
        }
        sb.append(" LIMIT ? , ?");
        params.add(start);
        params.add(pageSize);
        sql = sb.toString();
        try {
            routeList = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        System.out.println(sql);
        return routeList;
    }
}
