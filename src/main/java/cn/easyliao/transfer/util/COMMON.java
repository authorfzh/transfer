package cn.easyliao.transfer.util;


/**
 * Created by Administrator on 2017/7/12.
 */
public class COMMON {

    //失败返回code
    public static Integer CODE_err;
    //成功返回code
    public static Integer CODE_ok;
    //返回类型 1 JSONobject
    public static Integer DATA_JSONObject;
    //返回类型 2 JSONArray
    public static Integer DATA_JSONArray;
    //返回类型 3 String
    public static Integer DATA_String;

    static {
        CODE_err = Integer.parseInt(PropertiesUtils.getString("CODE_err"));
        CODE_ok = Integer.parseInt(PropertiesUtils.getString("CODE_ok"));
        DATA_JSONObject = Integer.parseInt(PropertiesUtils.getString("DATA_JSONObject"));
        DATA_JSONArray = Integer.parseInt(PropertiesUtils.getString("DATA_JSONArray"));
        DATA_String = Integer.parseInt(PropertiesUtils.getString("DATA_String"));
    }

}
