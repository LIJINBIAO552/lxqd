package zqu.lxqd.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import zqu.lxqd.bean.*;
import zqu.lxqd.service.*;
import zqu.lxqd.utils.PhotoUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ljb
 */
@Controller
@RequestMapping("students")
public class StudentsController {
    @Autowired
    @Qualifier("StudentsService")
    private StudentsService studentsService;

    public void setStudentsService(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @Autowired
    @Qualifier("DepartmentService")
    private DepartmentService departmentService;

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Autowired
    @Qualifier("AcadsignService")
    private AcadsignService acadsignService;
    @Autowired
    @Qualifier("AdminService")
    private AdminService adminService;
    @Autowired
    @Qualifier("ClasteacsignService")
    private ClasTeacSignService clasTeacSignService;
    @Autowired
    @Qualifier("DormmanaService")
    private DormmanaService dormmanaService;
    @Autowired
    @Qualifier("DrinkwaterService")
    private DrinkwaterService drinkwaterService;
    @Autowired
    @Qualifier("EducandcompService")
    private EducAndCompService educAndCompService;
    @Autowired
    @Qualifier("Hotwater1Service")
    private Hotwater1Service hotwater1Service;
    @Autowired
    @Qualifier("Hotwater2Service")
    private Hotwater2Service hotwater2Service;
    @Autowired
    @Qualifier("HydropowerService")
    private HydropowerService hydropowerService;
    @Autowired
    @Qualifier("LibraryService")
    private LibraryService libraryService;
    @Autowired
    @Qualifier("LogimanaService")
    private LogimanaService logimanaService;
    @Autowired
    @Qualifier("LxqdService")
    private LxqdService lxqdService;
    @Autowired
    @Qualifier("StudaffadiviService")
    private StudaffadiviService studaffadiviService;
    @Autowired
    @Qualifier("SuppcentService")
    private SuppcentService suppcentService;

    @Autowired
    @Qualifier("ProcedureService")
    private ProcedureService procedureService;

    public void setProcedureService(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    public void setAcadsignService(AcadsignService acadsignService) {
        this.acadsignService = acadsignService;
    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public void setClasTeacSignService(ClasTeacSignService clasTeacSignService) {
        this.clasTeacSignService = clasTeacSignService;
    }

    public void setDormmanaService(DormmanaService dormmanaService) {
        this.dormmanaService = dormmanaService;
    }

    public void setDrinkwaterService(DrinkwaterService drinkwaterService) {
        this.drinkwaterService = drinkwaterService;
    }

    public void setEducAndCompService(EducAndCompService educAndCompService) {
        this.educAndCompService = educAndCompService;
    }

    public void setHotwater1Service(Hotwater1Service hotwater1Service) {
        this.hotwater1Service = hotwater1Service;
    }

    public void setHotwater2Service(Hotwater2Service hotwater2Service) {
        this.hotwater2Service = hotwater2Service;
    }

    public void setHydropowerService(HydropowerService hydropowerService) {
        this.hydropowerService = hydropowerService;
    }

    public void setLibraryService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public void setLogimanaService(LogimanaService logimanaService) {
        this.logimanaService = logimanaService;
    }

    public void setLxqdService(LxqdService lxqdService) {
        this.lxqdService = lxqdService;
    }

    public void setStudaffadiviService(StudaffadiviService studaffadiviService) {
        this.studaffadiviService = studaffadiviService;
    }

    public void setSuppcentService(SuppcentService suppcentService) {
        this.suppcentService = suppcentService;
    }


    @RequestMapping(value = "userLogin.do", method = RequestMethod.GET)
    public String toLogin() {
        return "redirect:login.jsp";
    }

    @RequestMapping(value = "userLogin.do", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String doLogin(String userid, String userPass, String checkRemem1,
//                          String captcha,
                          HttpSession session, HttpServletResponse response) {
        // 获取Kaptcha jar包里面的KAPTCHA_SESSION_KEY
//        String trueCaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
//        if (trueCaptcha.toLowerCase().equals(captcha.toLowerCase())) {
        // 判断输入的账号密码是否为空
        if (userid != null && userPass != null) {
            ServletContext application = session.getServletContext();
            Map<String,Object> logListMap = (Map<String, Object>) application.getAttribute("logListMap");
            if (logListMap == null){
                logListMap = new HashMap<String, Object>();
            }else {
                for (String userLogId : logListMap.keySet()) {
                    if (userid.equals(userLogId)){
                        if (session.getId().equals(logListMap.get(userLogId))){
                            return "该用户已经登录,请别重复登录!";
                        }else {
                            return "该用户已在别处登录，请先退出登录";
                        }
                    }
                }
            }
            //定义字符串类型变量来接收数据库查到的密码
            String spass = null;
            //从数据库中按id查询相应的用户
            Students students = studentsService.selectByStudId(userid);
//            判断是否有用户存在，若存在则获取数据库中的密码
            if (students != null) {
                spass = students.getStudpass();
            } else {
                return "用户不存在，请从新输入用户！";
            }
            //通过判断用户输入的密码是否与数据库中的密码相同，相同则存进session中，不同则返回错误信息
            if (userPass.equals(spass)) {

                logListMap.put(userid,session.getId());
                application.setAttribute("logListMap",logListMap);
                //判断查看学生是那一年的,本科还是专科,
                String studgrade = students.getGrade().substring(0, 2);
                int endyear = Calendar.getInstance().get(Calendar.YEAR);
                if (students.getEducation().equals("本") || students.getEducation().equals("本科")) {
                    endyear = Integer.valueOf(studgrade) + 4;
                } else if (students.getEducation().equals("专") || students.getEducation().equals("专科")) {
                    endyear = Integer.valueOf(studgrade) + 3;
                }
                //获取毕业时间
                List<Procedure> procedureList = procedureService.selectProcedureByLimitYear("20" + endyear + "");
                for (Procedure procedure1 : procedureList) {
                    if (procedure1.getEvent().equals("毕业离校")) {
                        //将毕业离校时间保存到students中
                        students.setProcedure(procedure1);
                    }
                }
                //将用户存进session中
                session.setAttribute("student", students);
//                判断用户是否选择记住密码
                if (checkRemem1 != null) {
//                  创建两个Cookie对象
                    Cookie userIdCookie = new Cookie("StudentId", userid);
                    Cookie userPassCookie = new Cookie("StudentPassword", userPass);
//                    设置Cookies有效期为7天
                    userIdCookie.setMaxAge(60 * 60 * 24 * 7);
                    userPassCookie.setMaxAge(60 * 60 * 24 * 7);

                    response.addCookie(userIdCookie);
                    response.addCookie(userPassCookie);
                }
                if (students.getDepartId().equals("20")) {
                    return "success_login";
                } else if (students.getDepartId().equals("21")) {
                    return "success_login";
                } else {
                    return "other_login";
                }
            } else {
                return "用户名或密码错误，请重新登录！";
            }
        } else {
            return "用户名和密码不能为空，请重新输入!";
        }
//        } else {
//            return "验证码错误，请重新输入！";
//        }
    }


    //跳转到主页面
    @RequestMapping("/StudentsMain.do")
    public String toMain() {
        return "forward:selectMyselfOfLxqd.do";
    }

    //退出登录
    @RequestMapping("userLogout.do")
    public String logout(HttpSession session) {

        Students students = (Students) session.getAttribute("student");
        if (students != null){
            ServletContext application = session.getServletContext();
            Map<String,Object> logListMap = (Map<String, Object>) application.getAttribute("logListMap");
            logListMap.remove(students.getStudid());
            application.setAttribute("logListMap",logListMap);
            session.removeAttribute("user");
            //清队session
            session.invalidate();
        }
        return "userLogin.do";
    }

    /**
     * 学生个人信息管理
     */
    //查询个人信息
    //查询数据库中个人信息
    @RequestMapping(value = "selectMyselfOfStudent.do", produces = "text/html;charset=utf-8")
    @ResponseBody
    public ModelAndView doSelectMyselfOfStudent(String studid, HttpSession session) {

        ModelAndView modelAndView = new ModelAndView();
        Students student1 = (Students) session.getAttribute("student");
        Students students = studentsService.selectByStudId(student1.getStudid());

        modelAndView.addObject("MySelfOfstudents", students);
        modelAndView.setViewName("/students/lookMyselfOfStudent.jsp");
        return modelAndView;
    }

    //修改数据库中个人信息
    @RequestMapping(value = "updateMyselfOfStudent.do", produces = "text/html;charset=utf-8")
    public String doUpdateMyself(Students students, HttpSession session) {

        Students students1 = studentsService.selectByStudId(students.getStudid());
        students.setStudpass(null);
        students.setDepartId(students1.getDepartId());
        studentsService.updateByStuIdSelective(students);



        return "selectMyselfOfStudent.do";
    }

    @RequestMapping(value = "UploadStudentsPhoto.do", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String doUploadStudentsPhoto(MultipartFile userphoto, HttpSession session, HttpServletRequest request) throws Exception {

        String path = "/img/userPhoto";
        int index = userphoto.getOriginalFilename().lastIndexOf('.');
        String studid = userphoto.getOriginalFilename().substring(0, index);
        Students students = (Students) session.getAttribute("student");

        if (studid.equals(students.getStudid())){
            String photoPath = PhotoUtils.uploadUserPhoto(request, path, userphoto);
            if (photoPath != null) {
                students.setStudphoto(photoPath);
                studentsService.updateByStuIdSelective(students);
                return "upload_ok";
            } else {
                return "upload_no";
            }
        }else{
            return "no_studid";
        }
    }

    /**
     * 查看个人离校清单
     */
    //查看数据库中离校清单
    @RequestMapping(value = "selectMyselfOfLxqd.do", produces = "text/html;charset=utf-8")
    @ResponseBody
    public ModelAndView doSelectMyselfOfLxqd(HttpSession session, @RequestParam(defaultValue = "0") String downloadflag) throws IOException {

        ModelAndView modelAndView = new ModelAndView();

        Students students = (Students) session.getAttribute("student");
        String studid = students.getStudid();
        Suppcent suppcent = suppcentService.simpleSelectByStudId(students.getStudid());
        if (suppcent.getAdminId() != null) {
            suppcent.setAdmin(adminService.selectByAdminId(suppcent.getAdminId()));
        }
        Dormmana dormmana = dormmanaService.selectDormmanaByStudId(studid);
        if (dormmana.getAdminId() != null) {
            dormmana.setAdmin(adminService.selectByAdminId(dormmana.getAdminId()));
        }
        Studaffadivi studaffadivi = studaffadiviService.selectByStudId(studid);
        if (studaffadivi.getAdminId() != null) {
            studaffadivi.setAdmin(adminService.selectByAdminId(studaffadivi.getAdminId()));
        }
        Hydropower hydropower = hydropowerService.selectByStudId(studid);
        if (hydropower.getAdminId() != null) {
            hydropower.setAdmin(adminService.selectByAdminId(hydropower.getAdminId()));
        }
        Drinkwater drinkwater = drinkwaterService.selectByStudId(studid);
        if (drinkwater.getAdminId() != null) {
            drinkwater.setAdmin(adminService.selectByAdminId(drinkwater.getAdminId()));
        }
        Logimana logimana = logimanaService.selectByStudId(studid);
        if (logimana.getAdminId() != null) {
            logimana.setAdmin(adminService.selectByAdminId(logimana.getAdminId()));
        }
        Library library = libraryService.selectByStudId(studid);
        if (library.getAdminId() != null) {
            library.setAdmin(adminService.selectByAdminId(library.getAdminId()));
        }
        if (library.getSealer() != null) {
            library.setAdmin1(adminService.selectByAdminId(library.getSealer()));
        }
        Hotwater1 hotwater1 = hotwater1Service.selectByStudId(studid);
        if (hotwater1.getAdminId() != null) {
            hotwater1.setAdmin(adminService.selectByAdminId(hotwater1.getAdminId()));
        }
        if (hotwater1.getSealer() != null) {
            hotwater1.setAdmin1(adminService.selectByAdminId(hotwater1.getSealer()));
        }
        Hotwater2 hotwater2 = hotwater2Service.selectByStudId(studid);
        if (hotwater2.getAdminId() != null) {
            hotwater2.setAdmin(adminService.selectByAdminId(hotwater2.getAdminId()));
        }
        if (hotwater2.getSealer() != null) {
            hotwater2.setAdmin1(adminService.selectByAdminId(hotwater2.getSealer()));
        }
        Educandcomp educandcomp = educAndCompService.selectByStudId(studid);
        if (educandcomp.getAdminId() != null) {
            educandcomp.setAdmin(adminService.selectByAdminId(educandcomp.getAdminId()));
        }
        if (educandcomp.getSealer() != null) {
            educandcomp.setAdmin1(adminService.selectByAdminId(educandcomp.getSealer()));
        }
        Clasteacsign clasteacsign = clasTeacSignService.selectByStudId(studid);
        if (clasteacsign.getAdminId() != null) {
            clasteacsign.setAdmin(adminService.selectByAdminId(clasteacsign.getAdminId()));
        }
        Acadsign acadsign = acadsignService.selectByStudId(studid);
        if (acadsign.getAdminId() != null) {
            acadsign.setAdmin(adminService.selectByAdminId(acadsign.getAdminId()));
        }
        if (acadsign.getSealer() != null) {
            acadsign.setAdmin1(adminService.selectByAdminId(acadsign.getSealer()));
        }

        List<Lxqd> lxqd = lxqdService.selectAll();
        for (Lxqd lxqd1 : lxqd) {
            students.setLxqd(lxqd1);
        }
        students.setSuppcent(suppcent);
        students.setDormmana(dormmana);
        students.setStudaffadivi(studaffadivi);
        students.setHydropower(hydropower);
        students.setDrinkwater(drinkwater);
        students.setLogimana(logimana);
        students.setLibrary(library);
        students.setHotwater1(hotwater1);
        students.setHotwater2(hotwater2);
        students.setEducandcomp(educandcomp);
        students.setClasteacsign(clasteacsign);
        students.setAcadsign(acadsign);


        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(students);
        modelAndView.addObject("students1", jsonObject);
        if (!downloadflag.equals("0")) {
            modelAndView.setViewName("/students/DLMyLxqd.jsp");
        } else {
            modelAndView.setViewName("/students/lookMyLxqd.jsp");
        }
        return modelAndView;
    }

    //查看数据库中离校流程
    @RequestMapping(value = "selectProcedure.do", produces = "text/html;charset=utf-8")
    @ResponseBody
    public ModelAndView doSelectProcedure(HttpSession session) throws ParseException {

        ModelAndView modelAndView = new ModelAndView();
        Map<String,Date> parameterMap = new HashMap<String, Date>();

        Students students = (Students) session.getAttribute("student");
        String studgrade = students.getGrade().substring(0,2);
        int endYear = Calendar.getInstance().get(Calendar.YEAR);
        if (students.getEducation().equals("本") || students.getEducation().equals("本科")) {
            endYear = Integer.valueOf(studgrade) + 4;
        } else if (students.getEducation().equals("专") || students.getEducation().equals("专科")) {
            endYear = Integer.valueOf(studgrade) + 3;
        }
        String start =  "20" + studgrade + "-" + "09-01";
        String end = "20" + endYear + "-" + "09-01";
        Date starttime = new SimpleDateFormat("yyyy-MM-dd").parse(start);
        Date endtime = new SimpleDateFormat("yyyy-MM-dd").parse(end);

        parameterMap.put("starttime",starttime);
        parameterMap.put("endtime",endtime);
        List<Procedure> procedureList = procedureService.selectProcedureByStartAndEnd(parameterMap);

        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(procedureList);
        System.out.println("***---///+++"+jsonArray);
        modelAndView.addObject("procedureList",jsonArray);

        modelAndView.setViewName("/students/procedure.jsp");
        return modelAndView;
    }
//    //修改数据库中个人信息
//    @RequestMapping(value = "downloadMyselfOfLxqd.do", produces = "text/html;charset=utf-8")
//    @ResponseBody
//    public ModelAndView doDownloadMyselfOfLxqd(HttpSession session) throws IOException {
//
//        ModelAndView modelAndView = new ModelAndView();
//
////        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(students);
//
//
//        modelAndView.setViewName("/students/lookMyLxqd.jsp");
//
//        return modelAndView;
//    }


//    /**
//     *
//     * @Title: generateRSAKey
//     * @Description: 生成公钥和私钥
//     * @param username
//     * @return
//     * @date 2018年2月5日 下午4:25:05
//     * @author p7
//     */
//
//    @ResponseBody
//    @GetMapping(value = "/rsaKey/{username}")
//    public ResultBean generateRSAKey(@PathVariable String username){
//
//        try{
//            // 获取公钥和私钥
//            HashMap<String, Object> keys=RSAUtils.getKeys();
//            RSAPublicKey publicKey=(RSAPublicKey)keys.get("public");
//            RSAPrivateKey privateKey=(RSAPrivateKey)keys.get("private");
//            // 保存私钥到 redis，也可以保存到数据库
//            boolean res=redisService.set(username,privateKey);
//            if(!res){
//                throw new BusinessLogicException("redis 保存失败");
//            }
//            // 将公钥传到前端
//            Map<String, String> map=new HashMap<String, String>();
//            // 注意返回modulus和exponent以16为基数的BigInteger的字符串表示形式
//            map.put("modulus",publicKey.getModulus().toString(16));
//            map.put("exponent",publicKey.getPublicExponent().toString(16));
//
//            return new ResultBean(map);
//        }catch(NoSuchAlgorithmException e){
//            return new ResultBean(ResultBean.ERROR,e.getMessage());
//        }catch(BusinessLogicException e){
//            return new ResultBean(ResultBean.ERROR,e.getMessage());
//        }
//    }
//
//    /**
//     *
//     * @Title: checkRSAKey
//     * @Description: 验证密码
//     * @param username
//     * @param password
//     * @return
//     * @date 2018年2月5日 下午4:25:43
//     * @author p7
//     */
//    @ResponseBody
//    @GetMapping(value = "/rsaKey/{username}/{password}")
//    public ResultBean checkRSAKey(@PathVariable String username,@PathVariable String password){
//        Object object=redisService.get(username);
//        try{
//            // 解密
//            String decryptByPrivateKey=RSAUtils.decryptByPrivateKey(password,(RSAPrivateKey)object);
//            return new ResultBean(decryptByPrivateKey);
//        }catch(Exception e){
//            return new ResultBean(ResultBean.ERROR,"解密失败");
//        }
//    }

}
