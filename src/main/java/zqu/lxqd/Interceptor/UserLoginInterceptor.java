package zqu.lxqd.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import zqu.lxqd.bean.Students;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author ljb
 */
public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //获得请求的URL
        String url = httpServletRequest.getRequestURI();
        //URL：除了adminLogin.do是可以公共访问，其他的URL都进行拦截控制
        if (url.indexOf("userLogin.do") >= 0 || url.indexOf("userLogout.do") >= 0) {
            return true;
        }
        //获得session
        HttpSession session = httpServletRequest.getSession();
        Students students = (Students) session.getAttribute("student");

        //如果有用户数据，则返回true,继续向下执行
        if (students != null && url.indexOf("students") >= 0) {
            return true;
        }
        //不合条件的给现提示信息，并转发到页面
        httpServletRequest.setAttribute("msg", "您还没登录，请先登录！");
        httpServletRequest.getRequestDispatcher("/students/userLogout.do").forward(httpServletRequest, httpServletResponse);

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
