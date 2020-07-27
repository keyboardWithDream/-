package cn.study.travel.dao;

import cn.study.travel.domain.Route;

import java.util.List;

/**
 * @author Harlan
 * @date 2020/7/27 13:37
 */
public interface RouteDao {

    /**
     * 查询总记录条数
     * @param cid 分类的id
     * @return 条数
     */
    public int getTotalCount(int cid);

    /**
     * 分页查询
     * @param cid 分类id
     * @param start 起始查询点
     * @param pageSize 每页条数
     * @return list
     */
    public List<Route> findByPage(int cid, int start, int pageSize);
}
