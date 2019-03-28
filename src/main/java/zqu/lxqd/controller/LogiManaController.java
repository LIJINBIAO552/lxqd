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
import zqu.lxqd.bean.Admin;
import zqu.lxqd.bean.Department;
import zqu.lxqd.bean.Logimana;
import zqu.lxqd.bean.Students;
import zqu.lxqd.service.AdminService;
import zqu.lxqd.service.DormmanaService;
import zqu.lxqd.service.LogimanaService;

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
@RequestMapping("admin/LogiMana/")
public class LogiManaController {

    @Autowired
    @Qualifier("LogimanaService")
    private LogimanaService logimanaService;

    public void setLogimanaService(LogimanaService logimanaService) {
        this.logimanaService = logimanaService;
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
     * "1":根据学号查学生后勤盖章情况
     * "2":根据学院查学生后勤盖章情况
     * "3":根据专业查学生后勤盖章情况
     * "4":根据班级查学生后勤盖章情况
     * "5":根据年级查学生后勤盖章情况
     * "6":根据姓名查学生后勤盖章情况
     * "7":根据性别查学生后勤盖章情况
     * "8":根据后勤盖章办理情况查学生资助办理情况
     * "9":根据经办人查学生宿舍办理情况
     * "all":查询所有信息
     */
    static String mode = "all";
    //查询参数
    static String selectParam;

    @RequestMapping(value = "/SelectLogiMana.do", produces = "text/html;charset=utf-8")
    public String doSelectLogiMana(String condition,
                                   @RequestParam(value = "condiValue", defaultValue = " ") String condiValue) {

        mode = condition;
        selectParam = condiValue;
        return "forward:distributionLogiMana.do";
    }

    /**
     * 执行这个请求时，会将查询条件设置为"all",查询所有宿舍办理情况
     */
    @RequestMapping("default.do")
    public String doDefault() {
        mode = "all";
        return "distributionLogiMana.do";
    }

    /**
     * 根据学生信息查询显示学生宿舍办理情况
     *
     * @param pn
     * @param session
     */
    @RequestMapping(value = "distributionLogiMana.do", produces = "text/html;charset=utf-8")
    public String doDistributionLogiMana(@RequestParam(value = "pn", defaultValue = "1") Integer pn, HttpSession session) {
        int pageSize = 8;
        if (mode.equals("all")) {
            PageHelper.startPage(pn, pageSize);
            List<Logimana> logiManas = logimanaService.selectAllLogimana();
            PageInfo page = new PageInfo(logiManas, 5);
            session.setAttribute("pageInfo", page);
            return "redirect:/admin/LogiMana/LogiMana.jsp";
        } else if (Integer.valueOf(mode) >= 1 && Integer.valueOf(mode) <= 7) {
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
            List<Logimana> logimanas = logimanaService.simpleSelectByStudents(students);
            // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
            // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
            PageInfo page = new PageInfo(logimanas, 5);
            //model.addAttribute("pageInfo", page);
            session.setAttribute("pageInfo", page);
            return "redirect:/admin/LogiMana/LogiMana.jsp";
        } else if (mode.equals("8")) {
            PageHelper.startPage(pn, pageSize);
            Logimana logimana = new Logimana();
            logimana.setAdmin(null);
            List<Logimana> logimanas = logimanaService.selectLogimanaByLogimana(logimana);
            PageInfo page = new PageInfo(logimanas, 5);
            session.setAttribute("pageInfo", page);
            return "redirect:/admin/LogiMana/LogiMana.jsp";
        } else if (mode.equals("9")) {
            PageHelper.startPage(pn, pageSize);
            Logimana logiMana = new Logimana();
            logiMana.setAdminId(selectParam);
            List<Logimana> logimanas = logimanaService.selectLogimanaByLogimana(logiMana);
            PageInfo page = new PageInfo(logimanas, 5);
            session.setAttribute("pageInfo", page);
            return "redirect:/admin/LogiMana/LogiMana.jsp";
        }
        return "redirect:/admin/LogiMana/LogiMana.jsp";
    }

    /**
     * 签名确认
     */
    @RequestMapping(value = "/LogiManaSign.do", produces = "text/html;charset=utf-8")
    public String doLogiManaSign(String studId, String adminid, HttpSession session) throws ParseException {
        //获取签名人
        Admin admin_logimana = (Admin) adminService.selectByAdminId(adminid);
        //获取该表情况
        Logimana logimana = logimanaService.selectByStudId(studId);
        if (logimana.getSealtime() != null || logimana.getSealtime() != null) {
            return "forward:distributionLogiMana.do";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logimana.setStudId(studId);
        logimana.setSealtime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
        logimana.setAdminId(admin_logimana.getAdminid());
        if (logimanaService.updateByStuId(logimana) >= 0) {
            logimana = null;
            return "forward:distributionLogiMana.do";
        } else {
            logimana = null;
            return "forward:distributionLogiMana.do";
        }
    }

    /**
     * 一键签名确认
     */
    @RequestMapping(value = "/LogiManaSignAll.do", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String doLogiManaSignAll(String studId, String adminid, HttpSession session) throws ParseException {
        int count = 0;
        //获取签名人
        Admin admin_logimana1 = (Admin) adminService.selectByAdminId(adminid);
        //获取该表情况
        Logimana logimana = new Logimana();
        List<Logimana> logimanaList = logimanaService.selectLogimanaByLogimana(logimana);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        for (Logimana logimana1 : logimanaList) {
            if (logimana1.getSealtime() == null && logimana1.getAdmin() == null) {
                logimana1.setStudId(logimana1.getStudId());
                logimana1.setSealtime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
                logimana1.setAdminId(admin_logimana1.getAdminid());
                logimanaService.updateByStuId(logimana1);
                count++;
            }
        }
        return String.valueOf(count);
    }


    /**
     * 通过excel文件将学生宿舍手续办理情况导入数据库
     */
    @RequestMapping(value = "/ImportLogiManaToDB.do", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String doImportLogiManaToDB(MultipartFile multipartFile, HttpSession session) throws IOException {

        CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) multipartFile;
        DiskFileItem diskFileItem = (DiskFileItem) commonsMultipartFile.getFileItem();
        File file = diskFileItem.getStoreLocation();

        int[] importResult = ImportLogiManaToDB(file);

        return "导入了：" + importResult[1] + "条记录；其中更新了：" + importResult[0] + "位学生的数据";
    }

    //将学生资助手续办理情况从数据库以*.xls格式导出到本地文件
    @RequestMapping(value = "/ExportLogiManaFromDB.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> doExportLogiManaFromDB(String filePath, HttpSession session) throws IOException {
        Map<String, String> result = new HashMap<String, String>();
        String fileDir = null;
        if (filePath.equals("123")) {
            fileDir = "D:\\lxqd\\download\\LogiMana";
            filePath = fileDir + "\\" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "" + (int) Math.random() * 1000 + ".xls";
        }
        if (!new File(fileDir).exists()) {
            new File(fileDir).mkdir();
        }
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        String resultcount = exportLogiManaFromDB(file);
        result.put("path", filePath);
        result.put("resultcount", resultcount);

        return result;
    }


    //导出学生资助手续办理情况到本地文件
    private String exportLogiManaFromDB(File file) {
        int count = 0;
        try {
            System.out.println("exportLogiManaFromDB..." + file);
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

            //查询数据库获取学生资助手续办理情况表中所有数据
            List<Logimana> logimanaList = logimanaService.selectAllLogimana();
            count = logimanaList.size();
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
            Label labelAdminName = new Label(12, 0, "经办人");
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
            for (int i = 0; i < logimanaList.size(); i++) {
                Label labelStudId_i = new Label(0, i + 1, logimanaList.get(i).getStudId());
                Label labeldepartName_i = new Label(1, i + 1, logimanaList.get(i).getStudents().getDepartment().getDepartname());
                Label labelMajor_i = new Label(2, i + 1, logimanaList.get(i).getStudents().getMajor());
                Label labelSclass_i = new Label(3, i + 1, logimanaList.get(i).getStudents().getSclass());
                Label labelGrade_i = new Label(4, i + 1, logimanaList.get(i).getStudents().getGrade());
                Label labelEducation_i = new Label(5, i + 1, logimanaList.get(i).getStudents().getEducation());
                Label labelStudName_i = new Label(6, i + 1, logimanaList.get(i).getStudents().getStudname());
                Label labelStudSex_i = new Label(7, i + 1, logimanaList.get(i).getStudents().getStudsex());
                Label labelStudPhone1_i = new Label(8, i + 1, logimanaList.get(i).getStudents().getStudphone1());
                Label labelStudPhone2_i = new Label(9, i + 1, logimanaList.get(i).getStudents().getStudphone2());
                Label labelAdress_i = new Label(10, i + 1, logimanaList.get(i).getStudents().getAdress());
                Label labelFamilyPhone_i = new Label(11, i + 1, logimanaList.get(i).getStudents().getFamilyphone());

                if (logimanaList.get(i).getAdmin() == null) {
                    labelAdminName_i = new Label(12, i + 1, "");
                    labelSignTime_i = new Label(13, i + 1, "");
                } else {
                    labelAdminName_i = new Label(12, i + 1, logimanaList.get(i).getAdmin().getAdminname());
                    if (logimanaList.get(i).getSealtime() == null) {
                        labelSignTime_i = new Label(13, i + 1, "");
                    } else {
                        labelSignTime_i = new Label(13, i + 1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(logimanaList.get(i).getSealtime()));
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

    //导入学生资助手续办理情况到数据库
    private int[] ImportLogiManaToDB(File file) {
        int[] returnnum = {0, 0};
        //获取excel文件表格中所有的数据
        List<Logimana> listExcel = getAllLogiManaByExcel(file);
        for (Logimana logimana : listExcel) {
            Logimana logimana2 = logimanaService.selectByStudId(logimana.getStudId());
            if (logimana2 != null) {
                if ((logimana2.getAdminId() == null || logimana2.getAdminId().equals(""))
                        && (logimana2.getSealtime() == null || logimana2.getSealtime().equals(""))) {
                    logimanaService.updateByStuId(logimana);
                    returnnum[0]++;
                }
            } else {
                logimanaService.insertByLogimana(logimana);
                returnnum[1]++;
            }
        }
        System.out.println(returnnum);
        System.out.println(returnnum[0]);
        System.out.println(returnnum[1]);
        return returnnum;
    }

    //获取excel文件中学生资助手续办理情况
    private List<Logimana> getAllLogiManaByExcel(File file) {
        List<Logimana> list = new ArrayList<Logimana>();
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
                    list.add(new Logimana(studId, adminId, signtime));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    //    跳转到主界面
    @RequestMapping("/LogiManaMain.do")
    public String toMain() {
        System.out.println("----------LogiMana.do ----------");
        return "forward:default.do";
    }

}
