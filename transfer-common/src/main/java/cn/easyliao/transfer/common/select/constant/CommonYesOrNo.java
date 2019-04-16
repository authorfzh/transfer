package cn.easyliao.transfer.common.select.constant;

/**
 * 选择是/否
 * @author chy
 *
 */
public enum CommonYesOrNo {
	Yes(1, "是"),
	NO(2, "否");

    int code;
    String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private CommonYesOrNo(int code, String message) {
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
            for (CommonYesOrNo s : CommonYesOrNo.values()) {
                if (s.getCode() == isYesOrNo) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
