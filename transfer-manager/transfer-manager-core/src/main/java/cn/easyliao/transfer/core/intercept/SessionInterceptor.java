package cn.easyliao.transfer.core.intercept;

import cn.easyliao.transfer.common.controller.BaseController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import cn.easyliao.transfer.core.util.HttpSessionHolder;
import org.springframework.stereotype.Component;

/**
 * 静态调用session的拦截器
 *
 * @author 
 */
@Aspect
@Component
public class SessionInterceptor extends BaseController {

    @Pointcut("execution(* cn.easyliao.transfer.*..controller.*.*(..))")
    public void cutService() {
    }

    @Around("cutService()")
    public Object sessionKit(ProceedingJoinPoint point) throws Throwable {

        HttpSessionHolder.put(super.getHttpServletRequest().getSession());
        try {
            return point.proceed();
        } finally {
            HttpSessionHolder.remove();
        }
    }
}
