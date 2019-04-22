package cn.easyliao.transfer.bean;

import java.io.Serializable;

/**
 * EC返回对象
 * @param <T>
 */
public class ECBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    // 状态码
    private int errCode;
    // 消息
    private String errMsg;
    // 返回数据
    private T data;

    public ECBean() {
    }

    public ECBean(int errCode, String errMsg, T data) {
        super();
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
