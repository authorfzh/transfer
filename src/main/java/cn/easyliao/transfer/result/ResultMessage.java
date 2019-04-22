package cn.easyliao.transfer.result;

import com.alibaba.fastjson.JSONObject;

public class ResultMessage {

    public static final int SUCCESS = 200;
    public static final int SYSTEM_ERROR = 500;
    public static final int AUTH_ERROR = 201;

    private int code = SUCCESS;
    private Object message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    /**
     * 设置返回结果.
     */
    public void setResult(int resultCode, Object resultMessage) {
        code = resultCode;
        message = resultMessage;
    }

    /**
     * 返回对象的json字符串类型
     */
    public String toJson() {
        return JSONObject.toJSONString(this);
    }

}
