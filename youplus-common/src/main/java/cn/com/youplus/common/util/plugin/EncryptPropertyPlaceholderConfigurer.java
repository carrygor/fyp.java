package cn.com.youplus.common.util.plugin;

import cn.com.youplus.common.util.AESUtil;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 支持加密配置文件插件
 * Created by 严汉羽 on 2017/2/4.
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	/**
	 * 需要加密的字符串，统统放到这里来
	 */
	private String[] propertyNames = {
			"master.jdbc.password",
			"slave.jdbc.password",
			"generator.jdbc.password",
			"master.redis.password",
			"aliyun.access_key",
			"aliyun.access_secret",
			"aliyun.mq.access_key_id",
			"aliyun.mq.access_key_secret",
			"weixin.open.token",
			"weixin.open.appid",
			"weixin.open.appsecret"
	};

	/**
	 * 解密指定propertyName的加密属性值
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		for (String p : propertyNames) {
			if (p.equalsIgnoreCase(propertyName)) {
				return AESUtil.AESDecode(propertyValue);
			}
		}

		return super.convertProperty(propertyName, propertyValue);
	}

}
