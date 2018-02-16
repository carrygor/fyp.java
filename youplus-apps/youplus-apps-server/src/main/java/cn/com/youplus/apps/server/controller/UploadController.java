package cn.com.youplus.apps.server.controller;

import cn.com.youplus.apps.server.service.AliyunOssService;
import cn.com.youplus.base.common.QRCode.QRCodeUtil;
import cn.com.youplus.common.exception.data.InvalidParameterException;
import cn.com.youplus.common.model.base.BaseResponse;
import com.google.zxing.WriterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 严汉羽 on 2017/6/28.
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/apps/v1/upload")
public class UploadController extends SuperController {

    private static Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AliyunOssService aliyunOssService;

    /**
     * 统一异常处理
     * @param request
     * @param response
     * @param exception
     */
    @ExceptionHandler(value = Throwable.class)
    public BaseResponse exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        return super.exceptionRealHandler(request, response, exception);
    }

    /**
     * 图片/视频上传,一定要将文件命名为 file
     * @return
     */
    @PostMapping("/picture")
    public BaseResponse ossUploadPicture(@RequestParam("file") MultipartFile file) {
        BaseResponse ret = new BaseResponse();
        try {
            // 获取上传文件的路径
            String uploadFilePath = file .getOriginalFilename();
            System.out.println("uploadFlePath:" + uploadFilePath);
            // 截取上传文件的文件名
            String uploadFileName = uploadFilePath.substring(uploadFilePath.lastIndexOf('\\') + 1, uploadFilePath.indexOf('.'));
            String ext = uploadFilePath.substring(uploadFilePath.lastIndexOf("."), uploadFilePath.length());

            // 截取上传文件的后缀
            String uploadFileSuffix = uploadFilePath.substring(
                    uploadFilePath.indexOf('.') + 1, uploadFilePath.length());
            System.out.println("uploadFileSuffix:" + uploadFileSuffix);
            InputStream is = null;

            if (!ext.startsWith(".")) {
                ext = "." + ext;
            }

            if (!pictureFormatSet.contains(ext.toLowerCase())) {
                throw new InvalidParameterException("不支持的视频格式");
            }

            try {
                is = file.getInputStream();
                BaseResponse response = aliyunOssService.uploadOneFile(is, ext);
                return response;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }



    private Set<String> pictureFormatSet = new HashSet<String>(){
        {
            //视频格式
            add(".png");
            add(".jpg");
            add(".bmp");
            add(".gif");
        }
    };

    private Set<String> videoFormatSet = new HashSet<String>(){
        {
            //视频格式
            add(".mp4");
            add(".mov");
            add(".rm");
            add(".rmvb");
            add(".wmv");
            add(".avi");
            add(".mkv");
            add(".3gp");
            add(".flv");
            //mp4|mov|rm|rmvb|wmv|avi|mkv|3gp|flv
        }
    };


    /**
     * 图片/视频上传,一定要将文件命名为 file
     * @return
     */
    @PostMapping("/video")
    public BaseResponse ossUploadVideo(@RequestParam("file") MultipartFile file) {
        BaseResponse ret = new BaseResponse();

        try {
            // 获取上传文件的路径
            String uploadFilePath = file .getOriginalFilename();
            System.out.println("uploadFlePath:" + uploadFilePath);
            // 截取上传文件的文件名
            String uploadFileName = uploadFilePath.substring(uploadFilePath.lastIndexOf('\\') + 1, uploadFilePath.indexOf('.'));
            String ext = uploadFilePath.substring(uploadFilePath.lastIndexOf("."), uploadFilePath.length());

            // 截取上传文件的后缀
            String uploadFileSuffix = uploadFilePath.substring(
                    uploadFilePath.indexOf('.') + 1, uploadFilePath.length());
            System.out.println("uploadFileSuffix:" + uploadFileSuffix);
            InputStream is = null;

            if (!ext.startsWith(".")) {
                ext = "." + ext;
            }

            if (!videoFormatSet.contains(ext.toLowerCase())) {
                throw new InvalidParameterException("不支持的视频格式");
            }

            try {
                is = file.getInputStream();

                BaseResponse response = aliyunOssService.uploadOneFile(is, ext);
                return response;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }


    @PostMapping("/qrcode")
    public BaseResponse testSucc() throws IOException, WriterException {
        BaseResponse response = new BaseResponse();

        String format = "png";// 图像类型
        BufferedImage bufferedImage = QRCodeUtil.EncodeUrlToBufferedImage("http://www.baidu.com", 256);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, format, os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());

        aliyunOssService.uploadOneFile(is, format);

        return response;
    }

}
