package cn.study.travel.web.filter; /**
 * @author Harlan
 * @date 2020/7/24 14:59
 */

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CharchaterFilter", urlPatterns = "/")
public class CharchaterFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String method = request.getMethod();

        if (method.equalsIgnoreCase("post")){
            request.setCharacterEncoding("utf-8");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
