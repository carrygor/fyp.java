package cn.com.youplus.cms.server.controller.admin;

import cn.com.youplus.base.api.auto.UpBaseSystemConfigService;
import cn.com.youplus.base.api.mq.MessageQueueService;
import cn.com.youplus.cms.common.form.SystemConfigAddForm;
import cn.com.youplus.cms.common.form.SystemConfigEditForm;
import cn.com.youplus.cms.common.form.SystemConfigForm;
import cn.com.youplus.cms.server.controller.SuperController;
import cn.com.youplus.common.annotation.Permission;
import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.form.BaseIdForm;
import cn.com.youplus.common.model.base.BaseResponse;
import cn.com.youplus.common.model.base.MessageQueueParams;
import cn.com.youplus.common.model.enums.MessageQueueTagEnum;
import cn.com.youplus.common.model.enums.SystemConfigParamsTypeEnum;
import cn.com.youplus.common.model.enums.SystemConfigTypeEnum;
import cn.com.youplus.common.validation.annotation.Valid;
import cn.com.youplus.model.auto.entity.base.UpBaseSystemConfig;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by 严汉羽 on 2017/10/15.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cms/v1/setting")
public class SettingController extends SuperController {

    //region 自动注入
    @Autowired
    private UpBaseSystemConfigService upBaseSystemConfigService;

    @Autowired
    private MessageQueueService messageQueueService;

    @ExceptionHandler
    @Override
    public BaseResponse exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        return super.exceptionRealHandler(request,response,exception);
    }

    //endregion

    //region 系统配置参数


    /**
     * 系统参数获取-List
     * @param form
     * @return
     * @throws AlertException
     */
    @Permission("系统设置:系统参数:列表")
    @PostMapping("/systemConfig/list")
    public BaseResponse<List> settingAdminParamsList(@Valid SystemConfigForm form) throws AlertException {
        BaseResponse<List> response = new BaseResponse<>();
        form.validType();
        List<UpBaseSystemConfig> list = upBaseSystemConfigService.selectList(
                new EntityWrapper<UpBaseSystemConfig>()
                        .eq(UpBaseSystemConfig.PARAMETER_TYPE, form.getParameterType())
                        .orderBy(UpBaseSystemConfig.SORT, true)
        );

        response.setData(list);
        return response;
    }

    /**
     * 添加参数获取-Add
     * @param form
     * @return
     * @throws AlertException
     */
    @Permission("系统设置:系统参数:添加")
    @PostMapping("/systemConfig/add")
    public BaseResponse<UpBaseSystemConfig> settingAdminParamsAdd(@Valid SystemConfigAddForm form) throws Exception {
        BaseResponse<UpBaseSystemConfig> response = new BaseResponse<>();
        form.validType();

        //检查是否存在相同的key
        if (upBaseSystemConfigService.selectCount(
                new EntityWrapper<UpBaseSystemConfig>()
                        .eq(UpBaseSystemConfig.PARAMETER_TYPE, form.getParameterType())
                        .eq(UpBaseSystemConfig.ATTRIBUTE_KEY, form.getAttributeKey())
        ) > 0) {
            throw new AlertException("已经存在相同的key:[" + form.getAttributeKey() + "]了");
        }

        UpBaseSystemConfig config = new UpBaseSystemConfig();
        config.setAddTime(new Date())
                .setUpdateTime(new Date())
                .setAttributeKey(form.getAttributeKey())
                .setAttributeValue(form.getAttributeValue())
                .setParameterType(form.getParameterType())
                .setSort(form.getSort())
                .setDescription(form.getDescription());

        if (upBaseSystemConfigService.insert(config)) {

            SystemConfigParamsTypeEnum systemConfigParamsTypeEnum = SystemConfigParamsTypeEnum.valueOf(form.getParameterType());

            SystemConfigTypeEnum mqType = null;
            switch(systemConfigParamsTypeEnum) {
                case 第三方参数: mqType = SystemConfigTypeEnum.THIRDPARTY_SYSTEM_CONFIG; break;
                case 用户参数: mqType = SystemConfigTypeEnum.APPS_SYSTEM_CONFIG; break;
                case 系统参数: mqType = SystemConfigTypeEnum.CMS_SYSTEM_CONFIG; break;
                case 网点参数: mqType = SystemConfigTypeEnum.TENANTS_SYSTEM_CONFIG; break;
            }


            MessageQueueParams messageQueueParams = new MessageQueueParams();
            messageQueueParams
                    .setTag(MessageQueueTagEnum.BROADCAST_SYSTEM_CONFIG.getType())
                    .setBody(mqType.getType());
            messageQueueService.sendMqAsync(messageQueueParams);
            response.setData(config);
        } else {
            throw new AlertException("添加失败!");
        }
        return response;
    }

    /**
     * 编辑参数获取-Edit
     * @param form
     * @return
     * @throws AlertException
     */
    @Permission("系统设置:系统参数:编辑")
    @PostMapping("/systemConfig/edit")
    public BaseResponse<UpBaseSystemConfig> settingAdminParamsEdit(@Valid SystemConfigEditForm form) throws Exception {
        BaseResponse<UpBaseSystemConfig> response = new BaseResponse<>();
        form.validType();

        //检查是否存在相同的key,需要排除自己
        if (upBaseSystemConfigService.selectCount(
                new EntityWrapper<UpBaseSystemConfig>()
                        .eq(UpBaseSystemConfig.PARAMETER_TYPE, form.getParameterType())
                        .eq(UpBaseSystemConfig.ATTRIBUTE_KEY, form.getAttributeKey())
                        .ne(UpBaseSystemConfig.ID, form.getId())
        ) > 0) {
            throw new AlertException("已经存在相同的key:[" + form.getAttributeKey() + "]了");
        }

        UpBaseSystemConfig config = new UpBaseSystemConfig();
        config.setId(form.getId())
                .setUpdateTime(new Date())
                .setAttributeKey(form.getAttributeKey())
                .setAttributeValue(form.getAttributeValue())
                .setParameterType(form.getParameterType())
                .setSort(form.getSort())
                .setDescription(form.getDescription());

        if (upBaseSystemConfigService.updateById(config)) {
            MessageQueueParams messageQueueParams = new MessageQueueParams();
            messageQueueParams
                    .setTag(MessageQueueTagEnum.BROADCAST_SYSTEM_CONFIG.getType())
                    .setBody(form.getParameterType());
            messageQueueService.sendMqAsync(messageQueueParams);
            response.setData(config);
        } else {
            throw new AlertException("修改失败，请稍后重试!");
        }
        return response;
    }

    /**
     * 删除参数获取-Delete
     * @param form
     * @return
     * @throws AlertException
     */
    @Permission("系统设置:系统参数:删除")
    @PostMapping("/systemConfig/delete")
    public BaseResponse<String> settingAdminParamsDelete(@Valid BaseIdForm form) throws AlertException {
        BaseResponse<String> response = new BaseResponse<>();

        //检查是否存在相同的key,需要排除自己
        if (upBaseSystemConfigService.deleteById(form.getId())) {
            response.setData("删除成功!");
        } else {
            throw new AlertException("删除失败，请稍后重试!");
        }
        return response;
    }

    //endregion

}
