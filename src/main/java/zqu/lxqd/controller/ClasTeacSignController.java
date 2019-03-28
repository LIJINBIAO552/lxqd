package zqu.lxqd.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import zqu.lxqd.bean.*;
import zqu.lxqd.service.AdminService;
import zqu.lxqd.service.ClasTeacSignService;
import zqu.lxqd.service.StudentsService;
import zqu.lxqd.service.TeacwithclasService;

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
@RequestMapping("admin/ClasTeacSign/")
public class ClasTeacSignController {

    @Autowired
    @Qualifier("ClasteacsignService")
    private ClasTeacSignService clasTeacSignService;

    public void setClasTeacSignService(ClasTeacSignService clasTeacSignService) {
        this.clasTeacSignService = clasTeacSignService;
    }

    @Autowired
    @Qualifier("TeacwithclasService")
    private TeacwithclasService teacwithclasService;

    public void setTeacwithclasService(TeacwithclasService teacwithclasService) {
        this.teacwithclasService = teacwithclasService;
    }

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

    /*
     * 查询条件
     * mode =
     * "1":根据学号查班主任签名情况
     * "2":
     * "3":根据专业查班主任签名情况
     * "4":
     * "5":
     * "6":根据姓名查班主任签名情况
     * "7":
     * "8":
     * "9":
     * "all":查询所有信息
     */
    static String mode = "all";
    //查询参数
    static String selectParam;

    @RequestMapping(value = "/SelectClasTeacSign.do", produces = "text/html;charset=utf-8")
    public String doSelectClasTeacSign(String condition,
                                       @RequestParam(value = "condiValue", defaultValue = " ") String condiValue) {

        mode = condition;
        selectParam = condiValue;
        return "forward:distributionClasTeacSign.do";
    }

    /**
     * 执行这个请求时，会将查询条件设置为"all",查询所有宿舍办理情况
     */
    @RequestMapping("default.do")
    public String doDefault() {
        mode = "all";
        return "distributionClasTeacSign.do";
    }

    /**
     * 根据学生信息查询显示学生宿舍办理情况
     *
     * @param pn
     * @param session
     */
    @RequestMapping(value = "distributionClasTeacSign.do", produces = "text/html;charset=utf-8")
    public String doDistributionClasTeacSign(@RequestParam(value = "pn", defaultValue = "1") Integer pn, HttpSession session) {

        int pageSize = 8;
        int startNum = 0;
        int endNum = 0;
        //获取班主任信息
        Admin admin = (Admin) session.getAttribute("admin");
        //新建班主任与班级关系对象
        Teacwithclas teacwithclas = new Teacwithclas();
        //将班主任教工号存进对象，并通过关系对象查找所有属于班主任的班级
        teacwithclas.setAdminId(admin.getAdminid());
        List<Teacwithclas> teacwithclasList = teacwithclasService.selectByTeacwithclas(teacwithclas);
        //因为没有建班级表，属性在学生里，所以新建学生对象，通过设置学生对象的年级、班级、专业属性，查找班主任的所有学生的签名表
        Students studentstemp = new Students();
        List<Clasteacsign> clasteacsignList = new ArrayList<Clasteacsign>();
//        if (mode.equals("all")) {
        //遍历班级，通过班级查询签名表，再将查到的表添加到总表
        for (Teacwithclas teacwithclas1 : teacwithclasList) {
            studentstemp.setGrade(teacwithclas1.getSgrade());
            studentstemp.setMajor(teacwithclas1.getSmajor());
            studentstemp.setSclass(teacwithclas1.getSsclass());
            System.out.println("....."+studentstemp);
            //一个班一个班给存进去
            if (mode.equals("all")) {//查询全部
                List<Clasteacsign> clasTeacSigntemp = clasTeacSignService.simpleSelectByStudents(studentstemp);
                clasteacsignList.addAll(clasTeacSigntemp);

            } else if (mode.equals("1")) {//根据学号
                Students studentstemp1 = studentsService.selectByStudId(selectParam);
                if ((teacwithclas1.getSgrade().equals(studentstemp1.getGrade())
                        && (teacwithclas1.getSmajor().equals(studentstemp1.getMajor()))
                        && (teacwithclas1.getSsclass().equals(studentstemp1.getSclass())))) {
                    clasteacsignList = clasTeacSignService.simpleSelectByStudents(studentstemp1);
                }
            } else if (mode.equals("3")) {//根据专业
                if ((teacwithclas1.getSmajor()).equals(selectParam)) {
                    List<Clasteacsign> clasTeacSigntemp = clasTeacSignService.simpleSelectByStudents(studentstemp);
                    clasteacsignList.addAll(clasTeacSigntemp);
                }
            } else if (mode.equals("6")) {
                System.out.println(selectParam);
                studentstemp.setStudname(selectParam);
                System.out.println(studentstemp+"12351555553");
                List<Clasteacsign> clasTeacSigntemp = clasTeacSignService.simpleSelectByStudents(studentstemp);
                System.out.println(clasTeacSigntemp.toString()+"88888");
                clasteacsignList.addAll(clasTeacSigntemp);
            }
        }
        startNum = (pn - 1) * pageSize;
        if (pn * pageSize <= clasteacsignList.size()){
            endNum = pn * pageSize;
        }else {
            endNum = clasteacsignList.size();
        }
        //根据页数截取全部结果的，取对应页内容进行返回
        List<Clasteacsign> resultc = clasteacsignList.subList(startNum, endNum);
        PageInfo page = new PageInfo(resultc, 5);
        session.setAttribute("pageInfo", page);
        return "redirect:/admin/ClasTeacSign/ClasTeacSign.jsp";
    }


