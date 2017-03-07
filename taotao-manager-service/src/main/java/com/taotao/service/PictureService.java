package com.taotao.service;

import com.taotao.common.pojo.PictureResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by duai on 2017/3/7.
 */
public interface PictureService {
    PictureResult uploadPic(MultipartFile picFile);
}
