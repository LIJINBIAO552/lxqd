package zqu.lxqd.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import zqu.lxqd.bean.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author ljb
 */
public class AdminLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //获得请求的URL
        String url = httpServletRequest.getRequestURI();
        //URL：除了adminLogin.do是可以公共访问，其他的URL都进行拦截控制
        if (url.indexOf("adminLogin.do") >= 0 || url.indexOf("adminLogout.do") >= 0 ) {
            return true;
        }
        //获得session
        HttpSession session = httpServletRequest.getSession();

        Admin admin = (Admin) session.getAttribute("admin");

        //如果有用户数据，则返回true,继续向下执行
        if (admin != null ) {
            if ((url.indexOf("admin/SuppCent/") >= 0 || url.indexOf("admin/updateAdmin") >= 0) && admin.getDepartid().equals("1")){
                return true;
            }else if ((url.indexOf("admin/DormMana/") >= 0 || url.indexOf("admin/updateAdmin") >= 0) && admin.getDepartid().equals("2")){
                return true;
            }else if ((url.indexOf("admin/StudAffaDivi/") >= 0 || url.indexOf("admin/updateAdmin") >= 0) && admin.getDepartid().equals("3")){
                return true;
            }else if ((url.indexOf("admin/Hydropower/") >= 0 || url.indexOf("admin/updateAdmin") >= 0) && admin.getDepartid().equals("4")){
                return true;
            }else if ((url.indexOf("admin/DrinkWater/") >= 0 || url.indexOf("admin/updateAdmin") >= 0)&& admin.getDepartid().equals("5")){
                return true;
            }else if ((url.indexOf("admin/LogiMana/") >= 0 || url.indexOf("admin/updateAdmin") >= 0) && admin.getDepartid().equals("6")){
                return true;
            }else if ((url.indexOf("admin/Library/") >= 0 || url.indexOf("admin/updateAdmin") >= 0)&& admin.getDepartid().equals("7")){
                return true;
            }else if ((url.indexOf("admin/HotWater1/") >= 0 || url.indexOf("admin/updateAdmin") >= 0) && admin.getDepartid().equals("8")){
                return true;
            }else if ((url.indexOf("admin/HotWater2/") >= 0 || url.indexOf("admin/updateAdmin") >= 0) && admin.getDepartid().equals("9")){
                return true;
            }else if ((url.indexOf("admin/EducAndComp/") >= 0 || url.indexOf("admin/updateAdmin") >= 0) && admin.getDepartid().equals("10")){
                return true;
            }else if ((url.indexOf("admin/ClasTeacSign/") >= 0 || url.indexOf("admin/updateAdmin") >= 0) && admin.getDepartid().equals("11")){
                return true;
            }else if ((url.indexOf("admin/AcadSign/") >= 0 || url.indexOf("admin/Acadsign/") >= 0
                    || url.indexOf("admin/updateAdmin") >= 0)&& admin.getDepartid().equals("20")){
                return true;
            }else if (url.indexOf("admin/admin") >=0 && admin.getDepartid().equals("100")){
                return true;
            }else if (url.indexOf("MyselfOfAdmin.do") >=0 || url.indexOf("MyselfOfPW.do") >=0){
                return true;
            }else if (url.indexOf("admin/") >=0 && admin.getDepartid().equals("100")){
                return true;
            }else {
                return false;
            }
        }
        //不合条件的给现提示信息，并转发到页面
        httpServletRequest.setAttribute("msg", "您还没登录，请先登录！");

        httpServletRequest.getRequestDispatcher("/admin/adminLogin.jsp").forward(httpServletRequest, httpServletResponse);

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }
}
