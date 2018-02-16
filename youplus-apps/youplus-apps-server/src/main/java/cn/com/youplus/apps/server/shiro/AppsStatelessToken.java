package cn.com.youplus.apps.server.shiro;

import org.apache.shiro.authc.AuthenticationToken;

import java.util.Map;

/**
 * 简化版的token校验
 * <p>User: 严汉羽
 * <p>Date: 17-12-26
 * <p>Version: 1.0
 */
public class AppsStatelessToken implements AuthenticationToken {

    private String timestamp;
    private String sign;
    private String noise;

    public AppsStatelessToken(String timestamp,
                              String sign,
                              String noise) {
        this.timestamp = timestamp;
        this.sign = sign;
        this.noise = noise;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public AppsStatelessToken setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getSign() {
        return sign;
    }

    public AppsStatelessToken setSign(String sign) {
        this.sign = sign;
        return this;
    }

    public String getNoise() {
        return noise;
    }

    public AppsStatelessToken setNoise(String noise) {
        this.noise = noise;
        return this;
    }

    @Override
    public Object getPrincipal() {
       return noise;
    }

    @Override
    public Object getCredentials() {
        return sign;
    }
}
