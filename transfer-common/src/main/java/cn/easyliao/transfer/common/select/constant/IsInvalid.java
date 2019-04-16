package cn.easyliao.transfer.common.select.constant;

/**
 * 生效/失效
 * @author chy
 *
 */
public enum IsInvalid {
	INVALID(1, "生效"),
	NOTINVALID(2, "失效");

    int code;
    String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private IsInvalid(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String valueOf(Integer isvalid) {
        if (isvalid == null) {
            return "";
        } else {
            for (IsInvalid s : IsInvalid.values()) {
                if (s.getCode() == isvalid) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
