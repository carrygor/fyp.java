package cn.com.youplus.common.model.base;

import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.util.Constants;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 严汉羽 on 2017/7/10.
 */
public class WeixinOauthBaseInfo implements Serializable {
    /**
     * 序列
     */
    private static final long serialVersionUID = 1L;

    private String accessToken;

    private int expiresIn;

    private Date expiresTime;

    private Date atExpiresTime;

    private String openid;

    private String unionid;

    private String scope;

    public void setExpiresTime(Date expiresTime) {
        this.expiresTime = expiresTime;
    }

    public void setAtExpiresTime(Date atExpiresTime) {
        this.atExpiresTime = atExpiresTime;
    }

    private String refreshToken;

    public WeixinOauthBaseInfo(){

    }

    public WeixinOauthBaseInfo(JSONObject jsonObject) throws AlertException {
        if (jsonObject.getIntValue("errcode") > 0) {
            throw new AlertException(jsonObject.getString("errmsg"));
        }
        this.accessToken = jsonObject.getString("access_token");
        this.expiresIn = jsonObject.getInteger("expires_in");
        this.openid = jsonObject.getString("openid");
        this.unionid = jsonObject.getString("unionid");
        this.refreshToken = jsonObject.getString("refresh_token");
        this.scope = jsonObject.getString("scope");
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresTime = new Date(new Date().getTime() + 1000 * expiresIn);
        this.atExpiresTime = new Date(new Date().getTime() + 29 * Constants.ONE_DAY);
        this.expiresIn = expiresIn;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getExpiresTime() {
        return expiresTime;
    }

    public Date getAtExpiresTime() {
        return atExpiresTime;
    }

    public String getScope() {
        return scope;
    }

    public WeixinOauthBaseInfo setScope(String scope) {
        this.scope = scope;
        return this;
    }
}
