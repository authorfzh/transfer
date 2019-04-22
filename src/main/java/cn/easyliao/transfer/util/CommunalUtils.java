package cn.easyliao.transfer.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public final class CommunalUtils {

    private static CommunalUtils communalUtils;

    private static Logger log = LoggerFactory.getLogger(CommunalUtils.class);

    @PostConstruct
    public void init() {
        communalUtils = this;
    }

    /*
     * 返回提示信息
     * code
     * 200 成功
     * 203 部份数据失败
     * 400 失败、参数错误
     * 404 数据已存在或名称已存在
     */

    /**
     * 统一输出格式
     *
     * @param request  请求头信息
     * @param code     状态码
     * @param msg      提示消息
     * @param data     返回数据
     * @param dataType 返回数据类型
     * @param success  是否成功
     * @return
     */
    public static String hint(HttpServletRequest request, int code, String msg, String data, int dataType, boolean success) {

        JSONObject info = new JSONObject();

        info.put("code", code);
        info.put("msg", msg);

        if (dataType == 1) {
            info.put("data", JSON.parseObject(data));
        } else if (dataType == 2) {
            info.put("data", JSON.parseArray(data));
        } else if (dataType == 3) {
            info.put("data", data);
        }

        info.put("success", success);

        return JSON.toJSONString(info);
    }

    /**
     * 仅导入导出使用
     *
     * @param code
     * @param msg
     * @param data
     * @param dataType
     * @param success
     * @return
     */
    public static String uploadHint(int code, String msg, String data, int dataType, boolean success) {

        JSONObject info = new JSONObject();

        info.put("code", code);
        info.put("msg", msg);

        if (dataType == 1) {
            info.put("data", JSON.parseObject(data));
        } else if (dataType == 2) {
            info.put("data", JSON.parseArray(data));
        }

        info.put("success", success);

        return JSON.toJSONString(info);
    }

    /**
     * 过滤
     *
     * @param str
     * @return
     */
    public static int getRegexCount(String str) {
        int chilaCount = 0;
        int engCount = 0;
        int count = 0;

        String chilaRegex = "[\u4e00-\u9fa5]";
        Pattern p = Pattern.compile(chilaRegex);
        Matcher m = p.matcher(str);

        while (m.find()) {
            chilaCount++;
        }

        String engRegex = "[a-zA-Z0-9]";
        Pattern pp = Pattern.compile(engRegex);
        Matcher mm = pp.matcher(str);
        while (mm.find()) {
            engCount++;
        }

        String regex = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern ppp = Pattern.compile(regex);
        Matcher mmm = ppp.matcher(str);
        while (mmm.find()) {
            count++;
        }

        return chilaCount + engCount + count;
        /*System.out.println();
        System.out.println("汉字出现的频率：" + chilaCount);
        System.out.println("非汉字出现的频率：" + engCount);
        System.out.println("特殊出现的频率：" + count);*/
    }

    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
     *
     * @param str 需要得到长度的字符串
     * @return int 得到的字符串长度
     */
    public static int getCharCount(String str) {
        if (str == null)
            return 0;
        char[] c = str.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (!isLetter(c[i])) {
                len++;
            }
        }
        return len;
    }

    /**
     * 判断是汉字还是字符
     *
     * @param c
     * @return
     */
    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }

    /**
     * 时间转换为时间戳
     *
     * @param s
     * @return
     */
    public static long dateToStamp(String s) throws ParseException {

        long ts;

        if (s.indexOf(":") != -1) {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            Date date = simpleDateFormat.parse(s);

            ts = date.getTime();

        } else {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

            Date date = simpleDateFormat.parse(s);

            ts = date.getTime();
        }

        return ts;
    }

    /**
     * 时间戳转换为时间
     *
     * @param s
     * @return
     */
    public static String stampToDate(long s) {

        String res;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Date date = new Date(s);

        res = simpleDateFormat.format(date);

        return res;
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 参考文章： http://developer.51cto.com/art/201111/305181.htm
     * <p>
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * <p>
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     * <p>
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 验证手机号是否合法
     *
     * @param mobiles
     * @return
     */
    public static int isMobileNO(final String mobiles) {

        boolean info = mobiles.matches("^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$");
        //boolean info = mobiles.matches("^(1(3|4|5|6|7|8|9)[0-9])\\d{8}$");

        System.out.println(mobiles + ":" + info);

        if (info)
            return 1;

        return 0;
    }

    /**
     * 区号+座机号码+分机号码
     *
     * @param fixedPhone
     * @return
     */
    public static int isFixedPhone(final String fixedPhone) {

        boolean info = false;

        boolean status = fixedPhone.contains("-");

        if (status) {
            // 验证带区号的
            info = fixedPhone.matches("^[0][1-9]{2,3}-[0-9]{5,10}$");

        } else {
            // 验证没有区号的
            info = fixedPhone.matches("^[1-9]{1}[0-9]{5,8}$");
        }

        if (info)
            return 1;

        return 0;
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static int checkEmail(final String email) {

        boolean info = email.matches("^([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$");

        System.out.println(email + ":" + info);

        if (info)
            return 1;

        return 0;
    }

    /**
     * 对象数据进行排序
     *
     * @param arr   所有数据
     * @param field 按此字段排序
     * @param sort  asc 按升序排列 (不用传，默认使用这个) desc 按降序排列
     * @return
     */
    public static HashMap<Integer, JSONObject> sort(HashMap<Integer, JSONObject> arr, String field, String sort) {

        if (arr == null || arr.size() <= 1) {
            return arr;
        }

        sort = StringUtils.isEmpty(sort) ? "asc" : sort;

        int length = arr.size();

        for (int i = 0; i < length; i++) {

            for (int j = 0; j < length - i - 1; j++) {

                JSONObject o1 = arr.get(j);
                JSONObject o2 = arr.get(j + 1);

                if (sort.equals("desc")) {
                    if (o1.getInteger(field) < o2.getInteger(field)) {
                        JSONObject temp = o1;
                        arr.put(j, o2);
                        arr.put(j + 1, temp);
                    }
                } else {
                    if (o1.getInteger(field) > o2.getInteger(field)) {
                        JSONObject temp = o1;
                        arr.put(j, o2);
                        arr.put(j + 1, temp);
                    }
                }
            }
        }
        return arr;
    }

    /**
     * 分页取值
     *
     * @param arr
     * @param page
     * @param rp
     * @return
     */
    public static HashMap<Integer, JSONObject> getPageRp(HashMap<Integer, JSONObject> arr, int page, int rp) {

        if (arr == null || arr.size() <= 1) {
            return arr;
        }

        int length = arr.size();

        HashMap<Integer, JSONObject> mapAll = new HashMap<>();

        int rank = 0;

        int count = page * rp - rp;

        for (int i = 0; i < length; i++) {

            if (i >= count && i < (count + rp)) {

                mapAll.put(rank, arr.get(i));

                rank++;
            }
        }
        return mapAll;
    }

    /**
     * 记录执行时间
     */
    public static void RecordExecutionTime(String record) {

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String dateStr = dateformat.format(System.currentTimeMillis());

        log.info("[Record the execution time]" + record + ":" + dateStr);
    }

    /**
     * 统计相同值的个数
     * 如果存在相同值则返回true,反之返回false
     *
     * @param rankMap
     * @return
     */
    public static boolean countRank(HashMap<Integer, Integer> rankMap) {

        if (rankMap.size() <= 0)
            return false;

        Map<Integer, Integer> res = new HashMap<>();

        for (Map.Entry<Integer, Integer> entry : rankMap.entrySet()) {
            if (res.containsKey(entry.getValue())) {
                res.put(entry.getValue(), res.get(entry.getValue()) + 1);
            } else {
                res.put(entry.getValue(), 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : res.entrySet()) {
            if (entry.getValue() > 1)
                return true;
        }

        return false;
    }

}
