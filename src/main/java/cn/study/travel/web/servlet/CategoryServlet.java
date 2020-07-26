package cn.study.travel.web.servlet;

import cn.study.travel.domain.Category;
import cn.study.travel.domain.ResultInfo;
import cn.study.travel.service.CategoryService;
import cn.study.travel.service.impl.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Harlan
 * @date 2020/7/26 9:17
 */
@WebServlet(name = "/CategoryServlet", urlPatterns = ("/category/*"))
public class CategoryServlet extends BaseServlet {

    private final CategoryService service = new CategoryServiceImpl();

    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = service.findAll();
        if (categories == null){
            System.out.println("查询错误");
        }else {
            writeValue(categories, resp);
        }
    }
}
