package cn.com.youplus.apps.server.service;

import cn.com.youplus.common.model.base.BaseResponse;
import com.aliyuncs.exceptions.ClientException;

import java.io.InputStream;

/**
 * Created by 严汉羽 on 2017/6/29.
 */
public interface AliyunOssService {
    //region 阿里云转码
    String submitMtsTask(String key, String suffix, String prefix) throws ClientException;

    BaseResponse uploadOneFile(InputStream is, String suffix);
}
