package cn.study.travel.service.impl;

import cn.study.travel.dao.RouteDao;
import cn.study.travel.dao.impl.RouteDaoImpl;
import cn.study.travel.domain.PageBean;
import cn.study.travel.domain.Route;
import cn.study.travel.service.RouteService;

import java.util.List;

/**
 * @author Harlan
 * @date 2020/7/27 14:02
 */
public class RouteServiceImpl implements RouteService {

    RouteDao dao = new RouteDaoImpl();

    @Override
    public PageBean<Route> getPageBean(int cid, int currentPage, int pageSize, String rname) {
        int totalCount = dao.getTotalCount(cid, rname);
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        int start = (currentPage - 1) * pageSize;
        List<Route> routeList = dao.findByPage(cid, start, pageSize, rname);
        PageBean<Route> pageBean = new PageBean<>(totalCount, totalPage, currentPage, pageSize, routeList);
        return pageBean;
    }
}
