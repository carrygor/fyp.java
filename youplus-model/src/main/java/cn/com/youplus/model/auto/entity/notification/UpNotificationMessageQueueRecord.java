package cn.com.youplus.model.auto.entity.notification;

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
@TableName("up_notification_message_queue_record")
public class UpNotificationMessageQueueRecord extends Model<UpNotificationMessageQueueRecord> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增长
     */
	private Long id;
    /**
     * 消息id自己生成的uuid
     */
	@TableField("message_id")
	private String messageId;
    /**
     * topic
     */
	private String topic;
    /**
     * 生产者
     */
	private String producer;
    /**
     * 投递时间
     */
	@TableField("deliver_time")
	private Date deliverTime;
    /**
     * 消费次数
     */
	@TableField("consume_times")
	private Integer consumeTimes;
    /**
     * 标签
     */
	private String tag;
    /**
     * 内容
     */
	private String body;
    /**
     * 消费结果
     */
	@TableField("consume_result")
	private String consumeResult;
    /**
     * 自己生成的uuid
     */
	@TableField("message_key")
	private String messageKey;
    /**
     * 发送结果
     */
	@TableField("send_result")
	private String sendResult;
    /**
     * 乐观锁
     */
	@Version
	private Integer version;
    /**
     * 逻辑删除
     */
	@TableField("logic_delete")
    @TableLogic
	private Integer logicDelete;
	@TableField("add_time")
	private Date addTime;
	@TableField("update_time")
	private Date updateTime;
	@TableField("send_result_json")
	private String sendResultJson;
    /**
     * 消费结果记录
     */
	@TableField("consume_result_json")
	private String consumeResultJson;


	public Long getId() {
		return id;
	}

	public UpNotificationMessageQueueRecord setId(Long id) {
		this.id = id;
		return this;
	}

	public String getMessageId() {
		return messageId;
	}

	public UpNotificationMessageQueueRecord setMessageId(String messageId) {
		this.messageId = messageId;
		return this;
	}

	public String getTopic() {
		return topic;
	}

	public UpNotificationMessageQueueRecord setTopic(String topic) {
		this.topic = topic;
		return this;
	}

	public String getProducer() {
		return producer;
	}

	public UpNotificationMessageQueueRecord setProducer(String producer) {
		this.producer = producer;
		return this;
	}

	public Date getDeliverTime() {
		return deliverTime;
	}

	public UpNotificationMessageQueueRecord setDeliverTime(Date deliverTime) {
		this.deliverTime = deliverTime;
		return this;
	}

	public Integer getConsumeTimes() {
		return consumeTimes;
	}

	public UpNotificationMessageQueueRecord setConsumeTimes(Integer consumeTimes) {
		this.consumeTimes = consumeTimes;
		return this;
	}

	public String getTag() {
		return tag;
	}

	public UpNotificationMessageQueueRecord setTag(String tag) {
		this.tag = tag;
		return this;
	}

	public String getBody() {
		return body;
	}

	public UpNotificationMessageQueueRecord setBody(String body) {
		this.body = body;
		return this;
	}

	public String getConsumeResult() {
		return consumeResult;
	}

	public UpNotificationMessageQueueRecord setConsumeResult(String consumeResult) {
		this.consumeResult = consumeResult;
		return this;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public UpNotificationMessageQueueRecord setMessageKey(String messageKey) {
		this.messageKey = messageKey;
		return this;
	}

	public String getSendResult() {
		return sendResult;
	}

	public UpNotificationMessageQueueRecord setSendResult(String sendResult) {
		this.sendResult = sendResult;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpNotificationMessageQueueRecord setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpNotificationMessageQueueRecord setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpNotificationMessageQueueRecord setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpNotificationMessageQueueRecord setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getSendResultJson() {
		return sendResultJson;
	}

	public UpNotificationMessageQueueRecord setSendResultJson(String sendResultJson) {
		this.sendResultJson = sendResultJson;
		return this;
	}

	public String getConsumeResultJson() {
		return consumeResultJson;
	}

	public UpNotificationMessageQueueRecord setConsumeResultJson(String consumeResultJson) {
		this.consumeResultJson = consumeResultJson;
		return this;
	}

	public static final String ID = "id";

	public static final String MESSAGE_ID = "message_id";

	public static final String TOPIC = "topic";

	public static final String PRODUCER = "producer";

	public static final String DELIVER_TIME = "deliver_time";

	public static final String CONSUME_TIMES = "consume_times";

	public static final String TAG = "tag";

	public static final String BODY = "body";

	public static final String CONSUME_RESULT = "consume_result";

	public static final String MESSAGE_KEY = "message_key";

	public static final String SEND_RESULT = "send_result";

	public static final String VERSION = "version";

	public static final String LOGIC_DELETE = "logic_delete";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	public static final String SEND_RESULT_JSON = "send_result_json";

	public static final String CONSUME_RESULT_JSON = "consume_result_json";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpNotificationMessageQueueRecord{" +
			"id=" + id +
			", messageId=" + messageId +
			", topic=" + topic +
			", producer=" + producer +
			", deliverTime=" + deliverTime +
			", consumeTimes=" + consumeTimes +
			", tag=" + tag +
			", body=" + body +
			", consumeResult=" + consumeResult +
			", messageKey=" + messageKey +
			", sendResult=" + sendResult +
			", version=" + version +
			", logicDelete=" + logicDelete +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			", sendResultJson=" + sendResultJson +
			", consumeResultJson=" + consumeResultJson +
			"}";
	}
}
