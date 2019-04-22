package cn.easyliao.transfer.service;

import cn.easyliao.transfer.bean.ActiveAcquirement;
import cn.easyliao.transfer.bean.ECBean;
import cn.easyliao.transfer.util.CardUtil;
import cn.easyliao.transfer.util.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * 名片分配中心
 */
@Service
public class AllocationCardService {

    // 企业id
    private static String CORP_ID = "10097014";
    // Appid
    private static String APP_ID = "459758381591166976";
    // App密钥
    private static String APP_SECRET = "e9ynm6N1ZSBGnhVafTB";
    // 获取token
    private static String GET_TOKEN_URL = "https://open.workec.com/auth/accesstoken";
    // 获取部门及员工信息
    //private static String GET_USERALL_URL = "https://open.workec.com/user/structure";
    // 获取自定义字段信息
    //private static String GET_CUSTOM_PARAMETER = "https://open.workec.com/customer/getCustomFieldMapping";
    // 创建客户
    private static String ADD_CUSTOMER = "https://open.workec.com/customer/addCustomer";
    // 批量创建客户
    private static String ADD_SUSTOMER_ALL = "https://open.workec.com/customer/create";
    // 字符集
    private static final String DEFAULT_CHARSET = "UTF-8";

    private final static Log log = LogFactory.getLog(AllocationCardService.class);

    private static ActiveAcquirement aa = new ActiveAcquirement();

    /**
     * 获取accessToken
     *
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public String getAccessToken() throws IOException, ExecutionException, InterruptedException {

        JSONObject s = new JSONObject();
        s.put("appId", APP_ID);
        s.put("appSecret", APP_SECRET);

        String info = HttpClientUtil.httpPost(GET_TOKEN_URL, JSON.toJSONString(s), null);
        ECBean<JSONObject> ec = JSON.parseObject(info, ECBean.class);

        log.info(JSON.toJSONString(ec.getData()));
        return ec.getData().getString("accessToken");
    }

    /**
     * 提交EC
     *
     * @param aa
     * @param data
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public String init(ActiveAcquirement aa, String data) throws InterruptedException, ExecutionException, IOException {

        this.aa = aa;
        data = getUserId(data);
        String token = getAccessToken();

        //String url = CardUtil.getValue(ac.getActiveAcquirement().getConfig(), "url");
        String url = ADD_CUSTOMER + "?Authorization=" + token + "&CORP_ID=" + CORP_ID;

        log.info("----------------------------->提交EC：" + url + "," + data + "," + token + "," + CORP_ID);
        String res = HttpClientUtil.httpPost(url, data, token, CORP_ID, null);
        log.info("----------------------------->提交EC返回结果：" + res);

        //TODO : 提交EC得到返回值需写入日志库
        return res;
    }

    /**
     * 获取分配客服工号及处理来源
     *
     * @param data
     * @return
     */
    public String getUserId(String data) {

        //TODO : 获得到分配客服工号，如果未得到则使用配置备用工号
        int userCode = 0;
        if (userCode == 0)
            userCode = Integer.parseInt(CardUtil.getValue(aa.getConfig(), "userCode"));

        JSONObject template = JSON.parseObject(data);
        if (template.containsKey("optUserId"))
            template.put("optUserId", userCode);
        if (template.containsKey("followUserId"))
            template.put("followUserId", userCode);

        return JSON.toJSONString(template);
    }

}
