package cn.easyliao.transfer.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class CardUtil {

    private final static Log _logger = LogFactory.getLog(CardUtil.class);

    public static String getValue(String configStr, String key) {
        String valueStr = null;
        Properties prop = new Properties();
        InputStream stringStream = StringToInputStream.getStringStream(configStr);
        try {
            prop.load(stringStream);
            valueStr = prop.getProperty(key);
        } catch (IOException e) {
            _logger.error(e.getMessage(), e);
        }
        return valueStr;
    }


}
