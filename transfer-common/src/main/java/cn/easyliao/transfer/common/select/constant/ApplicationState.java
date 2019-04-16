package cn.easyliao.transfer.common.select.constant;

/**
 * 状态
 * @author chy
 *
 */
public enum ApplicationState {
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

    private ApplicationState(int code, String message) {
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
            for (ApplicationState s : ApplicationState.values()) {
                if (s.getCode() == state) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
