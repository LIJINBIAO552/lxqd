package zqu.lxqd.Listener;

import zqu.lxqd.bean.Admin;
import zqu.lxqd.bean.Students;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;

/**
 * @author ljb
 */
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        Admin admin = (Admin)session.getAttribute("admin");
        Students students = (Students) session.getAttribute("student");
        if (admin != null || students != null){
            ServletContext application =session.getServletContext();
            Map<String,Object> logListMap = (Map<String, Object>) application.getAttribute("logListMap");
            if (admin != null){
                logListMap.remove(admin.getAdminid());
                application.setAttribute("logListMap",logListMap);
                session.removeAttribute("admin");
            }
            if (students != null){
                logListMap.remove(students.getStudid());
                application.setAttribute("logListMap",logListMap);
                session.removeAttribute("user");
            }
        }
    }
}
