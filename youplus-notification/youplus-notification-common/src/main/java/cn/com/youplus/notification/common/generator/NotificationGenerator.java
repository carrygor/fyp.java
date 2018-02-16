package cn.com.youplus.notification.common.generator;

import cn.com.youplus.common.generator.AutoGeneratorHelper;

/**
 * Created by 严汉羽 on 2017/10/11.
 */
public class NotificationGenerator {
    /**
     * 生成CMS的模板
     */
    public static void main(String []args) {
        //thirdparty 第三方接口
        AutoGeneratorHelper.generator(
                "notification",
                new String [] {},
                AutoGeneratorHelper.NOTIFICATION_TABLES
                );
    }

}
