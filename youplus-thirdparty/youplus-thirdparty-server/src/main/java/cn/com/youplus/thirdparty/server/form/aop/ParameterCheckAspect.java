package cn.com.youplus.thirdparty.server.form.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by 严汉羽 on 2017/7/6.
 */

@Component
@Aspect
public class ParameterCheckAspect {

    @Autowired
    private ThirdpartyParameterCheckOption thirdpartyParameterCheckOption;

    // 定义切点
    @Pointcut("within(cn.com.youplus.thirdparty.server.controller..*)")
    public void check() {
    }

    /**
     * 切面方法，使用统一异常处理
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "check()", argNames = "Valid")
    public Object checkIsValid(ProceedingJoinPoint joinPoint) throws Throwable {
        Object object = null;
        // 参数校验，未抛出异常表示验证OK
        thirdpartyParameterCheckOption.checkValid(joinPoint);
        object = ((ProceedingJoinPoint) joinPoint).proceed();
        return object;
    }
}
