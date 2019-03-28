package zqu.lxqd.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import zqu.lxqd.bean.*;
import zqu.lxqd.service.*;
import zqu.lxqd.utils.PhotoUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ljb
 */
@Controller
@RequestMapping("admin")
public class SuperAdminController {

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

    @Autowired
    @Qualifier("AcadsignService")
    private AcadsignService acadsignService;
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

    public AdminService getAdminService() {
        return adminService;
    }

    public StudentsService getStudentsService() {
        return studentsService;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public AcadsignService getAcadsignService() {
        return acadsignService;
    }

    public void setAcadsignService(AcadsignService acadsignService) {
        this.acadsignService = acadsignService;
    }

    public ClasTeacSignService getClasTeacSignService() {
        return clasTeacSignService;
    }

    public void setClasTeacSignService(ClasTeacSignService clasTeacSignService) {
        this.clasTeacSignService = clasTeacSignService;
    }

    public DormmanaService getDormmanaService() {
        return dormmanaService;
    }

    public void setDormmanaService(DormmanaService dormmanaService) {
        this.dormmanaService = dormmanaService;
    }

    public DrinkwaterService getDrinkwaterService() {
        return drinkwaterService;
    }

    public void setDrinkwaterService(DrinkwaterService drinkwaterService) {
        this.drinkwaterService = drinkwaterService;
    }

    public EducAndCompService getEducAndCompService() {
        return educAndCompService;
    }

    public void setEducAndCompService(EducAndCompService educAndCompService) {
        this.educAndCompService = educAndCompService;
    }

    public Hotwater1Service getHotwater1Service() {
        return hotwater1Service;
    }

    public void setHotwater1Service(Hotwater1Service hotwater1Service) {
        this.hotwater1Service = hotwater1Service;
    }

    public Hotwater2Service getHotwater2Service() {
        return hotwater2Service;
    }

    public void setHotwater2Service(Hotwater2Service hotwater2Service) {
        this.hotwater2Service = hotwater2Service;
    }

    public HydropowerService getHydropowerService() {
        return hydropowerService;
    }

    public void setHydropowerService(HydropowerService hydropowerService) {
        this.hydropowerService = hydropowerService;
    }

    public LibraryService getLibraryService() {
        return libraryService;
    }

    public void setLibraryService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public LogimanaService getLogimanaService() {
        return logimanaService;
    }

    public void setLogimanaService(LogimanaService logimanaService) {
        this.logimanaService = logimanaService;
    }

    public LxqdService getLxqdService() {
        return lxqdService;
    }

    public void setLxqdService(LxqdService lxqdService) {
        this.lxqdService = lxqdService;
    }

    public StudaffadiviService getStudaffadiviService() {
        return studaffadiviService;
    }

    public void setStudaffadiviService(StudaffadiviService studaffadiviService) {
        this.studaffadiviService = studaffadiviService;
    }

    public SuppcentService getSuppcentService() {
        return suppcentService;
    }

    public void setSuppcentService(SuppcentService suppcentService) {
        this.suppcentService = suppcentService;
    }

    //查询数据库中管理员的个人信息
    @RequestMapping(value = "selectAllAdminBylimit.do")
    public @ResponseBody
    Map<String, Object> doSelectAllAdminBylimit(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit,
                                                @RequestParam(value = "adminid", defaultValue = "null") String adminid, HttpSession session) {

        Map<String, Object> result = new HashMap<String, Object>();
        page = (page - 1) * limit;
        System.out.println(page + "+++++++++" + limit);
        Map<String, Integer> parmNum = new HashMap<String, Integer>();
        parmNum.put("page", page);
        parmNum.put("limit", limit);
        System.out.println("+++...+++" + adminid);
        if (adminid.equals("null")) {
            List<Admin> adminList = adminService.selectAllAdminBylimits(parmNum);
            Integer count = adminService.countAllAdmin();
            result.put("count", count);
            JSONArray jsonArray = (JSONArray) JSONArray.toJSON(adminList);
            result.put("data", jsonArray);
        } else {
            List<Admin> adminList = adminService.selectLikeAdminId(adminid);
            result.put("count", adminList.size());
            JSONArray jsonArray = (JSONArray) JSONArray.toJSON(adminList);
            result.put("data", jsonArray);
        }
        result.put("code", 0);
        result.put("msg", "请求成功");
        return result;
    }

    //更新数据库中管理员的个人信息
    @RequestMapping(value = "updateAdmin.do")
    public @ResponseBody
    Map<String, Object> doUpdateAdmin(Admin admin, HttpSession session) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 0);
        if (adminService.updateByAdminIdSelective(admin) > 0) {
            result.put("msg", "1");
        } else {
            result.put("msg", "0");
        }
        return result;
    }

    //删除数据库中管理员的个人信息
    @RequestMapping(value = "deleteAdmin.do")
    public @ResponseBody
    Map<String, Object> doDeleteAdmin(String adminid, HttpSession session) {
        Map<String, Object> result = new HashMap<String, Object>();

        result.put("code", 0);
        if (adminService.deleteByAdminId(adminid) > 0) {
            result.put("msg", "1");
        } else {
            result.put("msg", "0");
        }
        return result;
    }

    //添加数据库中管理员的个人信息
    @RequestMapping(value = "AddAdmin.do")
    public @ResponseBody
    Map<String, Object> doAddAdmin(Admin admin, HttpSession session) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 0);
        if (adminService.selectByAdminId(admin.getAdminid()) != null) {
            result.put("msg", "该账户已存在，请重新输入");
        } else {
            if (adminService.insertOfSelective(admin) > 0) {
                result.put("msg", "1");
            } else {
                result.put("msg", "插入失败");
            }
        }
        return result;
    }

    //上传更新数据库中管理员的照片
    @RequestMapping(value = "UploadAdminPhoto.do")
    public @ResponseBody
    Map<String, Object> doUploadAdminPhoto(MultipartFile file, HttpSession session, HttpServletRequest request) throws Exception {

        String path = "/img/adminPhoto";
        Map<String, Object> result = new HashMap<String, Object>();
        int index = file.getOriginalFilename().lastIndexOf('.');
        String adminid = file.getOriginalFilename().substring(0, index);
        Admin admin = adminService.selectByAdminId(adminid);
        if (admin != null) {
            result.put("code", 0);
            String photoPath = PhotoUtils.uploadUserPhoto(request, path, file);
            if (photoPath != null) {
                Map<String, Object> srcPath = new HashMap<String, Object>();
                List<String> strings = new ArrayList<String>();
                strings.add(photoPath);
                admin.setAdminphoto(photoPath);
                if (adminService.updateByAdminIdSelective(admin) > 0) {
                    result.put("msg", "上传成功");
                    JSONArray jsonArray = (JSONArray) JSONArray.toJSON(strings);
                    srcPath.put("src", jsonArray);
                    result.put("data", srcPath);
                    return result;
                } else {
                    result.put("code", 1);
                    result.put("msg", "上传失败");
                    return result;
                }
            } else {
                result.put("code", 1);
                result.put("msg", "上传失败");
                result.put("data", "");
                return result;
            }
        } else {
            result.put("code", 1);
            result.put("msg", "文件命名错误，找不到对应账号");
            return result;
        }
    }

    //查询数据库中管理员的个人信息
    @RequestMapping(value = "selectAllStudentsBylimit.do")
    public @ResponseBody
    Map<String, Object> doSelectAllStudentsBylimit(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit,
                                                   @RequestParam(value = "studid", defaultValue = "null") String studid, HttpSession session) {

        Map<String, Object> result = new HashMap<String, Object>();
        page = (page - 1) * limit;
        Map<String, Integer> parmNum = new HashMap<String, Integer>();
        parmNum.put("page", page);
        parmNum.put("limit", limit);
        if (studid.equals("null")) {
            List<Students> studentsList = studentsService.selectAllStudBylimits(parmNum);
            Integer count = studentsService.countAllStud();
            result.put("count", count);
            JSONArray jsonArray = (JSONArray) JSONArray.toJSON(studentsList);
            result.put("data", jsonArray);
        } else {
            List<Students> studentsList = studentsService.selectLikeStudId(studid);
            result.put("count", studentsList.size());
            JSONArray jsonArray = (JSONArray) JSONArray.toJSON(studentsList);
            result.put("data", jsonArray);
        }
        result.put("code", 0);
        result.put("msg", "请求成功");
        return result;
    }

    //更新数据库中管理员的个人信息
    @RequestMapping(value = "updateStudents.do")
    public @ResponseBody
    Map<String, Object> doUpdateStudents(Students students, HttpSession session) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 0);
        if (studentsService.updateByStuIdSelective(students) > 0) {
            result.put("msg", "1");
        } else {
            result.put("msg", "0");
        }
        return result;
    }

    //删除数据库中管理员的个人信息
    @RequestMapping(value = "deleteStudents.do")
    public @ResponseBody Map<String, Object> doDeleteStudents(String studid, HttpSession session) {
        Map<String, Object> result = new HashMap<String, Object>();

        result.put("code", 0);
        if (studentsService.deleteByStudId(studid) > 0) {
            result.put("msg", "1");
        } else {
            result.put("msg", "0");
        }
        return result;
    }

    //添加数据库中管理员的个人信息
    @RequestMapping(value = "AddStudents.do")
    public @ResponseBody
    Map<String, Object> doAddStudents(Students students, HttpSession session) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 0);
        if (studentsService.selectByStudId(students.getStudid()) != null) {
            result.put("msg", "该账户已存在，请重新输入");
        } else {
            if (studentsService.insertSelectiveByStudents(students) > 0) {
                result.put("msg", "1");
            } else {
                result.put("msg", "插入失败");
            }
        }
        return result;
    }

    //上传更新数据库中管理员的照片
    @RequestMapping(value = "UploadStudentsPhoto.do")
    public @ResponseBody
    Map<String, Object> doUploadStudentsPhoto(MultipartFile file, HttpSession session, HttpServletRequest request) throws Exception {

        String path = "/img/StudentsPhoto";
        Map<String, Object> result = new HashMap<String, Object>();
        int index = file.getOriginalFilename().lastIndexOf('.');
        String studid = file.getOriginalFilename().substring(0, index);
        Students students = studentsService.selectByStudId(studid);
        if (students != null) {
            result.put("code", 0);
            String photoPath = PhotoUtils.uploadUserPhoto(request, path, file);
            if (photoPath != null) {
                Map<String, Object> srcPath = new HashMap<String, Object>();
                List<String> strings = new ArrayList<String>();
                strings.add(photoPath);
                students.setStudphoto(photoPath);
                if (studentsService.updateByStuIdSelective(students) > 0) {
                    result.put("msg", "上传成功");
                    JSONArray jsonArray = (JSONArray) JSONArray.toJSON(strings);
                    srcPath.put("src", jsonArray);
                    result.put("data", srcPath);
                    return result;
                } else {
                    result.put("code", 1);
                    result.put("msg", "上传失败");
                    return result;
                }
            } else {
                result.put("code", 1);
                result.put("msg", "上传失败");
                result.put("data", "");
                return result;
            }
        } else {
            result.put("code", 1);
            result.put("msg", "文件命名错误，找不到对应账号");
            return result;
        }
    }

    //导出未完全盖章的学生
    @RequestMapping(value = "ExportAllNoPassStudents.do")
    public @ResponseBody
    Map<String, String> doExportAllNoPassStudents(String filePath, HttpSession session, HttpServletRequest request) throws Exception {

        Map<String, String> result = new HashMap<String, String>();
        String fileDir = null;
        if (filePath.equals("123")) {
            fileDir = "D:\\lxqd\\download";
            filePath = fileDir + "\\" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "" + (int) Math.random() * 1000 + ".xls";
        }
        if (!new File(fileDir).exists()) {
            new File(fileDir).mkdir();
        }
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        String resultcount = exportAllNoPassStudentsFromDB(file);
        if (resultcount.equals("0") || resultcount == null) {
            result.put("code", "1");
            result.put("msg", "出错啦。。。导不出，请主人再试一遍。");
            return result;
        } else {
            result.put("code", "0");
            result.put("msg", "成功导出" + resultcount + "位同学的信息，文件已导出至 " + filePath + "路径下了，请自行查找。");

            return result;
        }
    }

    private String exportAllNoPassStudentsFromDB(File file) {
        int count = 0;
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

            //查询数据库获取学生借用学院物品情况情况表中所有数据
            List<Acadsign> acadsignList = acadsignService.selectAllAcadsign();
            //要插入到二线
            Label labelStudId = new Label(0, 0, "学号");
            Label labeldepartName = new Label(1, 0, "学院");
            Label labelMajor = new Label(2, 0, "专业");
            Label labelSclass = new Label(3, 0, "班级");
            Label labelGrade = new Label(4, 0, "年级");
            Label labelEducation = new Label(5, 0, "专/本");
            Label labelStudName = new Label(6, 0, "姓名");
            Label labelStudSex = new Label(7, 0, "性别");
            Label labelStudPhone1 = new Label(8, 0, "个人联系电话(长号)");
            Label labelStudPhone2 = new Label(9, 0, "短号");
            Label labelAdress = new Label(10, 0, "家庭详细地址");
            Label labelFamilyPhone = new Label(11, 0, "家庭联系电话");
            Label labelItemBorrDesc = new Label(12, 0, "二级学院物品借用情况");
            Label labelsuppdesc = new Label(13, 0, "学生资助管理中心");
            Label labeldormdesc = new Label(14, 0, "学生宿舍管理中心");
            Label labelhydrpay = new Label(15, 0, "水电管理服务中心");
            Label labeldwpay = new Label(16, 0, "饮用水服务中心");
            Label labelnedesc = new Label(17, 0, "图书馆");
            Label labelhw1pay = new Label(18, 0, "宇正公司（厚德、明智、博学、力行四个书院热水服务)");
            Label labelrentdesc = new Label(19, 0, "信息中心");
            Label labelhw2pay = new Label(20, 0, "纽恩泰公司(四个书院以外其它宿舍区热水服务)");

            writableSheet.addCell(labelStudId);
            writableSheet.addCell(labeldepartName);
            writableSheet.addCell(labelMajor);
            writableSheet.addCell(labelSclass);
            writableSheet.addCell(labelGrade);
            writableSheet.addCell(labelEducation);
            writableSheet.addCell(labelStudName);
            writableSheet.addCell(labelStudSex);
            writableSheet.addCell(labelStudPhone1);
            writableSheet.addCell(labelStudPhone2);
            writableSheet.addCell(labelAdress);
            writableSheet.addCell(labelFamilyPhone);
            writableSheet.addCell(labelItemBorrDesc);
            writableSheet.addCell(labelsuppdesc);
            writableSheet.addCell(labeldormdesc);
            writableSheet.addCell(labelhydrpay);
            writableSheet.addCell(labeldwpay);
            writableSheet.addCell(labelnedesc);
            writableSheet.addCell(labelhw1pay);
            writableSheet.addCell(labelrentdesc);
            writableSheet.addCell(labelhw2pay);

            for (int i = 0; i < acadsignList.size(); i++) {
                if ((acadsignList.get(i).getSealer() == null || acadsignList.get(i).getSealer() == "")
                        || acadsignList.get(i).getLogimana().getAdmin().getAdminname() == null || acadsignList.get(i).getLogimana().getAdmin().getAdminname() == ""
                        || acadsignList.get(i).getSuppcent().getAdmin().getAdminname() == null || acadsignList.get(i).getSuppcent().getAdmin().getAdminname() == ""
                        || acadsignList.get(i).getDormmana().getAdmin().getAdminname() == null || acadsignList.get(i).getDormmana().getAdmin().getAdminname() == ""
                        || acadsignList.get(i).getHydropower().getAdmin().getAdminname() == null || acadsignList.get(i).getHydropower().getAdmin().getAdminname() == ""
                        || acadsignList.get(i).getDrinkwater().getAdmin().getAdminname() == null || acadsignList.get(i).getDrinkwater().getAdmin().getAdminname() == ""
                        || acadsignList.get(i).getStudaffadivi().getAdmin().getAdminname() == null || acadsignList.get(i).getStudaffadivi().getAdmin().getAdminname() == ""
                        || acadsignList.get(i).getHotwater1().getAdmin().getAdminname() == null || acadsignList.get(i).getHotwater1().getAdmin().getAdminname() == ""
                        || acadsignList.get(i).getLibrary().getAdmin().getAdminname() == null || acadsignList.get(i).getLibrary().getAdmin().getAdminname() == ""
                        || acadsignList.get(i).getEducandcomp().getAdmin().getAdminname() == null || acadsignList.get(i).getEducandcomp().getAdmin().getAdminname() == ""
                        || acadsignList.get(i).getHotwater2().getAdmin().getAdminname() == null || acadsignList.get(i).getHotwater2().getAdmin().getAdminname() == ""
                        || acadsignList.get(i).getClasteacsign().getAdmin().getAdminname() == null || acadsignList.get(i).getClasteacsign().getAdmin().getAdminname() == ""
                ) {

                    Label labelStudId_i = new Label(0, i + 1, acadsignList.get(i).getStudId());
                    Label labeldepartName_i = new Label(1, i + 1, acadsignList.get(i).getStudents().getDepartment().getDepartname());
                    Label labelMajor_i = new Label(2, i + 1, acadsignList.get(i).getStudents().getMajor());
                    Label labelSclass_i = new Label(3, i + 1, acadsignList.get(i).getStudents().getSclass());
                    Label labelGrade_i = new Label(4, i + 1, acadsignList.get(i).getStudents().getGrade());
                    Label labelEducation_i = new Label(5, i + 1, acadsignList.get(i).getStudents().getEducation());
                    Label labelStudName_i = new Label(6, i + 1, acadsignList.get(i).getStudents().getStudname());
                    Label labelStudSex_i = new Label(7, i + 1, acadsignList.get(i).getStudents().getStudsex());
                    Label labelStudPhone1_i = new Label(8, i + 1, acadsignList.get(i).getStudents().getStudphone1());
                    Label labelStudPhone2_i = new Label(9, i + 1, acadsignList.get(i).getStudents().getStudphone2());
                    Label labelAdress_i = new Label(10, i + 1, acadsignList.get(i).getStudents().getAdress());
                    Label labelFamilyPhone_i = new Label(11, i + 1, acadsignList.get(i).getStudents().getFamilyphone());
                    Label labelItemBorrDesc_i = new Label(12, i + 1, acadsignList.get(i).getItemborrdesc());
                    Label labelsuppdesc_i = new Label(13, i + 1, acadsignList.get(i).getSuppcent().getSuppdesc());
                    Label labeldormdesc_i = new Label(14, i + 1, acadsignList.get(i).getDormmana().getDormdesc());
                    Label labelhydrpay_i = new Label(15, i + 1, acadsignList.get(i).getHydropower().getHydrpay());
                    Label labeldwpay_i = new Label(16, i + 1, acadsignList.get(i).getDrinkwater().getDwpay());
                    Label labelnedesc_i = new Label(17, i + 1, acadsignList.get(i).getLibrary().getNedesc());
                    Label labelhw1pay_i = new Label(18, i + 1, acadsignList.get(i).getHotwater1().getHw1pay());
                    Label labelrentdesc_i = new Label(19, i + 1, acadsignList.get(i).getEducandcomp().getRentdesc());
                    Label labelhw2pay_i = new Label(20, i + 1, acadsignList.get(i).getHotwater2().getHw2pay());

                    writableSheet.addCell(labelStudId_i);
                    writableSheet.addCell(labeldepartName_i);
                    writableSheet.addCell(labelMajor_i);
                    writableSheet.addCell(labelSclass_i);
                    writableSheet.addCell(labelGrade_i);
                    writableSheet.addCell(labelEducation_i);
                    writableSheet.addCell(labelStudName_i);
                    writableSheet.addCell(labelStudSex_i);
                    writableSheet.addCell(labelStudPhone1_i);
                    writableSheet.addCell(labelStudPhone2_i);
                    writableSheet.addCell(labelAdress_i);
                    writableSheet.addCell(labelFamilyPhone_i);
                    writableSheet.addCell(labelItemBorrDesc_i);
                    writableSheet.addCell(labelsuppdesc_i);
                    writableSheet.addCell(labeldormdesc_i);
                    writableSheet.addCell(labelhydrpay_i);
                    writableSheet.addCell(labeldwpay_i);
                    writableSheet.addCell(labelnedesc_i);
                    writableSheet.addCell(labelhw1pay_i);
                    writableSheet.addCell(labelrentdesc_i);
                    writableSheet.addCell(labelhw2pay_i);

                    count++;
                }
            }
            //写进文档
            writableWorkbook.write();
            //关闭excel工作簿对象
            writableWorkbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(count);
    }

    //查看数据库中离校清单
    @RequestMapping(value = "lookLxqd.do")
    @ResponseBody
    public ModelAndView doLookLxqd(HttpSession session, @RequestParam(defaultValue = "0") String downloadflag) throws IOException {

        ModelAndView modelAndView = new ModelAndView();

        Students students = new Students();
        List<Lxqd> lxqd = lxqdService.selectAll();
        for (Lxqd lxqd1 : lxqd) {
            students.setLxqd(lxqd1);
        }
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(students);
        modelAndView.addObject("students1", jsonObject);
        if (!downloadflag.equals("0")) {
            modelAndView.setViewName("/admin/lookDLLxqd.jsp");
        } else {
            modelAndView.setViewName("/admin/lookLxqd.jsp");
        }
        return modelAndView;
    }


    //修改数据库中离校清单
    @RequestMapping(value = "lookUpdateLxqd.do")
    @ResponseBody
    public ModelAndView doSelectMyselfOfLxqd(Lxqd lxqd, @RequestParam(defaultValue = "0") String lookflag) throws IOException {

        ModelAndView modelAndView = new ModelAndView();
        Students students = new Students();
        if (!lookflag.equals("0")) {
            lxqdService.updateByLxqdId(lxqd);
        }
        List<Lxqd> lxqdList = lxqdService.selectAll();
        for (Lxqd lxqd1 : lxqdList) {
            students.setLxqd(lxqd1);
        }
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(students);
        modelAndView.addObject("students1", jsonObject);
        modelAndView.setViewName("/admin/updateLxqd.jsp");
        return modelAndView;
    }

    //查看数据库中离校流程
    @RequestMapping(value = "lookProcedure.do", produces = "text/html;charset=utf-8")
    @ResponseBody
    public ModelAndView doLookProcedure(HttpSession session, @RequestParam(defaultValue = "0") String lookflag) throws ParseException {

        ModelAndView modelAndView = new ModelAndView();

        List<Procedure> procedureList = procedureService.selectAll();

        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(procedureList);

        modelAndView.addObject("procedureList",jsonArray);
        if (!lookflag.equals("0")){
            modelAndView.setViewName("/admin/procedureEdit.jsp");
        }else {
            modelAndView.setViewName("/admin/lookProcedure.jsp");
        }
        return modelAndView;
    }
    //更新数据库中离校流程
    @RequestMapping(value = "updateProcedure.do", produces = "text/html;charset=utf-8")
    @ResponseBody
    public ModelAndView doUpdateProcedure(Integer id,String event,String eventtime,HttpSession session) throws ParseException {


        ModelAndView modelAndView = new ModelAndView();
        if (event != null && eventtime != null
                && !event.equals("") && !eventtime.equals("")){
            Procedure procedure = new Procedure();
            procedure.setEvent(event);
            procedure.setId(id);
            procedure.setEventtime(new SimpleDateFormat("yyyy-MM-hh").parse(eventtime));
            procedureService.updateByProcedureIdSelective(procedure);
        }
        List<Procedure> procedureList = procedureService.selectAll();

        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(procedureList);

        modelAndView.addObject("procedureList",jsonArray);

        modelAndView.setViewName("/admin/procedureEdit.jsp");

        return modelAndView;
    }

    //添加数据库中离校事件
    @RequestMapping(value = "addProcedure.do", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String doAddProcedure(String eventtime,String event,HttpSession session) throws ParseException {

        Procedure procedure = new Procedure();
        procedure.setEvent(event);
        procedure.setEventtime(new SimpleDateFormat("yyyy-MM-hh").parse(eventtime));
        procedureService.insertByProcedureSelective(procedure);

        return "/admin/procedureAdd.jsp";
    }

}
