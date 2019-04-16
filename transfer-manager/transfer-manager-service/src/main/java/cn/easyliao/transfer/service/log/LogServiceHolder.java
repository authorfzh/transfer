package cn.easyliao.transfer.service.log;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import cn.easyliao.transfer.core.util.SpringContextHolder;

/**
 * 被修改的bean临时存放的地方
 *
 * @author 
 */
@Component
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION)
public class LogServiceHolder {

    private Object object = null;

    public void set(Object obj) {
        this.object = obj;
    }

    public Object get() {
        return object;
    }

    public static LogServiceHolder me(){
        LogServiceHolder bean = SpringContextHolder.getBean(LogServiceHolder.class);
        return bean;
    }
}
