package cn.easyliao.transfer.service.log.factory;

import java.util.TimerTask;
import java.util.logging.LogManager;

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
 * 日志操作任务创建工厂
 *
 * @author 
 */
public class LogServiceTaskFactory {

    private static Logger logger = Logger.getLogger(LogManager.class);
    private static LoginLogMapper loginLogMapper = Db.getMapper(LoginLogMapper.class);
    private static OperationLogMapper operationLogMapper = Db.getMapper(OperationLogMapper.class);

    public static TimerTask loginLog(final Integer userId, final String ip) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    LoginLog loginLog = LogServiceFactory.createLoginLog(LogType.LOGIN, userId, null, ip);
                    loginLogMapper.insert(loginLog);
                } catch (Exception e) {
                    logger.error("创建登录日志异常!", e);
                }
            }
        };
    }

    public static TimerTask loginLog(final String username, final String msg, final String ip) {
        return new TimerTask() {
            @Override
            public void run() {
                LoginLog loginLog = LogServiceFactory.createLoginLog(
                        LogType.LOGIN_FAIL, null, "账号:" + username + "," + msg, ip);
                try {
                    loginLogMapper.insert(loginLog);
                } catch (Exception e) {
                    logger.error("创建登录失败异常!", e);
                }
            }
        };
    }

    public static TimerTask exitLog(final Integer userId, final String ip) {
        return new TimerTask() {
            @Override
            public void run() {
                LoginLog loginLog = LogServiceFactory.createLoginLog(LogType.EXIT, userId, null,ip);
                try {
                    loginLogMapper.insert(loginLog);
                } catch (Exception e) {
                    logger.error("创建退出日志异常!", e);
                }
            }
        };
    }

    public static TimerTask bussinessLog(final Integer userId, final String bussinessName, final String clazzName, final String methodName, final String msg) {
        return new TimerTask() {
            @Override
            public void run() {
                OperationLog operationLog = LogServiceFactory.createOperationLog(
                        LogType.BUSSINESS, userId, bussinessName, clazzName, methodName, msg, LogSucceed.SUCCESS);
                try {
                    operationLogMapper.insert(operationLog);
                } catch (Exception e) {
                    logger.error("创建业务日志异常!", e);
                }
            }
        };
    }

    public static TimerTask exceptionLog(final Integer userId, final Exception exception) {
        return new TimerTask() {
            @Override
            public void run() {
                String msg = ToolUtil.getExceptionMsg(exception);
                OperationLog operationLog = LogServiceFactory.createOperationLog(
                        LogType.EXCEPTION, userId, "", null, null, msg, LogSucceed.FAIL);
                try {
                    operationLogMapper.insert(operationLog);
                } catch (Exception e) {
                    logger.error("创建异常日志异常!", e);
                }
            }
        };
    }
}
