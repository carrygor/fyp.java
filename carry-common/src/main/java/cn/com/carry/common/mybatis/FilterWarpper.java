package cn.com.carry.common.mybatis;

import cn.com.carry.common.util.ValueHelper;
import cn.com.carry.common.form.ReportFilterForm;
import cn.com.carry.common.util.Constants;
import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 严汉羽 on 2017/11/2.
 */
public class FilterWarpper<T> extends EntityWrapper<T> implements Serializable{

    private final static Logger logger = LoggerFactory.getLogger(FilterWarpper.class);

    public FilterWarpper() {

    }

    /**
     * 需要获取90天前的对应数据
     * @param form
     * @param clazz
     * @return
     */
    public Wrapper<T> filterBefore(ReportFilterForm form, Class<?> clazz, long deltaDays) {
        if (deltaDays > 0) {
            if (form == null) {
                form = new ReportFilterForm();
                form.setEndDate(new Date(new Date().getTime() - deltaDays * Constants.ONE_DAY).getTime());
            } else {
                if (form.getStartDate() != null && form.getStartDate() > 0) {
                    form.setStartDate(form.getStartDate() - deltaDays * Constants.ONE_DAY);
                }
                if(form.getEndDate() != null && form.getEndDate() > 0) {
                    form.setEndDate(form.getEndDate() - deltaDays * Constants.ONE_DAY);
                } else {
                    form.setEndDate(new Date(new Date().getTime() - deltaDays * Constants.ONE_DAY).getTime());
                }
            }
        }
        return filter(form, clazz);
    }

    public Wrapper<T> filter(ReportFilterForm form, Class<?> clazz) {
        if (form == null) {
            return this;
        }

        if (form.getTenantsCompanyId() != null && form.getTenantsCompanyId() > 0) {
            try {
                clazz.getDeclaredField(Constants.COLUMN_COMPANY_ID);
                this.eq(Constants.COLUMN_COMPANY_ID, form.getTenantsCompanyId());
            } catch (NoSuchFieldException e) {
                logger.debug("不存在" + Constants.COLUMN_COMPANY_ID);
            }
        }

        if (form.getStartDate() != null && form.getStartDate() > 0) {
            try {
                clazz.getDeclaredField(Constants.COLUMN_ADD_TIME);
                this.ge(Constants.COLUMN_ADD_TIME, new Date(form.getStartDate()));
            } catch (NoSuchFieldException e) {
                logger.debug("不存在" + Constants.COLUMN_ADD_TIME);
            }
        }

        if (form.getEndDate() != null && form.getEndDate() > 0) {
            try {
                clazz.getDeclaredField(Constants.COLUMN_ADD_TIME);
                this.lt(Constants.COLUMN_ADD_TIME, new Date(form.getEndDate() + Constants.ONE_DAY));
            } catch (NoSuchFieldException e) {
                logger.debug("不存在" + Constants.COLUMN_ADD_TIME);
            }
        }

        if (!ValueHelper.isNone(form.getPeriod()) && !form.getPeriod().equals(Constants.COLUMN_ALL) &&
                !ValueHelper.isNone(form.getPeriodMin()) && !ValueHelper.isNone(form.getPeriodMax())
                ) {
            try {
                clazz.getDeclaredField(Constants.COLUMN_ADD_TIME);
                //如果min大于max则需要要用逻辑 or
                if (form.getPeriodMin().compareTo(form.getPeriodMax()) > 0) {
                    this.andNew();
                    this.ge("DATE_FORMAT(" + Constants.COLUMN_ADD_TIME + ", '%H:%i:%S')",
                            form.getPeriodMin());
                    this.or();
                    this.le("DATE_FORMAT(" + Constants.COLUMN_ADD_TIME + ", '%H:%i:%S')",
                            form.getPeriodMax());
                    this.andNew();
                } else {
                    this.andNew();
                    this.ge("DATE_FORMAT(" + Constants.COLUMN_ADD_TIME + ", '%H:%i:%S')",
                            form.getPeriodMin());
                    this.and();
                    this.le("DATE_FORMAT(" + Constants.COLUMN_ADD_TIME + ", '%H:%i:%S')",
                            form.getPeriodMax());
                    this.andNew();
                }
            } catch (NoSuchFieldException e) {
                logger.debug("不存在" + Constants.COLUMN_ADD_TIME);
            }
        }

        if (!ValueHelper.isNone(form.getServiceType()) && !form.getServiceType().equals(Constants.COLUMN_ALL)) {
            try {
                clazz.getDeclaredField(Constants.COLUMN_SERVICE_TYPE);
                this.eq(Constants.COLUMN_SERVICE_TYPE, form.getServiceType());
            } catch (Exception e) {
                logger.debug("不存在add_time");
            }
        }

        if (!ValueHelper.isNone(form.getSiteOptions()) && !form.getSiteOptions().equals(Constants.COLUMN_ALL)) {
            try {
                clazz.getDeclaredField(Constants.COLUMN_QUICK_TAG);
                this.like(Constants.COLUMN_QUICK_TAG, form.getSiteOptions(), SqlLike.RIGHT);
            } catch (NoSuchFieldException e) {
                logger.debug("不存在" + Constants.COLUMN_QUICK_TAG);
            }
        }
        return this;
    }

}
