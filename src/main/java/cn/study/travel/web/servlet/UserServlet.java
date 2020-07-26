package cn.study.travel.web.servlet;

import cn.study.travel.domain.ResultInfo;
import cn.study.travel.domain.User;
import cn.study.travel.service.UserService;
import cn.study.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author Harlan
 * @date 2020/7/26 9:03
 */
@WebServlet(name = "/UserServlet", urlPatterns = ("/user/*"))
public class UserServlet extends BaseServlet {

    private final UserService service = new UserServiceImpl();

    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_code = req.getParameter("check");
        HttpSession session = req.getSession();
        String code = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (code == null || !code.equalsIgnoreCase(user_code)){
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            resp.setContentType("application/json; charset=UTF-8");
            resp.getWriter().write(json);
            return;
        }

        Map<String, String[]> userMap = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, userMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        boolean flag = service.register(user);

        ResultInfo info = new ResultInfo();
        if (flag){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().write(json);
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> userMap = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, userMap);
            user = service.login(user);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        ResultInfo info = new ResultInfo();
        if (user == null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }else {
            if ("N".equals(user.getStatus())){
                info.setErrorMsg("用户未激活请先激活");
                info.setFlag(false);
            }else {
                info.setFlag(true);
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json; charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(), info);
    }

    public void find(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        System.out.println(user);
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json; charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(), user);
    }

    public void exit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //销毁Session
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath()+"/login.html");
    }

    public void active(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        boolean flag = service.activeUser(code);
        String msg = null;
        if (flag){
            msg = "激活成功, 请<a href='http://localhost/travel/login.html'>登录</a>";
        }else {
            msg = "激活失败";
        }
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write(msg);
    }
}
