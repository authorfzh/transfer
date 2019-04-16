package cn.easyliao.transfer.common.select.constant;

/**
 * 是否显示
 * @author chy
 *
 */
public enum ApplicationIsshow {
	HIDDEN(1, "显示"),
	SHOW(2, "隐藏");

    int code;
    String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private ApplicationIsshow(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String valueOf(Integer isshow) {
        if (isshow == null) {
            return "";
        } else {
            for (ApplicationIsshow s : ApplicationIsshow.values()) {
                if (s.getCode() == isshow) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
