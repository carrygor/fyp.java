package cn.com.youplus.apps.server.service.impl;

import cn.com.youplus.apps.server.service.AliyunOssService;
import cn.com.youplus.common.model.base.BaseResponse;
import cn.com.youplus.common.model.enums.ResponseEnum;
import cn.com.youplus.common.model.resources.AliyunProperties;
import cn.com.youplus.common.util.StringHelper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.mts.model.v20140618.SubmitJobsRequest;
import com.aliyuncs.mts.model.v20140618.SubmitJobsResponse;
import com.aliyuncs.mts.model.v20140618.SubmitSnapshotJobRequest;
import com.aliyuncs.mts.model.v20140618.SubmitSnapshotJobResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 严汉羽 on 2017/6/29.
 */
@Service("aliyunOssService")
public class AliyunOssServiceImpl implements AliyunOssService {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AliyunOssServiceImpl.class);

    @Autowired
    private AliyunProperties aliyunProperties;

    //定义一些支持的文件格式
    private Set<String> fileFormatSet = new HashSet<String>(){
        {
            //图片格式
            add(".png");
            add(".jpg");
            add(".bmp");
            add(".gif");
            //视频格式
            add(".amr");
            add(".mov");
            add(".mp4");
            add(".flv");
            add(".xlsx");
            add(".xls");
        }
    };

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
        }
    };

    private Set<String> audioFormatSet = new HashSet<String>(){
        {
            //视频格式
            add(".mp3");
            add(".aac");
            add(".amr");
        }
    };

    private boolean inSet(String suffix, Set<String> set) {
        if (set.contains(suffix.toLowerCase())) {
            return true;
        }
        return false;
    }


    //region 阿里云转码
    @Override
    public String submitMtsTask(String key, String suffix, String prefix) throws ClientException {
        if (inSet(suffix, audioFormatSet)) {
            SubmitJobsRequest sub = new SubmitJobsRequest();

            JSONObject input = new JSONObject();
            input.put("Bucket",aliyunProperties.getAliyunOssBucket());
            input.put("Location",aliyunProperties.getAliyunOssLocation());
            input.put("Object",key);

            JSONArray outputs = new JSONArray();
            JSONObject output = new JSONObject();

            output.put("OutputObject", prefix + ".mp3");
            output.put("TemplateId", "97253c0abc9a8db7e458469ead5702fe"); //mp3

            outputs.add(output);

            logger.debug("oss->" + input.toJSONString());
            logger.debug("oss->" + outputs.toJSONString());

            sub.setInput(input.toJSONString());
            sub.setOutputs(outputs.toJSONString());
            sub.setOutputLocation(aliyunProperties.getAliyunOssLocation());
            sub.setOutputBucket(aliyunProperties.getAliyunOssBucket());
            sub.setPipelineId(aliyunProperties.getAliyunOssPipeLine());

            DefaultProfile.addEndpoint(
                    aliyunProperties.getAliyunOssRegion(),
                    aliyunProperties.getAliyunOssRegion(),
                    "mts","mts.cn-shenzhen.aliyuncs.com");
            IClientProfile profile = DefaultProfile.getProfile(
                    aliyunProperties.getAliyunOssRegion(),
                    aliyunProperties.getAliyunMqAccessKeyId(),
                    aliyunProperties.getAliyunMqAccessKeySecret());
            IAcsClient client = new DefaultAcsClient(profile);
            SubmitJobsResponse respone = client.getAcsResponse(sub);
            logger.debug(respone.getJobResultList().get(0).getMessage());
            return null;
        } else if (inSet(suffix, videoFormatSet)) {
            String region = aliyunProperties.getAliyunOssRegion();
            String accessKeyId = aliyunProperties.getAliyunMqAccessKeyId();
            String accessKeySecret = aliyunProperties.getAliyunMqAccessKeySecret();
            String pipelineId = aliyunProperties.getAliyunOssPipeLine();

            String inputBucket=aliyunProperties.getAliyunOssBucket();
            String ossLocation = aliyunProperties.getAliyunOssLocation();
            String inputObject = key;
            String outputBucket= aliyunProperties.getAliyunOssBucket();
            String outputObject = prefix + ".jpg";
            String time = "5";
            String width="300";
            String height="300";

            DefaultProfile profile = DefaultProfile.getProfile(region, accessKeyId, accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);

            SubmitSnapshotJobRequest request=new SubmitSnapshotJobRequest();

            request.setInput(generateSnapshotInput(ossLocation,inputBucket,inputObject).toJSONString());
            request.setSnapshotConfig(generateSingleSnapshotConfig(ossLocation,outputBucket,outputObject,time,
                    width,height).toJSONString());
            request.setPipelineId(pipelineId);

            SubmitSnapshotJobResponse response=client.getAcsResponse(request);
            SubmitSnapshotJobResponse.SnapshotJob job=response.getSnapshotJob();
            logger.info(job.toString());
            return prefix + ".jpg";
        }
        return null;
    }

    private static JSONObject generateSingleSnapshotConfig(String osslocation, String outputbucket,
                                                     String outputobject,String time,  String width, String height) {
        JSONObject snapshotConfig = new JSONObject();
        JSONObject output = generateSnapshotInput(osslocation,outputbucket,outputobject);
        snapshotConfig.put("OutputFile", output);
        snapshotConfig.put("Time", time);
        //snapshotConfig.put("FrameType", frametype);
        snapshotConfig.put("Width", width);
        snapshotConfig.put("Height", height);
        return snapshotConfig;
    }

    private static JSONObject generateMultiSnapshotConfig(String osslocation, String outputbucket,
                                                           String outputobject,String time, String interval, String num,
                                                           String frametype, String width, String height) {
        JSONObject snapshotConfig = new JSONObject();
        JSONObject output = generateSnapshotInput(osslocation,outputbucket,outputobject);
        snapshotConfig.put("OutputFile", output);
        snapshotConfig.put("Time", time);
        snapshotConfig.put("Interval", interval);
        snapshotConfig.put("Num", num);
        snapshotConfig.put("FrameType", frametype);
        snapshotConfig.put("Width", width);
        snapshotConfig.put("Height", height);
        return snapshotConfig;
    }

    private static JSONObject generateSnapshotInput(String ossLocation,
                                                    String inputBucket, String inputObject){
        JSONObject input = new JSONObject();
        input.put("Location", ossLocation);
        input.put("Bucket", inputBucket);
        input.put("Object", inputObject);
        return input;
    }

    //endregion

    @Autowired
    private OSSClient ossClient;

    @Override
    public BaseResponse<String> uploadOneFile(InputStream is, String suffix) {
        BaseResponse<String> response = new BaseResponse<>();
        if(null == is){
            response.setErrcode(ResponseEnum.INVALID_INPUT_STREAM.getCode());
            response.setErrmsg(ResponseEnum.INVALID_INPUT_STREAM.getErrMsg());
            return response;
        }
        if (!suffix.startsWith(".")) {
            suffix = "." + suffix;
        }
        if (!fileFormatSet.contains(suffix.toLowerCase())) {
            response.setErrcode(ResponseEnum.INVALID_FILE_FORMAT.getCode());
            response.setErrmsg(ResponseEnum.INVALID_FILE_FORMAT.getErrMsg() + ":" + suffix);
            return response;
        }

        String key,prefix, previewImg = null;
        try {
            prefix = StringHelper.getImgName();
            key = prefix + suffix;
            logger.debug("阿里云OSS->" + key);

            ossClient.putObject(aliyunProperties.getAliyunOssBucket(), key, is);
            previewImg = submitMtsTask(key, suffix, prefix);
        } catch (Exception e) {
            logger.error("阿里云OSS->上传图片信息发生错误:",e);
            response.setErrcode(ResponseEnum.ERROR.getCode());
            response.setErrmsg(ResponseEnum.ERROR.getErrMsg());
            return null;
        }
        response.setData(prefix + suffix);
        return response;
    }
}
