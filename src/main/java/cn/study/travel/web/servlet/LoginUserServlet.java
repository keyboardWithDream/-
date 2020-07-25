package cn.study.travel.web.servlet;

import cn.study.travel.domain.ResultInfo;
import cn.study.travel.domain.User;
import cn.study.travel.service.UserService;
import cn.study.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author Harlan
 * @date 2020/7/26 0:15
 */
@WebServlet(name = "/LoginUserServlet", urlPatterns = ("/loginUserServlet"))
public class LoginUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,String[]> userMap = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, userMap);
            UserService service = new UserServiceImpl();
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
