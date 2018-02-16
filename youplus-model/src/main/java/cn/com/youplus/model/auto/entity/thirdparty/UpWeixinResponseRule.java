package cn.com.youplus.model.auto.entity.thirdparty;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.Version;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 严汉羽
 */
@TableName("up_weixin_response_rule")
public class UpWeixinResponseRule extends Model<UpWeixinResponseRule> implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;
	@TableField("tenants_company_id")
	private Long tenantsCompanyId;
	private String appid;
	@TableField("response_type")
	private String responseType;
	@TableField("message_type")
	private String messageType;
    /**
     * "多个关键字，以都好隔开，
如果是菜单栏回复，该值未菜单栏的值"
     */
	private String keyword;
	@TableField("match_type")
	private String matchType;
    /**
     * "回复的内容
如果是文字类型：直接是文字
如果是语音和图片
存储语音的  mediaId
如果是news
则存储对应的json
"
     */
	private String content;
    /**
     * 如果消息类型为IMGAGE或VOICE,该值为微信传回来的mediaId
     */
	@TableField("media_id")
	private String mediaId;
    /**
     * 乐观锁版本
     */
	@Version
	private Integer version;
    /**
     * 逻辑删除
     */
	@TableField("logic_delete")
    @TableLogic
	private Integer logicDelete;
    /**
     * 添加时间
     */
	@TableField("add_time")
	private Date addTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;


	public Long getId() {
		return id;
	}

	public UpWeixinResponseRule setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getTenantsCompanyId() {
		return tenantsCompanyId;
	}

	public UpWeixinResponseRule setTenantsCompanyId(Long tenantsCompanyId) {
		this.tenantsCompanyId = tenantsCompanyId;
		return this;
	}

	public String getAppid() {
		return appid;
	}

	public UpWeixinResponseRule setAppid(String appid) {
		this.appid = appid;
		return this;
	}

	public String getResponseType() {
		return responseType;
	}

	public UpWeixinResponseRule setResponseType(String responseType) {
		this.responseType = responseType;
		return this;
	}

	public String getMessageType() {
		return messageType;
	}

	public UpWeixinResponseRule setMessageType(String messageType) {
		this.messageType = messageType;
		return this;
	}

	public String getKeyword() {
		return keyword;
	}

	public UpWeixinResponseRule setKeyword(String keyword) {
		this.keyword = keyword;
		return this;
	}

	public String getMatchType() {
		return matchType;
	}

	public UpWeixinResponseRule setMatchType(String matchType) {
		this.matchType = matchType;
		return this;
	}

	public String getContent() {
		return content;
	}

	public UpWeixinResponseRule setContent(String content) {
		this.content = content;
		return this;
	}

	public String getMediaId() {
		return mediaId;
	}

	public UpWeixinResponseRule setMediaId(String mediaId) {
		this.mediaId = mediaId;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpWeixinResponseRule setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpWeixinResponseRule setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpWeixinResponseRule setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpWeixinResponseRule setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public static final String ID = "id";

	public static final String TENANTS_COMPANY_ID = "tenants_company_id";

	public static final String APPID = "appid";

	public static final String RESPONSE_TYPE = "response_type";

	public static final String MESSAGE_TYPE = "message_type";

	public static final String KEYWORD = "keyword";

	public static final String MATCH_TYPE = "match_type";

	public static final String CONTENT = "content";

	public static final String MEDIA_ID = "media_id";

	public static final String VERSION = "version";

	public static final String LOGIC_DELETE = "logic_delete";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpWeixinResponseRule{" +
			"id=" + id +
			", tenantsCompanyId=" + tenantsCompanyId +
			", appid=" + appid +
			", responseType=" + responseType +
			", messageType=" + messageType +
			", keyword=" + keyword +
			", matchType=" + matchType +
			", content=" + content +
			", mediaId=" + mediaId +
			", version=" + version +
			", logicDelete=" + logicDelete +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			"}";
	}
}
