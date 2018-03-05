package cn.com.carry.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
public class ComparatorUtil {
    private final static Logger logger = LoggerFactory.getLogger(ComparatorUtil.class);

    public static class IntComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 > o2) {
                return 1;
            }
            if (o1 < o2) {
                return -1;
            }
            return 0;
        }
    }

    public static boolean isTime1AfterTime2(Date d1, Date d2) {
        if (ValueHelper.isNone(d1) || ValueHelper.isNone(d2)) {
            return false;
        }
        return d1.after(d2);
    }

    public static boolean isTime1BeforeTime2(Date d1, Date d2) {
        if (ValueHelper.isNone(d1) || ValueHelper.isNone(d2)) {
            return false;
        }
        return d1.before(d2);
    }

    public static void main(String [] args) {
        List<Integer> ids = new ArrayList<>();
        ids.add(7);
        ids.add(8);
        ids.add(3);
        ids.add(35);
        ids.add(135);
        ids.sort(new IntComparator());

        for (Integer i: ids) {
            logger.debug(i + "");
        }
    }
}
