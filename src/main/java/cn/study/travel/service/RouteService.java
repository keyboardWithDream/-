package cn.study.travel.service;

import cn.study.travel.domain.PageBean;
import cn.study.travel.domain.Route;

/**
 * @author Harlan
 * @date 2020/7/27 14:00
 */
public interface RouteService {

    /**
     * 返回PageBean
     * @param cid 分类id
     * @param currentPage 当前页码
     * @param pageSize 每页条数
     * @return pageBean
     */
    public PageBean<Route> getPageBean(int cid, int currentPage, int pageSize, String rname);
}
