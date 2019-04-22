package cn.easyliao.transfer.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

    /**
     * 读取配置文件
     *
     * @return
     */
    static public Properties properties() {
        InputStream path = null;
        try {
            path = PropertiesUtils.class.getClassLoader().getResource("config.properties").openStream();
        } catch (IOException e) {

            e.printStackTrace();
        }
        Properties properties = new Properties();
        try {
            properties.load(path);


        } catch (IOException e) {

            e.printStackTrace();
        }
        return properties;
    }

    /**
     * 读取配置文件中参数
     *
     * @param key
     * @return
     */
    public static String getString(String key) {
        Properties properties = properties();
        String result = properties.getProperty(key, null);
        return result;
    }

    public static void setProperties(String key, String value) {
        Properties properties = properties();
        properties.setProperty(key, value);
    }

	/*public static void main(String[] args) {
        System.out.println(getString("chatSet.rptcol"));
	}*/

}