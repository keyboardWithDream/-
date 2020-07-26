package cn.study.travel.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Harlan
 * @date 2020/7/26 9:17
 */
@WebServlet(name = "/CategoryServlet", urlPatterns = ("/category/*"))
public class CategoryServlet extends BaseServlet {

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public void find(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
