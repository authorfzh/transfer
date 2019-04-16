package cn.easyliao.transfer.common.select.constant;

/**
 * 选择认证方式
 * @author chy
 *
 */
public enum AuthenticationMethed {
	USERAUTHENTICATION(1, "用户认证"),
	ADMINAUTHENTICATION(2, "管理员认证");

    int code;
    String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private AuthenticationMethed(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String valueOf(Integer isYesOrNo) {
        if (isYesOrNo == null) {
            return "";
        } else {
            for (AuthenticationMethed s : AuthenticationMethed.values()) {
                if (s.getCode() == isYesOrNo) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
