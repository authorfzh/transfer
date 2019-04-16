package cn.easyliao.transfer.common.select.constant;

/**
 * 二次认证
 * @author chy
 *
 */
public enum ApplicationTwoauthentication {

	NOCONFIRMAT (1, "无需确认"),
	MOBILECERTIFICATE(2, "移动证书"),
	QRCODE(3, "二维码"),
	MOBLETLKEN(4, "手机令牌"),
	SMSVERIFICATION(5, "短信验证");
	
    int code;
    String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private ApplicationTwoauthentication(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String valueOf(Integer twoauthentication) {
        if (twoauthentication == null) {
            return "";
        } else {
            for (ApplicationTwoauthentication s : ApplicationTwoauthentication.values()) {
                if (s.getCode() == twoauthentication) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
