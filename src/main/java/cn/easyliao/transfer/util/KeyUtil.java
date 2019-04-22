package cn.easyliao.transfer.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class KeyUtil {
	
	/*public static void main(String[] args) {
		String key = getKey("257790");
		System.out.println(key);
	}*/

	/*public static String getKey(String oid) {
		// oid + "_crm_zdytable_257790_20396_" + Format(Now, "yyyy-MM-dd")
		// 字符串MD5加密后的值（32位）！
		String key = oid + "_crm_zdytable_257790_20396_"
				+ DateUtil.getCurDate();
		return encryption(key);
	}*/

	/**
	 * 
	 * @param plainText
	 *            明文
	 * @return 32位密文
	 */
	public static String encryption(String plainText) {
		String re_md5 = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			re_md5 = buf.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return re_md5;
	}
}
