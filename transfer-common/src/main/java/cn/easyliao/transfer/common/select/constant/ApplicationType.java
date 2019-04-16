package cn.easyliao.transfer.common.select.constant;

/**
 * 类型
 * @author chy
 *
 */
public enum ApplicationType {
	WEBAPPLICATION(1, "Web应用程序"),
    CLIENT(2, "客户端程序"),
	MOBLE(3, "移动端程序");

    int code;
    String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private ApplicationType(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String valueOf(Integer type) {
        if (type == null) {
            return "";
        } else {
            for (ApplicationType s : ApplicationType.values()) {
                if (s.getCode() == type) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
