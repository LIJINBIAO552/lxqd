package zqu.lxqd.controller;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import zqu.lxqd.bean.*;
import zqu.lxqd.service.*;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ljb
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    @Qualifier("AdminService")
    private AdminService adminService;

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

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
    @Qualifier("LxqdService")
    private LxqdService lxqdService;

    public void setLxqdService(LxqdService lxqdService) {
        this.lxqdService = lxqdService;
    }

    @RequestMapping(value = "adminLogin.do", method = RequestMethod.GET)
    public String toLogin() {
        return "redirect:adminLogin.jsp";
    }

    @RequestMapping(value = "adminLogin.do", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String doLogin(String adminid, String adminPass, String checkRemem,
//                          String captcha,
                          HttpSession session, HttpServletResponse response) {
        // 获取Kaptcha jar包里面的KAPTCHA_SESSION_KEY
//        String trueCaptcha = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
//        if(trueCaptcha.toLowerCase().equals(captcha.toLowerCase())) {
        // 判断输入的账号密码是否为空
        if (adminid != null && adminPass != null) {
            //限制用户登录
            ServletContext application = session.getServletContext();
            Map<String,Object> logListMap = (Map<String, Object>) application.getAttribute("logListMap");
            if (logListMap == null){
                logListMap = new HashMap<String, Object>();
            }else {
                for (String logid : logListMap.keySet()) {
                    System.out.println("123132..."+logid);
                    System.out.println("213131///"+adminid);
                    if (adminid.equals(logid)){
                        System.out.println("xiangtong...");
                        if (session.getId().equals(logListMap.get(logid))){
                            System.out.println("2222");
                            return "该用户已经登录,请别重复登录!";
                        }else {
                            System.out.println("11111");
                            return "该用户已在别处登录，请先退出登录";
                        }
                    }
                }
            }
            //定义字符串类型变量来接收数据库查到的密码
            String apass = null;
            //从数据库中按id查询相应的用户
            Admin admin = adminService.selectByAdminId(adminid);
//            判断是否有用户存在，若存在则获取数据库中的密码
            if (admin != null) {
                apass = admin.getAdminpass();
            } else {
                return "用户不存在，请从新输入用户！";
            }
            //通过判断用户输入的密码是否与数据库中的密码相同，相同则存进session中，不同则返回错误信息
            if (adminPass.equals(apass)) {

                logListMap.put(adminid,session.getId());
                application.setAttribute("logListMap",logListMap);
                //将用户存进session中
                session.setAttribute("admin", admin);
//                判断用户是否选择记住密码
                if (checkRemem != null) {
//                  创建两个Cookie对象
                    Cookie adminIdCookie = new Cookie("AdminId", adminid);
                    Cookie adminPassCookie = new Cookie("AdminPassword", adminPass);
//                    设置Cookies有效期为7天
                    adminIdCookie.setMaxAge(60 * 60 * 24 * 7);
                    adminPassCookie.setMaxAge(60 * 60 * 24 * 7);

                    response.addCookie(adminIdCookie);
                    response.addCookie(adminPassCookie);
                }
                if (admin.getDepartid().equals("1")) {
                    return "admin/SuppCent/SuppCentMain.do";
                } else if (admin.getDepartid().equals("2")) {
                    return "admin/DormMana/DormManaMain.do";
                } else if (admin.getDepartid().equals("3")) {
                    return "admin/StudAffaDivi/StudAffaDiviMain.do";
                } else if (admin.getDepartid().equals("4")) {
                    return "admin/Hydropower/HydropowerMain.do";
                } else if (admin.getDepartid().equals("5")) {
                    return "admin/DrinkWater/DrinkWaterMain.do";
                } else if (admin.getDepartid().equals("6")) {
                    return "admin/LogiMana/LogiManaMain.do";
                } else if (admin.getDepartid().equals("7")) {
                    return "admin/Library/LibraryMain.do";
                } else if (admin.getDepartid().equals("8")) {
                    return "admin/HotWater1/HotWater1Main.do";
                } else if (admin.getDepartid().equals("9")) {
                    return "admin/HotWater2/HotWater2Main.do";
                } else if (admin.getDepartid().equals("10")) {
                    return "admin/EducAndComp/EducAndCompMain.do";
                } else if (admin.getDepartid().equals("11")) {
                    return "admin/ClasTeacSign/ClasTeacSignMain.do";
                } else if (admin.getDepartid().equals("20")) {
                    return "admin/Acadsign/AcadSignMain.do";
                } else if (admin.getDepartid().equals("100")) {
                    return "admin/adminMain.do";
                } else {
                    return "admin/adminLogin.jsp";
                }
            } else {
                return "用户名或密码错误，请重新登录！";
            }
        } else {
            return "用户名和密码不能为空，请重新输入!";
        }
    }
//        else {
//            return "验证码错误，请重新输入！";
//        }
//
//    }

    //    跳转到主界面
    @RequestMapping("adminMain.do")
    public String toMain() {
        return "forward:/admin/adminMain.jsp";
    }

    //退出登录
    @RequestMapping("adminLogout.do")
    public String logout(HttpSession session) {

        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null){
            ServletContext application = session.getServletContext();
            Map<String,Object> logListMap = (Map<String, Object>) application.getAttribute("logListMap");
            logListMap.remove(admin.getAdminid());
            application.setAttribute("logListMap",logListMap);
            session.removeAttribute("admin");
            //清队session
            session.invalidate();
        }

        return "adminLogin.do";
    }


    //通过本地.xls文件将管理员信息导入数据库
    @RequestMapping(value = "/ImportAdminToDB.do")
    public @ResponseBody
    Map<String, Object> doImportAdminToDB(MultipartFile file, HttpSession session) throws IOException {

        Map<String, Object> result = new HashMap<String, Object>();
        //获取文件名
        String filename = file.getOriginalFilename();
        int index = filename.lastIndexOf(".");
        String suffix = filename.substring(index + 1, filename.length());
        //判断是否为excel文件
        if (suffix.equals("xls") || suffix.equals("xlsx")) {
            CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) file;
            DiskFileItem diskFileItem = (DiskFileItem) commonsMultipartFile.getFileItem();
            File files = diskFileItem.getStoreLocation();

            Map<String, Integer> importresult = importAdminToDB(files);
            result.put("code", 0);
            result.put("msg", "成功导入了" + importresult.get("insertCount") + "位新的管理员，并且更新了" + importresult.get("updateCount") + "位管理员的信息");
            return result;
        } else {
            result.put("code", 1);
            result.put("msg", "该文件不是excel文件，请重新选择！");
            return result;
        }
    }

    //将管理员信息导入数据库
    public Map<String,Integer> importAdminToDB(File file) {
        Map<String,Integer> importInfo = new HashMap<String,Integer>();
        int insertCount = 0;
        int updateCount = 0;
        //获取excel文件表格中所有的数据
        List<Admin> listExcel = getAllAdminByExcel(file);
        for (Admin admin : listExcel) {
            String id = admin.getAdminid();
            if (adminService.selectByAdminId(id) != null) {
                admin.setAdminpass(null);
                adminService.updateByAdminIdSelective(admin);
                updateCount++;
            } else {
                adminService.insertOfSelective(admin);
                insertCount++;
            }
        }
        importInfo.put("insertCount",insertCount);
        importInfo.put("updateCount",updateCount);
        return importInfo;
    }

    //获取管理员excel文件表格中所有的数据
    private List<Admin> getAllAdminByExcel(File file) {
        List<Admin> list = new ArrayList<Admin>();
        try {
            Workbook workbook = Workbook.getWorkbook(file);
            //获取sheet1
            Sheet sheet1 = workbook.getSheet(0);
            int cols = sheet1.getColumns();
            int rows = sheet1.getRows();
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    String id = sheet1.getCell(j++, i).getContents();
                    String pass = sheet1.getCell(j++, i).getContents();
                    String name = sheet1.getCell(j++, i).getContents();
                    String sex = sheet1.getCell(j++, i).getContents();
                    String departName = sheet1.getCell(j++, i).getContents();
                    String Iden = sheet1.getCell(j++, i).getContents();
                    String phone = sheet1.getCell(j++, i).getContents();
                    String mail = sheet1.getCell(j++, i).getContents();

                    Department department = departmentService.selectByDepartmentName(departName);
                    String departId = department.getDepartid();

                    list.add(new Admin(id, pass, name, sex, departId, department, Iden, phone, mail));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //导入学生数据
    //将学生信息导入数据库
    public Map<String,Integer> importStudentsToDB(File file) {
        Map<String,Integer> importInfo = new HashMap<String,Integer>();
        int insertCount = 0;
        int updateCount = 0;
        //获取excel文件表格中所有的数据
        List<Students> listExcel = getAllStudentsByExcel(file);
        for (Students students : listExcel) {
            String id = students.getStudid();
            if (studentsService.selectByStudId(id) != null) {
                students.setStudpass(null);
                studentsService.updateByStuIdSelective(students);
                updateCount++;
            } else {
                studentsService.insertByStudents(students);
                insertCount++;
            }
        }
        importInfo.put("insertCount",insertCount);
        importInfo.put("updateCount",updateCount);

        return importInfo;
    }

    //获取学生的excel文件表格中所有的数据
    private List<Students> getAllStudentsByExcel(File file) {
        List<Students> list = new ArrayList<Students>();
        try {
            Workbook workbook = Workbook.getWorkbook(file);
            //获取sheet1
            Sheet sheet1 = workbook.getSheet(0);
            int cols = sheet1.getColumns();
            int rows = sheet1.getRows();
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    String studId = sheet1.getCell(j++, i).getContents();
                    String studpass = sheet1.getCell(j++, i).getContents();
                    String departName = sheet1.getCell(j++, i).getContents();
                    String major = sheet1.getCell(j++, i).getContents();
                    String sclass = sheet1.getCell(j++, i).getContents();
                    String grade = sheet1.getCell(j++, i).getContents();
                    String education = sheet1.getCell(j++, i).getContents();
                    String studName = sheet1.getCell(j++, i).getContents();
                    String studSex = sheet1.getCell(j++, i).getContents();
                    String studAge = sheet1.getCell(j++, i).getContents();
                    String studIden = sheet1.getCell(j++, i).getContents();
                    String dormitory = sheet1.getCell(j++, i).getContents();
                    String studPhone1 = sheet1.getCell(j++, i).getContents();
                    String studPhone2 = sheet1.getCell(j++, i).getContents();
                    String birthplace = sheet1.getCell(j++, i).getContents();
                    String adress = sheet1.getCell(j++, i).getContents();
                    String familyPhone = sheet1.getCell(j++, i).getContents();
                    String remark = sheet1.getCell(j++, i).getContents();

                    Department department = departmentService.selectByDepartmentName(departName);
                    String departId = department.getDepartid();
                    list.add(new Students(studId, studpass, departId, major, sclass, grade, education, studName,
                            studSex, studAge, studIden, dormitory, studPhone1,
                            studPhone2, birthplace, adress, familyPhone, remark));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //将管理员的基本信息从数据库以*.xls格式导出到本地文件
    @RequestMapping(value = "/ExportAdminFromDB.do", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String doExportAdminFromDB(String filePath, HttpSession session) throws IOException {

        String fileDir = null;
        if (filePath.equals("123")) {
            fileDir = "D:\\lxqd\\download\\admin";
            filePath = fileDir + "\\"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "" + (int) Math.random() * 1000 + ".xls";
        }
        if (!new File(fileDir).exists()) {
            new File(fileDir).mkdir();
        }
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        if (exportAdminFromDB(file)) {
            return filePath;
        }else {
            return "1";
        }
    }

    //通过本地.xls文件将学生信息导入数据库
    @RequestMapping(value = "/ImportStudentsToDB.do")
    public @ResponseBody Map<String, Object> doImportStudentsToDB(MultipartFile file, HttpSession session) throws IOException {

        Map<String, Object> result = new HashMap<String, Object>();
        //获取文件名
        String filename = file.getOriginalFilename();
        int index = filename.lastIndexOf(".");
        String suffix = filename.substring(index + 1, filename.length());
        //判断是否为excel文件
        if (suffix.equals("xls") || suffix.equals("xlsx")) {
            CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) file;
            DiskFileItem diskFileItem = (DiskFileItem) commonsMultipartFile.getFileItem();
            File files = diskFileItem.getStoreLocation();
            Map<String, Integer> importresult = importStudentsToDB(files);
            result.put("code", 0);
            result.put("msg", "成功导入了" + importresult.get("insertCount") + "位新的管理员，并且更新了" + importresult.get("updateCount") + "位管理员的信息");
            return result;
        } else {
            result.put("code", 1);
            result.put("msg", "该文件不是excel文件，请重新选择！");
            return result;
        }
    }

    //将学生的基本信息从数据库以*.xls格式导出到本地文件
    @RequestMapping(value = "/ExportStudentsFromDB.do", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String doExportStudentsFromDB(String filePath, HttpSession session) throws IOException {

        String fileDir = null;
        if (filePath.equals("123")) {
            fileDir = "D:\\lxqd\\download\\students";
            filePath = fileDir + "\\" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "" + (int) Math.random() * 1000 + ".xls";
        }
        if(!new File(fileDir).exists()){
            new File(fileDir).mkdir();
        }
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        if (exportStudentsFromDB(file)) {
            return filePath;
        }
        return "1";
    }


    //导出管理员信息到本地文件
    private boolean exportAdminFromDB(File file) {
        try {
            //初始化定义一个workbook
            WritableWorkbook writableWorkbook = null;
            //创建可写入excel的工作簿
//            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            //以filename为文件名创建一个workbook
            writableWorkbook = Workbook.createWorkbook(file);
            //创建工作表
            WritableSheet writableSheet = writableWorkbook.createSheet("Sheet1", 0);

            //查询数据库获取管理员表中所有数据
            List<Admin> adminList = adminService.selectAllAdmin();
            //要插入到二线
            Label labelid = new Label(0, 0, "工号");
            Label labelpass = new Label(1, 0, "密码");
            Label labelName = new Label(2, 0, "姓名");
            Label labelSex = new Label(3, 0, "性别");
            Label labeldepartName = new Label(4, 0, "部门名称");
            Label labelIden = new Label(5, 0, "身份证号码");
            Label labelPhone = new Label(6, 0, "联系号码");
            Label labelMail = new Label(7, 0, "邮箱");

            writableSheet.addCell(labelid);
            writableSheet.addCell(labelName);
            writableSheet.addCell(labelSex);
            writableSheet.addCell(labelpass);
            writableSheet.addCell(labeldepartName);
            writableSheet.addCell(labelIden);
            writableSheet.addCell(labelPhone);
            writableSheet.addCell(labelMail);

            for (int i = 0; i < adminList.size(); i++) {
                Label labelid_i = new Label(0, i + 1, adminList.get(i).getAdminid());
                Label labelpass_i = new Label(1, i + 1, adminList.get(i).getAdminpass());
                Label labelName_i = new Label(2, i + 1, adminList.get(i).getAdminname());
                Label labelSex_i = new Label(3, i + 1, adminList.get(i).getAdminsex());
                Label labeldepartName_i = new Label(4, i + 1, adminList.get(i).getDepartment().getDepartname());
                Label labelIden_i = new Label(5, i + 1, adminList.get(i).getAdminiden());
                Label labelPhone_i = new Label(6, i + 1, adminList.get(i).getAdminphone());
                Label labelMail_i = new Label(7, i + 1, adminList.get(i).getAdminmail());

                writableSheet.addCell(labelid_i);
                writableSheet.addCell(labelName_i);
                writableSheet.addCell(labelSex_i);
                writableSheet.addCell(labelpass_i);
                writableSheet.addCell(labeldepartName_i);
                writableSheet.addCell(labelIden_i);
                writableSheet.addCell(labelPhone_i);
                writableSheet.addCell(labelMail_i);
            }
            //写进文档
            writableWorkbook.write();
            //关闭excel工作簿对象
            writableWorkbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    //导出学生信息到本地文件
    private boolean exportStudentsFromDB(File file) {
        try {
            System.out.println("exportStudentsFromDB..." + file);
            //初始化定义一个workbook
            WritableWorkbook writableWorkbook = null;
            //创建可写入excel的工作簿
//            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            //以filename为文件名创建一个workbook
            writableWorkbook = Workbook.createWorkbook(file);
            //创建工作表
            WritableSheet writableSheet = writableWorkbook.createSheet("Sheet1", 0);

            //查询数据库获取管理员表中所有数据
            List<Students> studentsList = studentsService.selectAllStudent();
            //要插入到二线
            Label labelStudId = new Label(0, 0, "学号");
            Label labelStudpass = new Label(1, 0, "密码");
            Label labeldepartName = new Label(2, 0, "学院");
            Label labelMajor = new Label(3, 0, "专业");
            Label labelSclass = new Label(4, 0, "班级");
            Label labelGrade = new Label(5, 0, "年级");
            Label labelEducation = new Label(6, 0, "专/本");
            Label labelStudName = new Label(7, 0, "姓名");
            Label labelStudSex = new Label(8, 0, "性别");
            Label labelStudAge = new Label(9, 0, "年龄");
            Label labelStudIden = new Label(10, 0, "身份证号码");
            Label labelDormitory = new Label(11, 0, "住宿");
            Label labelStudPhone1 = new Label(12, 0, "个人联系电话(长号)");
            Label labelStudPhone2 = new Label(13, 0, "短号");
            Label labelBirthplace = new Label(14, 0, "籍贯");
            Label labelAdress = new Label(15, 0, "家庭详细地址");
            Label labelFamilyPhone = new Label(16, 0, "家庭联系电话");
            Label labelRemark = new Label(17, 0, "备注");

            writableSheet.addCell(labelStudId);
            writableSheet.addCell(labelStudpass);
            writableSheet.addCell(labeldepartName);
            writableSheet.addCell(labelMajor);
            writableSheet.addCell(labelSclass);
            writableSheet.addCell(labelGrade);
            writableSheet.addCell(labelEducation);
            writableSheet.addCell(labelStudName);
            writableSheet.addCell(labelStudSex);
            writableSheet.addCell(labelStudAge);
            writableSheet.addCell(labelStudIden);
            writableSheet.addCell(labelDormitory);
            writableSheet.addCell(labelStudPhone1);
            writableSheet.addCell(labelStudPhone2);
            writableSheet.addCell(labelBirthplace);
            writableSheet.addCell(labelAdress);
            writableSheet.addCell(labelFamilyPhone);
            writableSheet.addCell(labelRemark);

            for (int i = 0; i < studentsList.size(); i++) {
                Label labelStudId_i = new Label(0, i + 1, studentsList.get(i).getStudid());
                Label labelStudpass_i = new Label(1, i + 1, studentsList.get(i).getStudpass());
                Label labeldepartName_i = new Label(2, i + 1, studentsList.get(i).getDepartment().getDepartname());
                Label labelMajor_i = new Label(3, i + 1, studentsList.get(i).getMajor());
                Label labelSclass_i = new Label(4, i + 1, studentsList.get(i).getSclass());
                Label labelGrade_i = new Label(5, i + 1, studentsList.get(i).getGrade());
                Label labelEducation_i = new Label(6, i + 1, studentsList.get(i).getEducation());
                Label labelStudName_i = new Label(7, i + 1, studentsList.get(i).getStudname());
                Label labelStudSex_i = new Label(8, i + 1, studentsList.get(i).getStudsex());
                Label labelStudAge_i = new Label(9, i + 1, studentsList.get(i).getStudage());
                Label labelStudIden_i = new Label(10, i + 1, studentsList.get(i).getStudiden());
                Label labelDormitory_i = new Label(11, i + 1, studentsList.get(i).getDormitory());
                Label labelStudPhone1_i = new Label(12, i + 1, studentsList.get(i).getStudphone1());
                Label labelStudPhone2_i = new Label(13, i + 1, studentsList.get(i).getStudphone2());
                Label labelBirthplace_i = new Label(14, i + 1, studentsList.get(i).getBirthplace());
                Label labelAdress_i = new Label(15, i + 1, studentsList.get(i).getAdress());
                Label labelFamilyPhone_i = new Label(16, i + 1, studentsList.get(i).getFamilyphone());
                Label labelRemark_i = new Label(17, i + 1, studentsList.get(i).getRemark());

                writableSheet.addCell(labelStudId_i);
                writableSheet.addCell(labelStudpass_i);
                writableSheet.addCell(labeldepartName_i);
                writableSheet.addCell(labelMajor_i);
                writableSheet.addCell(labelSclass_i);
                writableSheet.addCell(labelGrade_i);
                writableSheet.addCell(labelEducation_i);
                writableSheet.addCell(labelStudName_i);
                writableSheet.addCell(labelStudSex_i);
                writableSheet.addCell(labelStudAge_i);
                writableSheet.addCell(labelStudIden_i);
                writableSheet.addCell(labelDormitory_i);
                writableSheet.addCell(labelStudPhone1_i);
                writableSheet.addCell(labelStudPhone2_i);
                writableSheet.addCell(labelBirthplace_i);
                writableSheet.addCell(labelAdress_i);
                writableSheet.addCell(labelFamilyPhone_i);
                writableSheet.addCell(labelRemark_i);
            }
            //写进文档
            writableWorkbook.write();
            //关闭excel工作簿对象
            writableWorkbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 管理人员的个人信息管理
     */
    //查询数据库中个人信息
    @RequestMapping(value = "selectMyselfOfAdmin.do", produces = "text/html;charset=utf-8")
    public String doSelectMyselfOfAdmin(String adminid, HttpSession session) {

        Admin admin1 = (Admin) session.getAttribute("admin");
        Admin admin_result = (Admin) adminService.selectByAdminId(admin1.getAdminid());
        session.setAttribute("MySelfOfAdmin", admin_result);

        return "redirect:/admin/adminEdit.jsp";
    }

    //修改数据库中个人信息
    @RequestMapping(value = "/updateMyselfOfAdmin.do", produces = "text/html;charset=utf-8")
    public String doUpdateMyselfOfAdmin(Admin admin, HttpSession session) {

        if ( adminService.updateByAdminId(admin) >= 0){
            session.setAttribute("admin",admin);
        }
        return "redirect:/admin/lookMyselfOfAdmin.jsp";
    }

    //修改个人密码
    @RequestMapping(value = "updateMyselfOfPW.do", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String doUpdateMyselfOfPW(String oldadminpass, String adminpass, HttpSession session) {

        System.out.println("11135555555555555555555555555555555555555");
        System.out.println(adminpass + ".........doupdatemy......." + oldadminpass);
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin.getAdminpass().equals(oldadminpass)) {
            admin.setAdminpass(adminpass);
            adminService.updateByAdminId(admin);
            return "1";
        } else {
            return "0";
        }
    }


}
