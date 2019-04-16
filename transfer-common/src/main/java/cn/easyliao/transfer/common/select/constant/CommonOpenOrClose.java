package cn.easyliao.transfer.common.select.constant;

/**
 * 选择是/否
 * @author chy
 *
 */
public enum CommonOpenOrClose {
	OPEN(1, "开启"),
	CLOSE(2, "关闭");

    int code;
    String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private CommonOpenOrClose(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String valueOf(Integer isOpenOrClose) {
        if (isOpenOrClose == null) {
            return "";
        } else {
            for (CommonOpenOrClose s : CommonOpenOrClose.values()) {
                if (s.getCode() == isOpenOrClose) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
