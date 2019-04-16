package cn.easyliao.transfer.common.select.constant;

/**
 * 状态
 * @author chy
 *
 */
public enum UserBasicPolitics {
	PARTY_MEMBER(1, "党员"),
	MEMBERS(2, "团员"),
	MASSES(3, "群众");
	
    int code;
    String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private UserBasicPolitics(int code, String message) {
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
            for (UserBasicPolitics s : UserBasicPolitics.values()) {
                if (s.getCode() == state) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
