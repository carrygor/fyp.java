package cn.com.carry.common.util.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * Created by 严汉羽 on 2017/10/24.
 */
public class MybatisServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IMybatisService<T> {


    /**
     * 注意，这个是跨RPC调用时用来返回实例的。
     *
     * @param e
     * @return
     */
    @Override
    public T mInsert(T e) {
        if (super.insert(e)) {
            return e;
        } else {
            return null;
        }
    }

    /**
     * 注意，这个是跨RPC调用时用来返回实例的。
     *
     * @param e
     * @return
     */
    @Override
    public T mInsertAllColumn(T e) {
        if (super.insertAllColumn(e)) {
            return e;
        } else {
            return null;
        }
    }
}
