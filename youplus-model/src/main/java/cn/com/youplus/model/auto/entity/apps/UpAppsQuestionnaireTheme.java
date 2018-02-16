package cn.com.youplus.model.auto.entity.apps;

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
@TableName("up_apps_questionnaire_theme")
public class UpAppsQuestionnaireTheme extends Model<UpAppsQuestionnaireTheme> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增长
     */
	private Long id;
    /**
     * 背景图片
     */
	@TableField("bkg_img")
	private String bkgImg;
    /**
     * 图标图片
     */
	@TableField("icon_img")
	private String iconImg;
    /**
     * 主题颜色
     */
	@TableField("theme_color")
	private String themeColor;
    /**
     * 选定色
     */
	@TableField("selected_color")
	private String selectedColor;
    /**
     * 非选定色
     */
	@TableField("unselected_color")
	private String unselectedColor;
    /**
     * 背景色
     */
	@TableField("bkg_color")
	private String bkgColor;
	@TableField("start_img")
	private String startImg;
	@TableField("end_img")
	private String endImg;
	@TableField("banner_img")
	private String bannerImg;
	@TableField("already_submit_img")
	private String alreadySubmitImg;
	@TableField("select_icon")
	private String selectIcon;
	@TableField("unselect_icon")
	private String unselectIcon;
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


	public Long getId() {
		return id;
	}

	public UpAppsQuestionnaireTheme setId(Long id) {
		this.id = id;
		return this;
	}

	public String getBkgImg() {
		return bkgImg;
	}

	public UpAppsQuestionnaireTheme setBkgImg(String bkgImg) {
		this.bkgImg = bkgImg;
		return this;
	}

	public String getIconImg() {
		return iconImg;
	}

	public UpAppsQuestionnaireTheme setIconImg(String iconImg) {
		this.iconImg = iconImg;
		return this;
	}

	public String getThemeColor() {
		return themeColor;
	}

	public UpAppsQuestionnaireTheme setThemeColor(String themeColor) {
		this.themeColor = themeColor;
		return this;
	}

	public String getSelectedColor() {
		return selectedColor;
	}

	public UpAppsQuestionnaireTheme setSelectedColor(String selectedColor) {
		this.selectedColor = selectedColor;
		return this;
	}

	public String getUnselectedColor() {
		return unselectedColor;
	}

	public UpAppsQuestionnaireTheme setUnselectedColor(String unselectedColor) {
		this.unselectedColor = unselectedColor;
		return this;
	}

	public String getBkgColor() {
		return bkgColor;
	}

	public UpAppsQuestionnaireTheme setBkgColor(String bkgColor) {
		this.bkgColor = bkgColor;
		return this;
	}

	public String getStartImg() {
		return startImg;
	}

	public UpAppsQuestionnaireTheme setStartImg(String startImg) {
		this.startImg = startImg;
		return this;
	}

	public String getEndImg() {
		return endImg;
	}

	public UpAppsQuestionnaireTheme setEndImg(String endImg) {
		this.endImg = endImg;
		return this;
	}

	public String getBannerImg() {
		return bannerImg;
	}

	public UpAppsQuestionnaireTheme setBannerImg(String bannerImg) {
		this.bannerImg = bannerImg;
		return this;
	}

	public String getAlreadySubmitImg() {
		return alreadySubmitImg;
	}

	public UpAppsQuestionnaireTheme setAlreadySubmitImg(String alreadySubmitImg) {
		this.alreadySubmitImg = alreadySubmitImg;
		return this;
	}

	public String getSelectIcon() {
		return selectIcon;
	}

	public UpAppsQuestionnaireTheme setSelectIcon(String selectIcon) {
		this.selectIcon = selectIcon;
		return this;
	}

	public String getUnselectIcon() {
		return unselectIcon;
	}

	public UpAppsQuestionnaireTheme setUnselectIcon(String unselectIcon) {
		this.unselectIcon = unselectIcon;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpAppsQuestionnaireTheme setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpAppsQuestionnaireTheme setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpAppsQuestionnaireTheme setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpAppsQuestionnaireTheme setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public static final String ID = "id";

	public static final String BKG_IMG = "bkg_img";

	public static final String ICON_IMG = "icon_img";

	public static final String THEME_COLOR = "theme_color";

	public static final String SELECTED_COLOR = "selected_color";

	public static final String UNSELECTED_COLOR = "unselected_color";

	public static final String BKG_COLOR = "bkg_color";

	public static final String START_IMG = "start_img";

	public static final String END_IMG = "end_img";

	public static final String BANNER_IMG = "banner_img";

	public static final String ALREADY_SUBMIT_IMG = "already_submit_img";

	public static final String SELECT_ICON = "select_icon";

	public static final String UNSELECT_ICON = "unselect_icon";

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
		return "UpAppsQuestionnaireTheme{" +
			"id=" + id +
			", bkgImg=" + bkgImg +
			", iconImg=" + iconImg +
			", themeColor=" + themeColor +
			", selectedColor=" + selectedColor +
			", unselectedColor=" + unselectedColor +
			", bkgColor=" + bkgColor +
			", startImg=" + startImg +
			", endImg=" + endImg +
			", bannerImg=" + bannerImg +
			", alreadySubmitImg=" + alreadySubmitImg +
			", selectIcon=" + selectIcon +
			", unselectIcon=" + unselectIcon +
			", version=" + version +
			", logicDelete=" + logicDelete +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			"}";
	}
}
