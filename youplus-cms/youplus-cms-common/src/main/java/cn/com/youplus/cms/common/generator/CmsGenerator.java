package cn.com.youplus.cms.common.generator;

import cn.com.youplus.common.generator.AutoGeneratorHelper;
import cn.com.youplus.common.util.AESUtil;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 严汉羽 on 2017/10/11.
 */
public class CmsGenerator {
    /**
     * 生成CMS的模板
     */
    public static void main(String []args) {
        //thirdparty 第三方接口
        AutoGeneratorHelper.generator(
                "cms",
                new String [] {},
                AutoGeneratorHelper.CMS_TABLES
                );
    }

}