    /**
     * 签名确认
     */
    @RequestMapping(value = "/ClasTeacSignSign.do", produces = "text/html;charset=utf-8")
    public String doClasTeacSignSign(String studId, String adminid, HttpSession session) throws ParseException {
        //获取签名人
        Admin admin_clasTeacSign = (Admin) adminService.selectByAdminId(adminid);
        //获取该表情况
        Clasteacsign clasTeacSign = clasTeacSignService.selectByStudId(studId);
        if (clasTeacSign.getSigntime() != null || clasTeacSign.getSigntime() != null) {
            return "forward:distributionClasTeacSign.do";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        clasTeacSign.setStudId(studId);
        clasTeacSign.setSigntime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
        clasTeacSign.setAdminId(admin_clasTeacSign.getAdminid());
        if (clasTeacSignService.updateByStuId(clasTeacSign) >= 0) {
            clasTeacSign = null;
            return "forward:distributionClasTeacSign.do";
        } else {
            clasTeacSign = null;
            return "forward:distributionClasTeacSign.do";
        }
    }

    /**
     * 一键签名确认
     */
    @RequestMapping(value = "/ClasTeacSignSignAll.do", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String doClasTeacSignSignAll(String studId, String adminid, HttpSession session) throws ParseException {
        int count = 0;
        //获取签名人
        Admin admin_clasTeacSign1 = (Admin) adminService.selectByAdminId(adminid);
        //获取该表情况
        Clasteacsign clasTeacSign = new Clasteacsign();
        //新建班主任与班级关系对象
        Teacwithclas teacwithclas = new Teacwithclas();
        //将班主任教工号存进对象，并通过关系对象查找所有属于班主任的班级
        teacwithclas.setAdminId(adminid);
        List<Teacwithclas> teacwithclasList = teacwithclasService.selectByTeacwithclas(teacwithclas);
        //因为没有建班级表，属性在学生里，所以新建学生对象，通过设置学生对象的年级、班级、专业属性，查找班主任的所有学生的签名表
        Students studentstemp = new Students();
        List<Clasteacsign> clasteacsignList = new ArrayList<Clasteacsign>();
        for (Teacwithclas teacwithclas1 : teacwithclasList) {
            studentstemp.setGrade(teacwithclas1.getSgrade());
            studentstemp.setMajor(teacwithclas1.getSmajor());
            studentstemp.setSclass(teacwithclas1.getSsclass());
            List<Clasteacsign> clasTeacSigntemp = clasTeacSignService.simpleSelectByStudents(studentstemp);
            clasteacsignList.addAll(clasTeacSigntemp);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        for (Clasteacsign clasTeacSign1 : clasteacsignList) {
            if (clasTeacSign1.getSigntime() == null && clasTeacSign1.getAdmin() == null) {
                clasTeacSign1.setStudId(clasTeacSign1.getStudId());
                clasTeacSign1.setSigntime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
                clasTeacSign1.setAdminId(admin_clasTeacSign1.getAdminid());
                clasTeacSignService.updateByStuId(clasTeacSign1);
                count++;
            }
        }
        return String.valueOf(count);
    }


    /**
     * 通过excel文件将班主任签名导入数据库
     */
    @RequestMapping(value = "/ImportClasTeacSignToDB.do", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String doImportClasTeacSignToDB(MultipartFile multipartFile, HttpSession session) throws IOException {

        CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) multipartFile;
        DiskFileItem diskFileItem = (DiskFileItem) commonsMultipartFile.getFileItem();
        File file = diskFileItem.getStoreLocation();

        int[] importResult = ImportClasTeacSignToDB(file);

        return "导入了：" + importResult[1] + "条记录；其中更新了：" + importResult[0] + "位学生的数据";
    }

    //将班主任签名从数据库以*.xls格式导出到本地文件
    @RequestMapping(value = "/ExportClasTeacSignFromDB.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> doExportClasTeacSignFromDB(String filePath, HttpSession session) throws IOException {

        Map<String, String> result = new HashMap<String, String>();
        //获取本人信息
        Admin myself = (Admin) session.getAttribute("admin");

        String fileDir = null;
        if (filePath.equals("123")) {
            fileDir = "D:\\lxqd\\download\\ClasTeacSign";
            filePath = fileDir + "\\" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "" + (int) Math.random() * 1000 + ".xls";
        }
        if (!new File(fileDir).exists()) {
            new File(fileDir).mkdir();
        }
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }




        String resultcount = exportClasTeacSignFromDB(file,myself);
        result.put("path", filePath);
        result.put("resultcount", resultcount);

        return result;
    }


    //导出班主任签名到本地文件
    private String exportClasTeacSignFromDB(File file,Admin myself) {
        int count = 0;
        try {
            System.out.println("exportClasTeacSignFromDB..." + file);
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

            //获取该表情况
            Clasteacsign clasTeacSign = new Clasteacsign();
            //新建班主任与班级关系对象
            Teacwithclas teacwithclas = new Teacwithclas();
            //将班主任教工号存进对象，并通过关系对象查找所有属于班主任的班级
            teacwithclas.setAdminId(myself.getAdminid());
            List<Teacwithclas> teacwithclasList = teacwithclasService.selectByTeacwithclas(teacwithclas);
            //因为没有建班级表，属性在学生里，所以新建学生对象，通过设置学生对象的年级、班级、专业属性，查找班主任的所有学生的签名表
            Students studentstemp = new Students();
            List<Clasteacsign> clasTeacSignList = new ArrayList<Clasteacsign>();
            //查询数据库获取班主任签名表中所有数据
            for (Teacwithclas teacwithclas1 : teacwithclasList) {
                studentstemp.setGrade(teacwithclas1.getSgrade());
                studentstemp.setMajor(teacwithclas1.getSmajor());
                studentstemp.setSclass(teacwithclas1.getSsclass());
                List<Clasteacsign> clasTeacSigntemp = clasTeacSignService.simpleSelectByStudents(studentstemp);
                //将各个班级的学生签名表存在一起
                clasTeacSignList.addAll(clasTeacSigntemp);
            }
            count = clasTeacSignList.size();

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
            Label labelAdminName = new Label(12, 0, "签名人");
            Label labelSignTime = new Label(13, 0, "签名时间");

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
            writableSheet.addCell(labelAdminName);
            writableSheet.addCell(labelSignTime);
            Label labelAdminName_i = null;
            Label labelSignTime_i = null;
            for (int i = 0; i < clasTeacSignList.size(); i++) {
                Label labelStudId_i = new Label(0, i + 1, clasTeacSignList.get(i).getStudId());
                Label labeldepartName_i = new Label(1, i + 1, clasTeacSignList.get(i).getStudents().getDepartment().getDepartname());
                Label labelMajor_i = new Label(2, i + 1, clasTeacSignList.get(i).getStudents().getMajor());
                Label labelSclass_i = new Label(3, i + 1, clasTeacSignList.get(i).getStudents().getSclass());
                Label labelGrade_i = new Label(4, i + 1, clasTeacSignList.get(i).getStudents().getGrade());
                Label labelEducation_i = new Label(5, i + 1, clasTeacSignList.get(i).getStudents().getEducation());
                Label labelStudName_i = new Label(6, i + 1, clasTeacSignList.get(i).getStudents().getStudname());
                Label labelStudSex_i = new Label(7, i + 1, clasTeacSignList.get(i).getStudents().getStudsex());
                Label labelStudPhone1_i = new Label(8, i + 1, clasTeacSignList.get(i).getStudents().getStudphone1());
                Label labelStudPhone2_i = new Label(9, i + 1, clasTeacSignList.get(i).getStudents().getStudphone2());
                Label labelAdress_i = new Label(10, i + 1, clasTeacSignList.get(i).getStudents().getAdress());
                Label labelFamilyPhone_i = new Label(11, i + 1, clasTeacSignList.get(i).getStudents().getFamilyphone());

                Admin admin1 = adminService.selectByAdminId(clasTeacSignList.get(i).getAdminId());
                if (clasTeacSignList.get(i).getAdmin() == null) {
                    labelAdminName_i = new Label(12, i + 1, "");
                    labelSignTime_i = new Label(13, i + 1, "");
                } else {
                    labelAdminName_i = new Label(12, i + 1, admin1.getAdminname());
                    if (clasTeacSignList.get(i).getSigntime() == null) {
                        labelSignTime_i = new Label(13, i + 1, "");
                    } else {
                        labelSignTime_i = new Label(13, i + 1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(clasTeacSignList.get(i).getSigntime()));
                    }
                }

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
                writableSheet.addCell(labelAdminName_i);
                writableSheet.addCell(labelSignTime_i);
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

    //导入班主任签名到数据库
    private int[] ImportClasTeacSignToDB(File file) {
        int[] returnnum = {0, 0};
        //获取excel文件表格中所有的数据
        List<Clasteacsign> listExcel = getAllClasTeacSignByExcel(file);
        for (Clasteacsign clasTeacSign : listExcel) {
            Clasteacsign clasTeacSign2 = clasTeacSignService.selectByStudId(clasTeacSign.getStudId());
            if (clasTeacSign2 != null) {
                if ((clasTeacSign2.getAdminId() == null || clasTeacSign2.getAdminId().equals(""))
                        && (clasTeacSign2.getSigntime() == null || clasTeacSign2.getSigntime().equals(""))) {
                    clasTeacSign.setSigntime(null);
                    clasTeacSign.setAdminId(null);
                    clasTeacSignService.updateByStuId(clasTeacSign);
                    returnnum[0]++;
                }
            }
        }
        System.out.println(returnnum);
        System.out.println(returnnum[0]);
        System.out.println(returnnum[1]);
        return returnnum;
    }

    //获取excel文件中班主任签名
    private List<Clasteacsign> getAllClasTeacSignByExcel(File file) {
        List<Clasteacsign> list = new ArrayList<Clasteacsign>();
        try {
            Workbook workbook = Workbook.getWorkbook(file);
            //获取sheet1
            Sheet sheet1 = workbook.getSheet(0);
            int cols = sheet1.getColumns();
            int rows = sheet1.getRows();
            Date signtime = null;
            Admin admin1 = null;
            String adminId = null;
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    String studId = sheet1.getCell(j++, i).getContents();
                    String adminName = sheet1.getCell(j++, i).getContents();
                    String signtimes = sheet1.getCell(j++, i).getContents();
                    if (adminName.equals("")) {
                        adminId = null;
                    } else {
                        admin1.setAdminname(adminName);
                        Admin admin2 = (Admin) adminService.selectAdminByAdmin(admin1);
                        adminId = admin2.getAdminid();
                    }
                    if (!signtimes.equals("")) {
                        signtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(signtimes);
                    }
                    list.add(new Clasteacsign(studId, adminId, signtime));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    //    跳转到主界面
    @RequestMapping("/ClasTeacSignMain.do")
    public String toMain() {
        System.out.println("----------ClasTeacSign.do ----------");
        return "forward:default.do";
    }

}
