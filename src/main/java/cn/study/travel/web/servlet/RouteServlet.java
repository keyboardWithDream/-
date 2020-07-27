package cn.study.travel.web.servlet;

import cn.study.travel.domain.PageBean;
import cn.study.travel.domain.Route;
import cn.study.travel.service.RouteService;
import cn.study.travel.service.impl.RouteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Harlan
 * @date 2020/7/27 1:07
 */
@WebServlet(name = "/RouteServlet", urlPatterns = ("/route/*"))
public class RouteServlet extends BaseServlet {

    RouteService service = new RouteServiceImpl();

    /**
     * 分页查询
     * @param req
     * @param resp
     * @throws ServletException
     */
    public void pageQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");
        String cidStr = req.getParameter("cid");

        int cid = 0;
        if (cidStr != null && cidStr.length() > 0){
            cid = Integer.parseInt(cidStr);
        }

        int currentPage = 1;
        if (currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }

        int pageSize = 0;
        if (pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else {
            pageSize = 5;
        }

        PageBean<Route> pageBean = service.getPageBean(cid, currentPage, pageSize);
        try {
            writeValue(pageBean, resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
