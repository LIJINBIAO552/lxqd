package zqu.lxqd.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author ljb
 */
public class PhotoUtils {

    /**
     * 上传图片
     *
     * @param request
     * @param pictureFile
     * @throws IOException
     */
    public static String uploadUserPhoto(HttpServletRequest request,String path,
                                MultipartFile pictureFile) throws IOException {
        String photoPath = null;//装配后的图片地址
        //上传图片
        if (!pictureFile.isEmpty()) {
            // 使用UUID给图片重命名，并去掉四个“-”
            String name = UUID.randomUUID().toString().replaceAll("-", "");
            String nowdatetime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "";
            // 获取文件的扩展名
            String ext = FilenameUtils.getExtension(pictureFile.getOriginalFilename());
            // 设置图片上传路径
            String url = request.getSession().getServletContext().getRealPath(path);
            // 检验文件夹是否存在
            File file = new File(url);
            if (!file.exists()) {
                file.mkdir();
            }
            // 以绝对路径保存重名命后的图片
            pictureFile.transferTo(new File(url + "/" + nowdatetime + name + "." + ext));
            // 装配图片地址
            photoPath = path +"/"+ nowdatetime + name + "." + ext;
        }
        return photoPath;
    }
}