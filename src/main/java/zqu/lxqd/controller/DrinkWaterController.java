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
import zqu.lxqd.bean.Drinkwater;
import zqu.lxqd.bean.Students;
import zqu.lxqd.service.AdminService;
import zqu.lxqd.service.DrinkwaterService;

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
@RequestMapping("admin/DrinkWater/")
public class DrinkWaterController {

    @Autowired
    @Qualifier("DrinkwaterService")
    private DrinkwaterService drinkwaterService;

    public void setDrinkwaterService(DrinkwaterService drinkwaterService) {
        this.drinkwaterService = drinkwaterService;
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
     * "1":根据学号查学生饮水办理情况
     * "2":根据学院查学生饮水办理情况
     * "3":根据专业查学生饮水办理情况
     * "4":根据班级查学生饮水办理情况
     * "5":根据年级查学生饮水办理情况
     * "6":根据姓名查学生饮水办理情况
     * "7":根据性别查学生饮水办理情况
     * "8":根据饮水手续办理情况查学生资助办理情况
     * "9":根据经办人查学生饮水办理情况
     * "all":查询所有信息
     */
    static String mode = "all";
    //查询参数
    static String selectParam;

    @RequestMapping(value = "/SelectDrinkWater.do", produces = "text/html;charset=utf-8")
    public String doSelectDrinkWater(String condition,
                                     @RequestParam(value = "condiValue", defaultValue = " ") String condiValue) {

        mode = condition;
        selectParam = condiValue;
        return "forward:distributionDrinkWater.do";
    }

    /**
     * 执行这个请求时，会将查询条件设置为"all",查询所有饮水办理情况
     */
    @RequestMapping("default.do")
    public String doDefault() {
        mode = "all";
        return "distributionDrinkWater.do";
    }

    /**
     * 根据学生信息查询显示学生饮水办理情况
     *
     * @param pn
     * @param session
     */
    @RequestMapping(value = "distributionDrinkWater.do", produces = "text/html;charset=utf-8")
    public String doDistributionDrinkWater(@RequestParam(value = "pn", defaultValue = "1") Integer pn, HttpSession session) {
        int pageSize = 8;
        if (mode.equals("all")) {
            PageHelper.startPage(pn, pageSize);
            List<Drinkwater> drinkwaters = drinkwaterService.selectAllDrinkwater();
            PageInfo page = new PageInfo(drinkwaters, 5);
            session.setAttribute("pageInfo", page);
            return "redirect:/admin/DrinkWater/DrinkWater.jsp";
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
            List<Drinkwater> drinkwater = drinkwaterService.simpleSelectByStudents(students);
            // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
            // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
            PageInfo page = new PageInfo(drinkwater, 5);
            //model.addAttribute("pageInfo", page);
            session.setAttribute("pageInfo", page);
            return "redirect:/admin/DrinkWater/DrinkWater.jsp";
        } else if (mode.equals("8")) {
            PageHelper.startPage(pn, pageSize);
            Drinkwater drinkwater = new Drinkwater();
            drinkwater.setDwpay(selectParam);
            List<Drinkwater> drinkwaters = drinkwaterService.selectDrinkwaterByDrinkwater(drinkwater);
            PageInfo page = new PageInfo(drinkwaters, 5);
            session.setAttribute("pageInfo", page);
            return "redirect:/admin/DrinkWater/DrinkWater.jsp";
        } else if (mode.equals("9")) {
            PageHelper.startPage(pn, pageSize);
            Drinkwater drinkwater = new Drinkwater();
            drinkwater.setAdminId(selectParam);
            List<Drinkwater> drinkwaters = drinkwaterService.selectDrinkwaterByDrinkwater(drinkwater);
            PageInfo page = new PageInfo(drinkwaters, 5);
            session.setAttribute("pageInfo", page);
            return "redirect:/admin/DrinkWater/DrinkWater.jsp";
        }
        return "redirect:/admin/DrinkWater/DrinkWater.jsp";
    }

    /**
     * 签名确认
     */
    @RequestMapping(value = "/DrinkWaterSign.do", produces = "text/html;charset=utf-8")
    public String doDrinkWaterSign(String studId, String adminid, HttpSession session) throws ParseException {
        //获取签名人
        Admin admin_drinkwater = (Admin) adminService.selectByAdminId(adminid);
        //获取该表情况
        Drinkwater drinkwater = drinkwaterService.selectByStudId(studId);
        if (drinkwater.getSigntime() != null || drinkwater.getSigntime() != null) {
            return "forward:distributionDrinkWater.do";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        drinkwater.setStudId(studId);
        drinkwater.setDwpay("已完成");
        drinkwater.setSigntime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
        drinkwater.setAdminId(admin_drinkwater.getAdminid());
        if (drinkwaterService.updateByDrinkwater(drinkwater) >= 0) {
            drinkwater = null;
            return "forward:distributionDrinkWater.do";
        } else {
            drinkwater = null;
            return "forward:distributionDrinkWater.do";
        }
    }

    /**
     * 一键签名确认
     */
    @RequestMapping(value = "/DrinkWaterSignAll.do", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String doSignAll(String studId, String adminid, HttpSession session) throws ParseException {
        int count = 0;
        //获取签名人
        Admin admin_drinkwater1 = (Admin) adminService.selectByAdminId(adminid);
        //获取该表情况
        Drinkwater drinkwater = new Drinkwater();
        drinkwater.setDwpay("待盖章");
        List<Drinkwater> drinkwaterList = drinkwaterService.selectDrinkwaterByDrinkwater(drinkwater);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        for (Drinkwater drinkwater1 : drinkwaterList) {
            if (drinkwater1.getSigntime() == null && drinkwater1.getAdmin() == null) {
                drinkwater1.setStudId(drinkwater1.getStudId());
                drinkwater1.setDwpay("已完成");
                drinkwater1.setSigntime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
                drinkwater1.setAdminId(admin_drinkwater1.getAdminid());
                drinkwaterService.updateByDrinkwater(drinkwater1);
                count++;
            }
        }
        return String.valueOf(count);
    }


    /**
     * 通过excel文件将学生饮水手续办理情况导入数据库
     */
    @RequestMapping(value = "/ImportDrinkWaterToDB.do", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String doImportDrinkWaterToDB(MultipartFile multipartFile, HttpSession session) throws IOException {

        CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) multipartFile;
        DiskFileItem diskFileItem = (DiskFileItem) commonsMultipartFile.getFileItem();
        File file = diskFileItem.getStoreLocation();

        int[] importResult = ImportDrinkWaterToDB(file);

        return "导入了：" + importResult[1] + "条记录；其中更新了：" + importResult[0] + "位学生的数据";
    }

    //将学生资助手续办理情况从数据库以*.xls格式导出到本地文件
    @RequestMapping(value = "/ExportDrinkWaterFromDB.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> doExportDrinkWaterFromDB(String filePath, HttpSession session) throws IOException {

        Map<String, String> result = new HashMap<String, String>();
        String fileDir = null;
        if (filePath.equals("123")) {
            fileDir = "D:\\lxqd\\download\\DrinkWater";
            filePath = fileDir + "\\" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "" + (int) Math.random() * 1000 + ".xls";
        }
        if (!new File(fileDir).exists()) {
            new File(fileDir).mkdir();
        }
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        String resultcount = exportDrinkWaterFromDB(file);

        result.put("path", filePath);
        result.put("resultcount", resultcount);

        return result;
    }


    //导出学生资助手续办理情况到本地文件
    private String exportDrinkWaterFromDB(File file) {
        int count = 0;
        try {
            System.out.println("exportDrinkWaterFromDB..." + file);
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
            List<Drinkwater> drinkwaterList = drinkwaterService.selectAllDrinkwater();
            count = drinkwaterList.size();
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
            Label labelDormDesc = new Label(12, 0, "饮水手续办理情况描述");
            Label labelAdminName = new Label(13, 0, "经办人");
            Label labelSignTime = new Label(14, 0, "签名时间");

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
            writableSheet.addCell(labelDormDesc);
            writableSheet.addCell(labelAdminName);
            writableSheet.addCell(labelSignTime);
            Label labelAdminName_i = null;
            Label labelSignTime_i = null;
            for (int i = 0; i < drinkwaterList.size(); i++) {
                Label labelStudId_i = new Label(0, i + 1, drinkwaterList.get(i).getStudId());
                Label labeldepartName_i = new Label(1, i + 1, drinkwaterList.get(i).getStudents().getDepartment().getDepartname());
                Label labelMajor_i = new Label(2, i + 1, drinkwaterList.get(i).getStudents().getMajor());
                Label labelSclass_i = new Label(3, i + 1, drinkwaterList.get(i).getStudents().getSclass());
                Label labelGrade_i = new Label(4, i + 1, drinkwaterList.get(i).getStudents().getGrade());
                Label labelEducation_i = new Label(5, i + 1, drinkwaterList.get(i).getStudents().getEducation());
                Label labelStudName_i = new Label(6, i + 1, drinkwaterList.get(i).getStudents().getStudname());
                Label labelStudSex_i = new Label(7, i + 1, drinkwaterList.get(i).getStudents().getStudsex());
                Label labelStudPhone1_i = new Label(8, i + 1, drinkwaterList.get(i).getStudents().getStudphone1());
                Label labelStudPhone2_i = new Label(9, i + 1, drinkwaterList.get(i).getStudents().getStudphone2());
                Label labelAdress_i = new Label(10, i + 1, drinkwaterList.get(i).getStudents().getAdress());
                Label labelFamilyPhone_i = new Label(11, i + 1, drinkwaterList.get(i).getStudents().getFamilyphone());
                Label labelDormDesc_i = new Label(12, i + 1, drinkwaterList.get(i).getDwpay());

                if (drinkwaterList.get(i).getAdmin() == null) {
                    labelAdminName_i = new Label(13, i + 1, "");
                    labelSignTime_i = new Label(14, i + 1, "");
                } else {
                    labelAdminName_i = new Label(13, i + 1, drinkwaterList.get(i).getAdmin().getAdminname());
                    if (drinkwaterList.get(i).getSigntime() == null) {
                        labelSignTime_i = new Label(14, i + 1, "");
                    } else {
                        labelSignTime_i = new Label(14, i + 1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(drinkwaterList.get(i).getSigntime()));
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
                writableSheet.addCell(labelDormDesc_i);
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
    private int[] ImportDrinkWaterToDB(File file) {
        int[] returnnum = {0, 0};
        //获取excel文件表格中所有的数据
        List<Drinkwater> listExcel = getAllDrinkWaterByExcel(file);
        for (Drinkwater drinkwater : listExcel) {
            Drinkwater drinkwater2 = drinkwaterService.selectByStudId(drinkwater.getStudId());
            if (drinkwater2 != null) {
                if ((drinkwater2.getAdminId() == null || drinkwater2.getAdminId().equals(""))
                        && (drinkwater2.getSigntime() == null || drinkwater2.getSigntime().equals(""))) {
                    drinkwater.setSigntime(null);
                    drinkwater.setAdminId(null);
                    drinkwaterService.updateByDrinkwater(drinkwater);
                    returnnum[0]++;
                }
            }
        }
        System.out.println(returnnum);
        System.out.println(returnnum[0]);
        System.out.println(returnnum[1]);
        return returnnum;
    }

    //获取excel文件中学生资助手续办理情况
    private List<Drinkwater> getAllDrinkWaterByExcel(File file) {
        List<Drinkwater> list = new ArrayList<Drinkwater>();
        try {
            Workbook workbook = Workbook.getWorkbook(file);
            //获取sheet1
            Sheet sheet1 = workbook.getSheet(3);
            int cols = sheet1.getColumns();
            int rows = sheet1.getRows();
            Date signtime = null;
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    String studId = sheet1.getCell(j++, i).getContents();
                    String dwPay = sheet1.getCell(j++, i).getContents();
                    String adminId = sheet1.getCell(j++, i).getContents();
                    String signtimes = sheet1.getCell(j++, i).getContents();

                    if (adminId.equals("")) {
                        adminId = null;
                    }
                    if (!signtimes.equals("")) {
                        signtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(signtimes);
                    }
                    list.add(new Drinkwater(studId, dwPay, adminId, signtime));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    //    跳转到主界面
    @RequestMapping("/DrinkWaterMain.do")
    public String toMain() {
        System.out.println("----------DrinkWater.do ----------");
        return "forward:default.do";
    }

}
