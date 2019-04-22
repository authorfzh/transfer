package cn.easyliao.transfer.service;

import cn.easyliao.transfer.Factory.ChannelInterface;
import cn.easyliao.transfer.bean.ActionConfig;
import cn.easyliao.transfer.bean.ActiveAcquirement;
import cn.easyliao.transfer.bean.LZSEBean;
import cn.easyliao.transfer.util.CardUtil;
import cn.easyliao.transfer.util.HttpClientUtil;
import cn.easyliao.transfer.util.KeyUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 联展-78、联展-89178
 */
@Service
public class LianZhanSEService implements ChannelInterface {

    @Autowired
    private static AllocationCardService acService = new AllocationCardService();

    @Autowired
    private static ReplaceParameterService rpService = new ReplaceParameterService();

    private final static Log log = LogFactory.getLog(LianZhanSEService.class);

    @Override
    public String getData(ActionConfig ac) {
        System.out.println("获取联展-78、联展-89178");

        ActiveAcquirement aa = ac.getActiveAcquirement();
        int companyId = ac.getCompanyId();

        //当前时间戳
        long ctime = System.currentTimeMillis();

        //当前日期
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateformat.format(ctime);

        //openkey
        String openkey = CardUtil.getValue(aa.getConfig(), "openKey");

        //openkey
        String privateKey = CardUtil.getValue(aa.getConfig(), "privateKey");

        //请求集合数据token MD5(私有key+ctime+date+ac)
        String token = KeyUtil.encryption(privateKey + ctime + currentDate + "tj");
        log.info("token:MD5(" + privateKey + ctime + currentDate + "tj)");

        String url = CardUtil.getValue(aa.getConfig(), "url") + "?openkey=" + openkey;
        //请求集合数据URL
        String listUrl = url + "&token=" + token + "&ctime=" + ctime + "&ac=tj&date=" + currentDate;
        //https://guest.qudao.com/api/data?openkey=null&token=3146a3f1a5dc7fc3fd6e1e46d42c4e1e&ctime=?1555656750295&ac=tj&date=2019-04-19
        log.info("listUrl:" + listUrl);

        String listData = HttpClientUtil.httpPost(listUrl, null, "application/json;charset=utf-8");
        log.info("listData:" + listData);

        List<LZSEBean> list = JSON.parseArray(listData, LZSEBean.class);

        //获取指定数据
        for (LZSEBean l : list) {

            ctime = System.currentTimeMillis();
            token = KeyUtil.encryption(privateKey + ctime + currentDate + "xg");
            //请求指定数据URL
            String appointUrl = url + "&token=" + token + "&ctime=?" + ctime + "&ac=xg&data_id=" + l.getData_id();
            String appointData = HttpClientUtil.httpPost(appointUrl, null, "application/json;charset=utf-8");

            if (StringUtils.isEmpty(appointData)) {
                log.info("获取联展-78、联展-89178数据：openkey：" + openkey + ",data_id:" + l.getData_id() + "数据为空");
                continue;
            }

            //数据统一转换为易聊名片数据
            String data = replaceParameter(companyId, aa, appointData);

            if (StringUtils.isEmpty(data)) {
                log.info("获取联展-78、联展-89178数据：openkey：" + openkey + ",data_id:" + l.getData_id() + "数据返回错误");
                continue;
            }

            try {
                //名片分配
                acService.init(aa, data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return "获取联展-78、联展-89178";
    }

    /**
     * 参数替换
     */
    public String replaceParameter(int companyId, ActiveAcquirement aa, String data) {

        JSONObject json = JSON.parseObject(data);

        if (!json.containsKey("sta") || !json.containsKey("data"))
            return null;
        if (json.getInteger("sta") != 100)
            return null;

        JSONObject dataJson = json.getJSONObject("data");

        LZSEBean lzseBean = JSON.parseObject(JSON.toJSONString(dataJson), LZSEBean.class);

        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("id",lzseBean.getData_id());
        //公司id
        dataMap.put("companyId", companyId);
        //访客名称
        dataMap.put("name", lzseBean.getUname());
        //邮箱
        dataMap.put("email", lzseBean.getEamil());
        //手机
        dataMap.put("mobile", lzseBean.getPhone());
        //QQ
        dataMap.put("qq", lzseBean.getQq());
        //备注
        dataMap.put("note", lzseBean.getContent());

        if (StringUtils.isNotEmpty(lzseBean.getArea())) {
            JSONObject area = analyzeArea(lzseBean.getArea());
            if (area != null) {
                //国家
                //dataMap.put("country", );
                //省
                dataMap.put("province", area.getString("province"));
                //市
                dataMap.put("city", area.getString("city"));
                //区
                dataMap.put("area", area.getString("area"));
            }
        }

        //关键词
        dataMap.put("keyword", lzseBean.getCustom_name());
        //访客来源
        dataMap.put("refer", lzseBean.getPlat_name());
        //创建时间
        dataMap.put("createTime", lzseBean.getDatetime());

        //访问者静态Id
        //dataMap.put("visitorStaticId", );
        //公司名称
        //dataMap.put("companyName", );
        //电话
        //dataMap.put("tel", );
        //性别
        //dataMap.put("sex", );
        //msn
        //dataMap.put("msn", );
        //dataMap.put("repName", );
        //dataMap.put("url", );
        //分配时间
        //dataMap.put("allocationTime", );
        //客服id
        //dataMap.put("userId", );
        //创建人id
        //搜索引擎
        //dataMap.put("searchEngine", );
        //对话页
        //dataMap.put("chatURL", );
        //搜索主机
        //dataMap.put("searchHost", );
        //dataMap.put("spreadFlag", );
        //首访Url
        //dataMap.put("firstUrl", );
        //dataMap.put("promotionId", );
        //dataMap.put("type", );
        //dataMap.put("crmState", );
        //编辑时间
        //dataMap.put("editTime", );
        //dataMap.put("hideMobile", );
        //会话Id
        //dataMap.put("chatId", );
        //设备类型
        //dataMap.put("deviceType", );
        //站点Id
        //dataMap.put("siteId", );
        //dataMap.put("createUserId", );
        //dataMap.put("extColumn1", );
        //dataMap.put("extColumn2", );
        //dataMap.put("extColumn3", );
        //dataMap.put("extColumn4", );
        //dataMap.put("extColumn5", );
        //dataMap.put("extColumn6", );
        //dataMap.put("extColumn7", );
        //dataMap.put("extColumn8", );
        //dataMap.put("extColumn9", );
        //dataMap.put("extColumn10", );

        return rpService.replaceParameter(aa, dataMap);
    }

    /**
     * 分解地域
     *
     * @param area
     * @return
     */
    public JSONObject analyzeArea(String area) {

        JSONObject json = new JSONObject();

        //广东省广州市天河区
        String[] provinceArray = area.split("省");
        if (provinceArray.length >= 2) {
            json.put("province", provinceArray[0]);
            String[] cityArray = provinceArray[1].split("市");
            if (cityArray.length >= 2) {
                json.put("city", cityArray[0]);
                json.put("area", cityArray[1]);
            }
        }
        return json;
    }

}
