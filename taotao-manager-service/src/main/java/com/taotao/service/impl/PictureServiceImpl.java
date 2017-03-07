package com.taotao.service.impl;

import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.FastDFSClient;
import com.taotao.service.PictureService;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * Created by duai on 2017/3/7.
 */
@Service
public class PictureServiceImpl implements PictureService {
    @Value("${IMAGE_SERVER_BASE_URL}")
    private String IMAGE_SERVER_BASE_URL;
    Logger logger = Logger.getLogger(PictureServiceImpl.class);
    @Override
    public PictureResult uploadPic(MultipartFile picFile) {
        PictureResult pictureResult = new PictureResult();
        if (picFile.isEmpty()){
            pictureResult.setError(1);
            pictureResult.setMessage("图片为空");
            return  pictureResult;
        }
        try {
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:/resource/client.conf");
            String extName = picFile.getOriginalFilename().substring(picFile.getOriginalFilename().lastIndexOf(".")+1);
            String resultUrl = fastDFSClient.uploadFile(picFile.getBytes(),extName);
            pictureResult.setError(0);
            pictureResult.setUrl(IMAGE_SERVER_BASE_URL+resultUrl);
            logger.info(IMAGE_SERVER_BASE_URL+resultUrl);
        } catch (Exception e) {
            pictureResult.setError(1);
            pictureResult.setMessage("图片上传失败");
            e.printStackTrace();
        }
        return pictureResult;
    }
}
