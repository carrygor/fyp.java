package cn.com.youplus.tenants.common.generator;

import cn.com.youplus.common.generator.AutoGeneratorHelper;

/**
 * Created by 严汉羽 on 2017/10/11.
 */
public class ThirdpartyGenerator {
    /**
     * 生成CMS的模板
     */
    public static void main(String []args) {
        //thirdparty 第三方接口
        AutoGeneratorHelper.generator(
                "thirdparty",
                new String [] {},
                AutoGeneratorHelper.THIRDPARTY_TABLES
                );
    }

}
