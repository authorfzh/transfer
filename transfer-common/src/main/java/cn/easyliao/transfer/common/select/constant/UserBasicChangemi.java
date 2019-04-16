package cn.easyliao.transfer.common.select.constant;

/**
 * 下次登录强制修改密码
 * @author chy
 *
 */
public enum UserBasicChangemi {
	PASSWORD_OK(1, "是"),
	PASSWORD_NO(2, "否");
	
    int code;
    String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private UserBasicChangemi(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String valueOf(Integer changemi) {
        if (changemi == null) {
            return "";
        } else {
            for (UserBasicChangemi s : UserBasicChangemi.values()) {
                if (s.getCode() == changemi) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
