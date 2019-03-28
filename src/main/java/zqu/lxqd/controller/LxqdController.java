package zqu.lxqd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import zqu.lxqd.bean.Lxqd;
import zqu.lxqd.service.LxqdService;

/**
 * @author ljb
 */
@Controller
public class LxqdController {

    @Autowired
    @Qualifier("LxqdService")
    private LxqdService lxqdService;

    public void setLxqdService(LxqdService lxqdService) {
        this.lxqdService = lxqdService;
    }

    @RequestMapping(value = "/selcetLxqd.do", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    public ModelAndView doSelcetLxqd(Lxqd lxqd) {

        ModelAndView modelAndView = new ModelAndView();
        Lxqd resultlxqd = lxqdService.selectByLxqdId(lxqd.getLxqdId());
        modelAndView.addObject("lxqd",resultlxqd);
        modelAndView.setViewName("lookMyLxqd.jsp");

        return modelAndView;
    }

    @RequestMapping(value = "/insertLxqd.do", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    public ModelAndView doInsertLxqd(Lxqd lxqd) {

        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }

    @RequestMapping(value = "/updateLxqd.do", produces = "text/html;charset=utf-8")
    public ModelAndView doUpdateLxqd(Lxqd lxqd) {

        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }
}
