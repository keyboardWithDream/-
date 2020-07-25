package cn.study.travel.web.servlet;

import cn.study.travel.service.UserService;
import cn.study.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Harlan
 * @date 2020/7/25 22:52
 */
@WebServlet(name = "/ActiveUserServlet", urlPatterns = ("/activeUserServlet"))
public class ActiveUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        UserService service = new UserServiceImpl();
        boolean flag = service.activeUser(code);
        String msg = null;
        if (flag){
            msg = "激活成功, 请<a href='login.html'>登录</a>";
        }else {
            msg = "激活失败";
        }
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write(msg);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
