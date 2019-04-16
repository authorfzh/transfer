package cn.easyliao.transfer.service.log.factory;

import java.util.Date;
import java.util.TimerTask;
import java.util.logging.LogManager;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import cn.easyliao.transfer.common.constant.state.LogSucceed;
import cn.easyliao.transfer.common.constant.state.LogType;
import cn.easyliao.transfer.common.persistence.dao.LoginLogMapper;
import cn.easyliao.transfer.common.persistence.dao.OperationLogMapper;
import cn.easyliao.transfer.common.persistence.model.LoginLog;
import cn.easyliao.transfer.common.persistence.model.OperationLog;
import cn.easyliao.transfer.core.db.Db;
import cn.easyliao.transfer.core.util.ToolUtil;

/**
 * 日志对象创建工厂
 *
 * @author 
 */
public class LogServiceFactory {

    /**
     * 创建操作日志
     *
     * @author fengshuonan
     * @Date 2017/3/30 18:45
     */
    public static OperationLog createOperationLog(LogType logType, Integer userId, String bussinessName, String clazzName, String methodName, String msg, LogSucceed succeed) {
        OperationLog operationLog = new OperationLog();
        operationLog.setLogtype(logType.getMessage());
        operationLog.setLogname(bussinessName);
        operationLog.setUserid(userId);
        operationLog.setClassname(clazzName);
        operationLog.setMethod(methodName);
        operationLog.setCreatetime(new Date());
        operationLog.setSucceed(succeed.getMessage());
        operationLog.setMessage(msg);
        return operationLog;
    }

    /**
     * 创建登录日志
     *
     * @author fengshuonan
     * @Date 2017/3/30 18:46
     */
    public static LoginLog createLoginLog(LogType logType, Integer userId, String msg,String ip) {
        LoginLog loginLog = new LoginLog();
        loginLog.setLogname(logType.getMessage());
        loginLog.setUserid(userId);
        loginLog.setCreatetime(new Date());
        loginLog.setSucceed(LogSucceed.SUCCESS.getMessage());
        loginLog.setIp(ip);
        loginLog.setMessage(msg);
        return loginLog;
    }
}

