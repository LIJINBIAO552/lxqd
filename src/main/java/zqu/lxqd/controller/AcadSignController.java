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
import zqu.lxqd.bean.Acadsign;
import zqu.lxqd.bean.Admin;
import zqu.lxqd.bean.Department;
import zqu.lxqd.bean.Students;
import zqu.lxqd.service.AcadsignService;
import zqu.lxqd.service.AdminService;

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
@RequestMapping("admin/Acadsign/")
public class AcadSignController {

    @Autowired
    @Qualifier("AcadsignService")
    private AcadsignService acadsignService;

    public void setAcadsignService(AcadsignService acadsignService) {
        this.acadsignService = acadsignService;
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
     * "1":根据学号查二级学院盖章情况
     * "2":根据学院查二级学院盖章情况
     * "3":根据专业查二级学院盖章情况
     * "4":根据班级查二级学院盖章情况
     * "5":根据年级查二级学院盖章情况
     * "6":根据姓名查二级学院盖章情况
     * "7":根据性别查二级学院盖章情况
     * "8":
     * "9":根据经办人查二级学院盖章情况
     * "all":查询所有信息
     */
    static String mode = "all";
    //查询参数
    static String selectParam;

    @RequestMapping(value = "/SelectAcadSign.do",produces = "text/html;charset=utf-8")
    public String doSelectAcadsign(String condition,
                                   @RequestParam(value = "condiValue",defaultValue = " ") String condiValue) {

        mode = condition;
        selectParam = condiValue;
        return "forward:distributionAcadSign.do";
    }
    /**
     * 执行这个请求时，会将查询条件设置为"all",查询所有宿舍办理情况
     */
    @RequestMapping("default.do")
    public String doDefault() {
        mode = "all";
        return "distributionAcadSign.do";
    }
    /**
     * 根据学生信息查询显示学生宿舍办理情况
     *
     * @param pn
     * @param session
     */
    @RequestMapping(value = "distributionAcadSign.do", produces = "text/html;charset=utf-8")
    public String doDistributionAcadSign(@RequestParam(value = "pn", defaultValue = "1") Integer pn, HttpSession session) {
        int pageSize = 8;
        Admin admin = (Admin) session.getAttribute("admin");
        Students studentstemp = new Students();
        studentstemp.setDepartId(admin.getDepartid());
        if (mode.equals("all")){
            PageHelper.startPage(pn, pageSize);
            List<Acadsign> acadsigns = acadsignService.simpleSelectByStudents(studentstemp);
            PageInfo page = new PageInfo(acadsigns, 5);
            session.setAttribute("pageInfo", page);
            return "redirect:/admin/AcadSign/AcadSign.jsp";
        }else if (Integer.valueOf(mode) >= 1 && Integer.valueOf(mode) <= 7) {
            if (mode.equals("1")) {
                studentstemp.setStudid(selectParam);
            } else if (mode.equals("2")) {
                Department department = new Department();
                department.setDepartname(selectParam);
                studentstemp.setDepartment(department);
            } else if (mode.equals("3")) {
                studentstemp.setMajor(selectParam);
            } else if (mode.equals("4")) {
                studentstemp.setSclass(selectParam);
            } else if (mode.equals("5")) {
                studentstemp.setEducation(selectParam);
            } else if (mode.equals("6")) {
                studentstemp.setStudname(selectParam);
            } else if (mode.equals("7")) {
                studentstemp.setStudsex(selectParam);
            }
            // 引入PageHelper分页插件
            // 在查询之前只需要调用，传入页码，以及每页的大小
            PageHelper.startPage(pn, pageSize);
            // startPage后面紧跟的这个查询就是一个分页查询
            List<Acadsign> acadsigns = acadsignService.simpleSelectByStudents(studentstemp);
            // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
            // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
            PageInfo page = new PageInfo(acadsigns, 5);
            //model.addAttribute("pageInfo", page);
            session.setAttribute("pageInfo", page);
            return "redirect:/admin/AcadSign/AcadSign.jsp";
        }else if (mode.equals("8")) {
            PageHelper.startPage(pn, pageSize);
            Acadsign acadsign = new Acadsign();
            acadsign.setAdmin(null);
            acadsign.setItemborrdesc(selectParam);
            List<Acadsign> acadsigns = acadsignService.selectAcadsignByAcadsign(acadsign);
            PageInfo page = new PageInfo(acadsigns, 5);
            session.setAttribute("pageInfo", page);
            return "redirect:/admin/AcadSign/AcadSign.jsp";
        }  else if (mode.equals("9")) {
            PageHelper.startPage(pn, pageSize);
            Acadsign acadsign = new Acadsign();
            acadsign.setAdminId(selectParam);
            List<Acadsign> acadsigns = acadsignService.selectAcadsignByAcadsign(acadsign);
            PageInfo page = new PageInfo(acadsigns, 5);
            session.setAttribute("pageInfo", page);
            return "redirect:/admin/AcadSign/AcadSign.jsp";
        }
        return "redirect:/admin/AcadSign/AcadSign.jsp";
    }

    /**
     * 签名确认
     */
    @RequestMapping(value = "/AcadSignSign.do", produces = "text/html;charset=utf-8")
    public String doAcadSignSign(String studId, String adminid, HttpSession session) throws ParseException {
        //获取签名人
        Admin admin_acadsign = (Admin) adminService.selectByAdminId(adminid);
        //获取该表情况
        Acadsign acadsign = acadsignService.selectByStudId(studId);
        if (acadsign.getSigntime() != null || acadsign.getSigntime() != null ){
            return "forward:distributionAcadSign.do";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        acadsign.setStudId(studId);
        acadsign.setSigntime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
        acadsign.setAdminId(admin_acadsign.getAdminid());
        acadsign.setItemborrdesc("已签名盖章");
        if (acadsignService.updateByStuId(acadsign) >= 0){
            acadsign = null;
            return "forward:distributionAcadSign.do";
        }else{
            acadsign = null;
            return "forward:distributionAcadSign.do";
        }
    }

    /**
     * 一键签名确认
     */
    @RequestMapping(value = "/AcadSignSignAll.do", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String doAcadSignSignAll(String studId, String adminid, HttpSession session) throws ParseException {
        int count = 0;
        //获取签名人
        Admin admin_acadsign1 = (Admin) adminService.selectByAdminId(adminid);
        //获取该表情况
        Students studentstemp = new Students();
        studentstemp.setDepartId(admin_acadsign1.getDepartid());
        List<Acadsign> acadsignList = acadsignService.simpleSelectByStudents(studentstemp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        for (Acadsign acadsign1:acadsignList) {
            if (acadsign1.getSigntime() == null && acadsign1.getAdmin() == null
                && acadsign1.getSuppcent() != null && acadsign1.getDormmana() != null
                    && acadsign1.getStudaffadivi() != null && acadsign1.getDrinkwater() != null
                    && acadsign1.getHydropower() != null && acadsign1.getEducandcomp() != null
                    && acadsign1.getHotwater1() != null && acadsign1.getHotwater2() != null
                    && acadsign1.getLibrary() != null && acadsign1.getLogimana() != null){

                acadsign1.setStudId(acadsign1.getStudId());
                acadsign1.setItemborrdesc("已签名盖章");
                acadsign1.setSigntime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
                acadsign1.setAdminId(admin_acadsign1.getAdminid());
                acadsignService.updateByStuId(acadsign1);
                count++;
            }
        }
        return String.valueOf(count);
    }


    /**
     * 通过excel文件将学生宿舍手续办理情况导入数据库
     */
    @RequestMapping(value = "/ImportAcadSignToDB.do", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String doImportAcadSignToDB(MultipartFile multipartFile, HttpSession session) throws IOException {

        CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) multipartFile;
        DiskFileItem diskFileItem = (DiskFileItem) commonsMultipartFile.getFileItem();
        File file = diskFileItem.getStoreLocation();

        int[] importResult = ImportAcadSignToDB(file);

        return "导入了："+importResult[1]+"条记录；其中更新了："+importResult[0]+"位学生的数据";
    }

    //将学生借用学院物品情况情况从数据库以*.xls格式导出到本地文件
    @RequestMapping(value = "/ExportAcadSignFromDB.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> doExportAcadSignFromDB(String filePath, HttpSession session) throws IOException {

        Admin admin = (Admin)session.getAttribute("admin");
        Map<String,String> result = new HashMap<String, String>();
        String fileDir = null;
        if (filePath.equals("123")) {
            fileDir = "D:\\lxqd\\download\\AcadSign";
            filePath = fileDir + "\\" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "" + (int) Math.random() * 1000 + ".xls";
        }
        if (!new File(fileDir).exists()) {
            new File(fileDir).mkdir();
        }
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        Students students = new Students();
        students.setDepartId(admin.getDepartid());
        String resultcount = exportAcadSignFromDB(file,students);
        result.put("path",filePath);
        result.put("resultcount",resultcount);
        return result;
    }


    //导出学生借用学院物品情况情况到本地文件
    private String exportAcadSignFromDB(File file,Students students) {
        int count =0;
        try {
            System.out.println("exportAcadSignFromDB..." + file);
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
            List<Acadsign> acadsignList = acadsignService.simpleSelectByStudents(students);

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
                if (acadsignList.get(i).getSealer() == null || acadsignList.get(i).getSealer() == ""){
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

    //导入学生借用学院物品情况情况到数据库
    private int[] ImportAcadSignToDB(File file) {
        int[] returnnum = {0,0};
        //获取excel文件表格中所有的数据
        List<Acadsign> listExcel = getAllAcadSignByExcel(file);
        for (Acadsign acadsign : listExcel) {
            Acadsign acadsign2 = acadsignService.selectByStudId(acadsign.getStudId());
            if (acadsign2 != null){
                if ((acadsign2.getAdminId() == null || acadsign2.getAdminId().equals(""))
                        && (acadsign2.getSigntime() == null || acadsign2.getSigntime().equals(""))){
                    acadsign.setSealer(null);
                    acadsign.setSealtime(null);
                    acadsign.setAdminId(null);
                    acadsign.setSigntime(null);
                    acadsignService.updateByStuId(acadsign);
                    returnnum[0]++;
                }
            }
        }
        return returnnum;
    }

    //获取excel文件中学生借用学院物品情况情况
    private List<Acadsign> getAllAcadSignByExcel(File file) {
        List<Acadsign> list = new ArrayList<Acadsign>();
        try {
            Workbook workbook = Workbook.getWorkbook(file);
            //获取sheet1
            Sheet sheet1 = workbook.getSheet(8);
            int cols = sheet1.getColumns();
            int rows = sheet1.getRows();
            Date signtime = null;
            Admin admin1 = null;
            String adminId = null;
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    String studId = sheet1.getCell(j++, i).getContents();
                    String itemborrdesc = sheet1.getCell(j++, i).getContents();
                    String adminName = sheet1.getCell(j++, i).getContents();
                    String signtimes = sheet1.getCell(j++, i).getContents();
                    if (adminName.equals("")){
                        adminId = null;
                    }else {
                        admin1.setAdminname(adminName);
                        Admin admin2 = (Admin) adminService.selectAdminByAdmin(admin1);
                        adminId = admin2.getAdminid();
                    }
                    if (!signtimes.equals("")) {
                        signtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(signtimes);
                    }
                    list.add(new Acadsign(studId,itemborrdesc, adminId, signtime));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    //    跳转到主界面
    @RequestMapping("/AcadSignMain.do")
    public String toMain() {
        System.out.println("----------AcadSign.do ----------");
        return "forward:default.do";
    }

}
