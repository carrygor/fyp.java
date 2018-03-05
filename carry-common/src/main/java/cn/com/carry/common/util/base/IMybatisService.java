package cn.com.carry.common.util.base;


import com.baomidou.mybatisplus.service.IService;

/**
 * Created by 严汉羽 on 2017/10/24.
 */
public interface IMybatisService<T> extends IService<T>{

    /**
     * 注意，这个是跨RPC调用时用来返回实例的。
     * @param e
     * @return
     */
    public T mInsert(T e);
    
    /**
     * 注意，这个是跨RPC调用时用来返回实例的。
     * @param e
     * @return
     */
    public T mInsertAllColumn(T e);
}
