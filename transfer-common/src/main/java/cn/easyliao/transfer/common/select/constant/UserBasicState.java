package cn.easyliao.transfer.common.select.constant;

/**
 * 状态
 * @author chy
 *
 */
public enum UserBasicState {
	INJOB(1, "在职"),
	LEAVEJOB(2, "离职"),
	INTERNSHIP(3, "实习");
    int code;
    String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private UserBasicState(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String valueOf(Integer state) {
        if (state == null) {
            return "";
        } else {
            for (UserBasicState s : UserBasicState.values()) {
                if (s.getCode() == state) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
