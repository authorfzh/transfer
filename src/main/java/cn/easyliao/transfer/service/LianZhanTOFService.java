package cn.easyliao.transfer.service;

import cn.easyliao.transfer.Factory.ChannelInterface;
import cn.easyliao.transfer.bean.ActionConfig;
import cn.easyliao.transfer.bean.ActiveAcquirement;
import cn.easyliao.transfer.bean.LZTOFBean;
import cn.easyliao.transfer.util.Base64;
import cn.easyliao.transfer.util.CardUtil;
import cn.easyliao.transfer.util.HttpClientUtil;
import cn.easyliao.transfer.util.KeyUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 联展-好315
 */
public class LianZhanTOFService implements ChannelInterface {

    @Autowired
    private static AllocationCardService acService = new AllocationCardService();

    @Autowired
    private static ReplaceParameterService rpService = new ReplaceParameterService();

    private final static Log log = LogFactory.getLog(LianZhanSEService.class);

    @Override
    public String getData(ActionConfig ac) {
        System.out.println("联展-好315");

        ActiveAcquirement aa = ac.getActiveAcquirement();
        int companyId = ac.getCompanyId();

        //当前时间戳
        long ctime = System.currentTimeMillis();

        //当前日期
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateformat.format(ctime);

        //token=base64_encode(md5('BigBackgroundMessageInterface'))
        String token = Base64.getBase64(KeyUtil.encryption("BigBackgroundMessageInterface"));
        log.info("token:" + token);

        //time_start=查询开始日期2019-04-02
        //time_end=查询结束日期2019-04-03
        String url = CardUtil.getValue(aa.getConfig(), "url") + "?token=" + token + "&time_start=" + currentDate + "&time_end=" + currentDate;

        log.info("url:" + url);

        String listData = HttpClientUtil.httpGet(url, "application/json;charset=utf-8");
        log.info("listData:" + listData);

        JSONObject json = JSON.parseObject(listData);

        if (!json.containsKey("code")) {
            log.info("获取联展-好315数据：" + listData + ",数据返回错误");
            return null;
        }

        if (json.getInteger("code") != 0) {
            log.info("获取联展-好315数据：" + listData + ",数据返回错误");
            return null;
        }

        List<LZTOFBean> list = JSON.parseArray(json.getString("res"), LZTOFBean.class);

        //获取指定数据
        for (LZTOFBean l : list) {

            //数据统一转换为易聊名片数据
            String data = replaceParameter(companyId, aa, l);

            if (StringUtils.isEmpty(data)) {
                log.info("获取联展-好315数据：转换数据" + JSON.toJSONString(l) + ",结果为空");
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
        return "联展-好315";
    }

    /**
     * 参数替换
     */
    public String replaceParameter(int companyId, ActiveAcquirement aa, LZTOFBean bean) {

        Map<String, Object> dataMap = new HashMap<>();

        //公司id
        dataMap.put("companyId", companyId);
        //访客名称
        dataMap.put("name", bean.getM_title());
        //手机
        //TODO : 好315 mobile需要处理
        dataMap.put("mobile", bean.getM_tel());
        //备注
        dataMap.put("note", bean.getLy());
        //省
        dataMap.put("province", bean.getM_addr());
        //关键词
        dataMap.put("keyword", bean.getMnames());
        //访客来源
        dataMap.put("refer", bean.getMnames());
        //创建时间
        dataMap.put("createTime", bean.getM_addtime());

        //市
        //dataMap.put("city", area.getString("city"));
        //区
        //dataMap.put("area", area.getString("area"));
        //邮箱
        //dataMap.put("email", lzseBean.getEamil());
        //QQ
        //dataMap.put("qq", bean.get());
        //dataMap.put("id",lzseBean.getData_id());
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
}
