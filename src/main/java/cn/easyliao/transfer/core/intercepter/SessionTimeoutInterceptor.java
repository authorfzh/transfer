package cn.easyliao.transfer.core.intercepter;

import org.apache.shiro.session.InvalidSessionException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证session超时的拦截器
 *
 * @author 
 */
@Aspect
@Component
public class SessionTimeoutInterceptor {

    @Pointcut("execution(* cn.easyliao.transfer.controller.*.*(..))")
    public void cutService() {
    }

    @Around("cutService()")
    public Object sessionTimeoutValidate(ProceedingJoinPoint point) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String servletPath = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getServletPath();
        Object tfUser = request.getSession().getAttribute("tfUser");
        if(servletPath.indexOf("login.html") >= 0 || servletPath.indexOf("loginVali") >= 0){
            return point.proceed();
        }
        if(tfUser != null){
            return point.proceed();
        }else{
            return "redirect:/login.html";
        }
    }
}
