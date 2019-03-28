package zqu.lxqd.controller;

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
import zqu.lxqd.service.DormmanaService;
import zqu.lxqd.service.StudaffadiviService;
import zqu.lxqd.service.SuppcentService;

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
@RequestMapping("admin/StudAffaDivi/")
public class StudAffaDiviController {

    @Autowired
    @Qualifier("StudaffadiviService")
    private StudaffadiviService studaffadiviService;

    @Autowired
    @Qualifier("SuppcentService")
    private SuppcentService suppcentService;

    @Autowired
    @Qualifier("DormmanaService")
    private DormmanaService dormmanaService;

    public void setStudaffadiviService(StudaffadiviService studaffadiviService) {
        this.studaffadiviService = studaffadiviService;
    }

    public void setSuppcentService(SuppcentService suppcentService) {
        this.suppcentService = suppcentService;
    }

    public void setDormmanaService(DormmanaService dormmanaService) {
        this.dormmanaService = dormmanaService;
    }

    @Autowired
    @Qualifier("AdminService")
    private AdminService adminService;

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    /*
     * 查询条件
     * mode =
     * "1":根据学号查学生学工处盖章情况
     * "2":根据学院查学生学工处盖章情况
     * "3":根据专业查学生学工处盖章情况
     * "4":根据班级查学生学工处盖章情况
     * "5":根据年级查学生学工处盖章情况
     * "6":根据姓名查学生学工处盖章情况
     * "7":根据性别查学生学工处盖章情况
     * "8":根据学工处盖章办理情况查学生资助办理情况
     * "9":根据经办人查学生学工处盖章情况
     * "all":查询所有信息
     */
    static String mode = "all";
    //查询参数
    static String selectParam;

    @RequestMapping(value = "/SelectStudaffadivi.do",produces = "text/html;charset=utf-8")
    public String doSelectStudaffadivi(String condition,
                                   @RequestParam(value = "condiValue",defaultValue = " ") String condiValue) {

        mode = condition;
        selectParam = condiValue;
        return "forward:distributionStudaffadivi.do";
    }
    /**
     * 执行这个请求时，会将查询条件设置为"all",查询所有学工处盖章情况
     */
    @RequestMapping("default.do")
    public String doDefault() {
        mode = "all";
        return "distributionStudaffadivi.do";
    }
    /**
     * 根据学生信息查询显示学生学工处盖章情况
     *
     * @param pn
     * @param session
     */
    @RequestMapping(value = "distributionStudaffadivi.do", produces = "text/html;charset=utf-8")
    public String doDistributionStudaffadivi(@RequestParam(value = "pn", defaultValue = "1") Integer pn, HttpSession session) {
        int pageSize = 8;
        if (mode.equals("all")){
            PageHelper.startPage(pn, pageSize);
            List<Studaffadivi> studaffadivis = studaffadiviService.selectAllStudaffadivi();
            PageInfo page = new PageInfo(studaffadivis, 5);
            session.setAttribute("pageInfo", page);
            return "redirect:/admin/StudAffaDivi/StudAffaDivi.jsp";
        }else if (Integer.valueOf(mode) >= 1 && Integer.valueOf(mode) <= 7) {
            Students students = new Students();
            if (mode.equals("1")) {
                students.setStudid(selectParam);
            } else if (mode.equals("2")) {
                Department department = new Department();
                department.setDepartname(selectParam);
                students.setDepartment(department);
            } else if (mode.equals("3")) {
                students.setMajor(selectParam);
            } else if (mode.equals("4")) {
                students.setSclass(selectParam);
            } else if (mode.equals("5")) {
                students.setEducation(selectParam);
            } else if (mode.equals("6")) {
                students.setStudname(selectParam);
            } else if (mode.equals("7")) {
                students.setStudsex(selectParam);
            }
            // 引入PageHelper分页插件
            // 在查询之前只需要调用，传入页码，以及每页的大小
            PageHelper.startPage(pn, pageSize);
            // startPage后面紧跟的这个查询就是一个分页查询
            List<Studaffadivi> studaffadivis = studaffadiviService.simpleSelectByStudents(students);

            // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
            // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
            PageInfo page = new PageInfo(studaffadivis, 5);
            //model.addAttribute("pageInfo", page);
            session.setAttribute("pageInfo", page);
            return "redirect:/admin/StudAffaDivi/StudAffaDivi.jsp";
        }else if (mode.equals("8")) {
            PageHelper.startPage(pn, pageSize);
            Studaffadivi studaffadivi = new Studaffadivi();
            studaffadivi.setAdmin(null);
            List<Studaffadivi> studaffadivis = studaffadiviService.selectStudaffadiviByStudaffadivi(studaffadivi);
            PageInfo page = new PageInfo(studaffadivis, 5);
            session.setAttribute("pageInfo", page);
            return "redirect:/admin/StudAffaDivi/StudAffaDivi.jsp";
        }  else if (mode.equals("9")) {
            PageHelper.startPage(pn, pageSize);
            Studaffadivi studaffadivi = new Studaffadivi();
            studaffadivi.setAdminId(selectParam);
            List<Studaffadivi> studaffadivis = studaffadiviService.selectStudaffadiviByStudaffadivi(studaffadivi);
            PageInfo page = new PageInfo(studaffadivis, 5);
            session.setAttribute("pageInfo", page);
            return "redirect:/admin/StudAffaDivi/StudAffaDivi.jsp";
        }
        return "redirect:/admin/StudAffaDivi/StudAffaDivi.jsp";
    }

