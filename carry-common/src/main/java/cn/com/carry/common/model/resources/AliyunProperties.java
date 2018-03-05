package cn.com.carry.common.model.resources;

/**
 * Created by 严汉羽 on 2017/10/19.
 */
public class AliyunProperties {

    private String aliyunOssBucket;
    private String aliyunOssPipeLine;
    private String aliyunOssRegion;
    private String aliyunOssLocation;
    private String aliyunOssVideoTemplate;
    private String aliyunOssAudioTemplate;
    private String aliyunOssEndpoint;

    private String aliyunMqAccessKeyId;
    private String aliyunMqAccessKeySecret;
    private String aliyunMqBlockOrderTopicName;
    private String aliyunMqNoneOrderTopicName;
    private String aliyunMqProviderId;
    private String aliyunMqConsumerId;

    //
    private String aliyunTableStorageEdpoint;

    private String aliyunTableStorageInstance;

    private String aliyunLogEndpoint;

    private String aliyunLogProjectName;

    public String getAliyunMqAccessKeyId() {
        return aliyunMqAccessKeyId;
    }

    public void setAliyunMqAccessKeyId(String aliyunMqAccessKeyId) {
        this.aliyunMqAccessKeyId = aliyunMqAccessKeyId;
    }

    public String getAliyunMqAccessKeySecret() {
        return aliyunMqAccessKeySecret;
    }

    public void setAliyunMqAccessKeySecret(String aliyunMqAccessKeySecret) {
        this.aliyunMqAccessKeySecret = aliyunMqAccessKeySecret;
    }

    public String getAliyunMqBlockOrderTopicName() {
        return aliyunMqBlockOrderTopicName;
    }

    public void setAliyunMqBlockOrderTopicName(String aliyunMqBlockOrderTopicName) {
        this.aliyunMqBlockOrderTopicName = aliyunMqBlockOrderTopicName;
    }

    public String getAliyunMqNoneOrderTopicName() {
        return aliyunMqNoneOrderTopicName;
    }

    public void setAliyunMqNoneOrderTopicName(String aliyunMqNoneOrderTopicName) {
        this.aliyunMqNoneOrderTopicName = aliyunMqNoneOrderTopicName;
    }

    public String getAliyunMqProviderId() {
        return aliyunMqProviderId;
    }

    public void setAliyunMqProviderId(String aliyunMqProviderId) {
        this.aliyunMqProviderId = aliyunMqProviderId;
    }

    public String getAliyunMqConsumerId() {
        return aliyunMqConsumerId;
    }

    public void setAliyunMqConsumerId(String aliyunMqConsumerId) {
        this.aliyunMqConsumerId = aliyunMqConsumerId;
    }

    public String getAliyunOssBucket() {
        return aliyunOssBucket;
    }

    public void setAliyunOssBucket(String aliyunOssBucket) {
        this.aliyunOssBucket = aliyunOssBucket;
    }

    public String getAliyunOssPipeLine() {
        return aliyunOssPipeLine;
    }

    public void setAliyunOssPipeLine(String aliyunOssPipeLine) {
        this.aliyunOssPipeLine = aliyunOssPipeLine;
    }

    public String getAliyunOssRegion() {
        return aliyunOssRegion;
    }

    public void setAliyunOssRegion(String aliyunOssRegion) {
        this.aliyunOssRegion = aliyunOssRegion;
    }

    public String getAliyunOssLocation() {
        return aliyunOssLocation;
    }

    public void setAliyunOssLocation(String aliyunOssLocation) {
        this.aliyunOssLocation = aliyunOssLocation;
    }

    public String getAliyunOssVideoTemplate() {
        return aliyunOssVideoTemplate;
    }

    public void setAliyunOssVideoTemplate(String aliyunOssVideoTemplate) {
        this.aliyunOssVideoTemplate = aliyunOssVideoTemplate;
    }

    public String getAliyunOssAudioTemplate() {
        return aliyunOssAudioTemplate;
    }

    public void setAliyunOssAudioTemplate(String aliyunOssAudioTemplate) {
        this.aliyunOssAudioTemplate = aliyunOssAudioTemplate;
    }

    public String getAliyunOssEndpoint() {
        return aliyunOssEndpoint;
    }

    public void setAliyunOssEndpoint(String aliyunOssEndpoint) {
        this.aliyunOssEndpoint = aliyunOssEndpoint;
    }

    public String getOssImgUrl(String key) {
        return "http://" + getAliyunOssBucket() + ".oss-cn-shenzhen.aliyuncs.com/" + key + "?x-oss-process=style/comp";
    }

    public String getAliyunTableStorageEdpoint() {
        return aliyunTableStorageEdpoint;
    }

    public AliyunProperties setAliyunTableStorageEdpoint(String aliyunTableStorageEdpoint) {
        this.aliyunTableStorageEdpoint = aliyunTableStorageEdpoint;
        return this;
    }

    public String getAliyunTableStorageInstance() {
        return aliyunTableStorageInstance;
    }

    public AliyunProperties setAliyunTableStorageInstance(String aliyunTableStorageInstance) {
        this.aliyunTableStorageInstance = aliyunTableStorageInstance;
        return this;
    }

    public String getAliyunLogEndpoint() {
        return aliyunLogEndpoint;
    }

    public void setAliyunLogEndpoint(String aliyunLogEndpoint) {
        this.aliyunLogEndpoint = aliyunLogEndpoint;
    }

    public String getAliyunLogProjectName() {
        return aliyunLogProjectName;
    }

    public void setAliyunLogProjectName(String aliyunLogProjectName) {
        this.aliyunLogProjectName = aliyunLogProjectName;
    }
}