    /**
     * 签名确认
     */
    @RequestMapping(value = "/StudaffadiviSign.do", produces = "text/html;charset=utf-8")
    public String doStudaffadiviSign(String studId, String adminid, HttpSession session) throws ParseException {
        //获取签名人
        Admin admin_studaffadivi = (Admin) adminService.selectByAdminId(adminid);
        //获取该表情况
        Studaffadivi studaffadivi = studaffadiviService.selectByStudId(studId);
        if (studaffadivi.getSealtime() != null || studaffadivi.getSealtime() != null ){
            return "forward:distributionStudaffadivi.do";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        studaffadivi.setStudId(studId);
        studaffadivi.setSealtime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
        studaffadivi.setAdminId(admin_studaffadivi.getAdminid());
        if (studaffadiviService.updateByStuId(studaffadivi) >= 0){
            studaffadivi = null;
            return "forward:distributionStudaffadivi.do";
        }else{
            studaffadivi = null;
            return "forward:distributionStudaffadivi.do";
        }
    }

    /**
     * 一键签名确认
     */
    @RequestMapping(value = "/StudaffadiviSignAll.do", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String doStudaffadiviSignAll(String studId, String adminid, HttpSession session) throws ParseException {
        int count = 0;
        //获取签名人
        Admin admin_studaffadivi1 = (Admin) adminService.selectByAdminId(adminid);
        //获取该表情况
        Studaffadivi studaffadivi = new Studaffadivi();
        List<Studaffadivi> studaffadiviList = studaffadiviService.selectStudaffadiviByStudaffadivi(studaffadivi);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        for (Studaffadivi studaffadivi1:studaffadiviList) {
            if (studaffadivi1.getSealtime() == null && studaffadivi1.getAdmin() == null ){
                if (studaffadivi1.getSuppcent().getAdmin().getAdminname() != null){
                    if (studaffadivi1.getDormmana().getAdmin().getAdminname() != null){
                        studaffadivi1.setStudId(studaffadivi1.getStudId());
                        studaffadivi1.setSealtime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
                        studaffadivi1.setAdminId(admin_studaffadivi1.getAdminid());
                        studaffadiviService.updateByStuId(studaffadivi1);
                        count++;
                    }
                }
            }
        }
        return String.valueOf(count);
    }


    /**
     * 通过excel文件将学生宿舍手续办理情况导入数据库
     */
    @RequestMapping(value = "/ImportStudaffadiviToDB.do", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String doImportStudaffadiviToDB(MultipartFile multipartFile, HttpSession session) throws IOException {

        CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) multipartFile;
        DiskFileItem diskFileItem = (DiskFileItem) commonsMultipartFile.getFileItem();
        File file = diskFileItem.getStoreLocation();

        int[] importResult = ImportStudaffadiviToDB(file);

        return "导入了："+importResult[1]+"条记录；其中更新了："+importResult[0]+"位学生的数据";
    }

    //将学工处盖章办理情况从数据库以*.xls格式导出到本地文件
    @RequestMapping(value = "/ExportStudaffadiviFromDB.do", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> doExportStudaffadiviFromDB(String filePath, HttpSession session) throws IOException {
        Map<String,String> result = new HashMap<String,String>();
        String fileDir = null;
        if (filePath.equals("123")) {
            fileDir = "D:\\lxqd\\download\\Studaffadivi";
            filePath = fileDir + "\\" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "" + (int) Math.random() * 1000 + ".xls";
        }
        if (!new File(fileDir).exists()) {
            new File(fileDir).mkdir();
        }
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        int count = exportStudaffadiviFromDB(file);
        String resultcount = String.valueOf(count);
        result.put("path",filePath);
        result.put("resultcount",resultcount);

        return result;
    }


    //导出学工处盖章办理情况到本地文件
    private int exportStudaffadiviFromDB(File file) {
        int count = 0;
        try {
            System.out.println("exportStudaffadiviFromDB..." + file);
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

            //查询数据库获取学工处盖章办理情况表中所有数据
            List<Studaffadivi> studaffadiviList = studaffadiviService.selectAllStudaffadivi();
            count = studaffadiviList.size();
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
            Label labelAdminName = new Label(12, 0, "盖章人");
            Label labelSignTime = new Label(13, 0, "盖章时间");

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
            for (int i = 0; i < studaffadiviList.size(); i++) {
                Label labelStudId_i = new Label(0, i + 1, studaffadiviList.get(i).getStudId());
                Label labeldepartName_i = new Label(1, i + 1, studaffadiviList.get(i).getStudents().getDepartment().getDepartname());
                Label labelMajor_i = new Label(2, i + 1, studaffadiviList.get(i).getStudents().getMajor());
                Label labelSclass_i = new Label(3, i + 1, studaffadiviList.get(i).getStudents().getSclass());
                Label labelGrade_i = new Label(4, i + 1, studaffadiviList.get(i).getStudents().getGrade());
                Label labelEducation_i = new Label(5, i + 1, studaffadiviList.get(i).getStudents().getEducation());
                Label labelStudName_i = new Label(6, i + 1, studaffadiviList.get(i).getStudents().getStudname());
                Label labelStudSex_i = new Label(7, i + 1, studaffadiviList.get(i).getStudents().getStudsex());
                Label labelStudPhone1_i = new Label(8, i + 1, studaffadiviList.get(i).getStudents().getStudphone1());
                Label labelStudPhone2_i = new Label(9, i + 1, studaffadiviList.get(i).getStudents().getStudphone2());
                Label labelAdress_i = new Label(10, i + 1, studaffadiviList.get(i).getStudents().getAdress());
                Label labelFamilyPhone_i = new Label(11, i + 1, studaffadiviList.get(i).getStudents().getFamilyphone());

                if (studaffadiviList.get(i).getAdmin() == null) {
                    labelAdminName_i = new Label(12, i + 1, "");
                    labelSignTime_i = new Label(13, i + 1, "");
                } else {
                    labelAdminName_i = new Label(12, i + 1, studaffadiviList.get(i).getAdmin().getAdminname());
                    if (studaffadiviList.get(i).getSealtime() == null) {
                        labelSignTime_i = new Label(13, i + 1, "");
                    } else {
                        labelSignTime_i = new Label(13, i + 1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(studaffadiviList.get(i).getSealtime()));
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
        return count;
    }

    //导入学工处盖章办理情况到数据库
    private int[] ImportStudaffadiviToDB(File file) {
        int[] returnnum = {0,0};
        //获取excel文件表格中所有的数据
        List<Studaffadivi> listExcel = getAllStudaffadiviByExcel(file);
        for (Studaffadivi studaffadivi : listExcel) {
            Studaffadivi studaffadivi2 = studaffadiviService.selectByStudId(studaffadivi.getStudId());
            if (studaffadivi2 != null){
                if ((studaffadivi2.getAdminId() == null || studaffadivi2.getAdminId().equals(""))
                        && (studaffadivi2.getSealtime() == null || studaffadivi2.getSealtime().equals(""))){
                    studaffadivi.setSealtime(null);
                    studaffadivi.setAdminId(null);
                    studaffadiviService.updateByStuId(studaffadivi);
                    returnnum[0]++;
                }
            }
        }
        System.out.println(returnnum);
        System.out.println(returnnum[0]);
        System.out.println(returnnum[1]);
        return returnnum;
    }

    //获取excel文件中学工处盖章办理情况
    private List<Studaffadivi> getAllStudaffadiviByExcel(File file) {
        List<Studaffadivi> list = new ArrayList<Studaffadivi>();
        try {
            Workbook workbook = Workbook.getWorkbook(file);
            //获取sheet1
            Sheet sheet1 = workbook.getSheet(0);
            int cols = sheet1.getColumns();
            int rows = sheet1.getRows();
            Date signtime = null;
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    String studId = sheet1.getCell(j++, i).getContents();
                    String adminId = sheet1.getCell(j++, i).getContents();
                    String signtimes = sheet1.getCell(j++, i).getContents();

                    if (adminId.equals("")) {
                        adminId = null;
                    }
                    if (!signtimes.equals("")) {
                        signtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(signtimes);
                    }
                    list.add(new Studaffadivi(studId, adminId, signtime));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    //    跳转到主界面
    @RequestMapping("/StudAffaDiviMain.do")
    public String toMain() {
        System.out.println("----------Studaffadivi.do ----------");
        return "forward:default.do";
    }

}
