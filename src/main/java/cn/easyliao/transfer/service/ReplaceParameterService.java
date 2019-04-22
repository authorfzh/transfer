package cn.easyliao.transfer.service;


import cn.easyliao.transfer.bean.ActiveAcquirement;
import cn.easyliao.transfer.bean.VisitorCardData;
import cn.easyliao.transfer.util.CardUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 参数替换
 */
public class ReplaceParameterService {

    /**
     * 参数替换
     *
     * @param aa      渠道配置
     * @param dataMap 渠道数据
     * @return
     */
    public String replaceParameter(ActiveAcquirement aa, Map<String, Object> dataMap) {

        VisitorCardData vcard = new VisitorCardData(dataMap);

        String refer = "直接输入";

        if (StringUtils.isEmpty(vcard.getRefer())) {
            //来源为空时，取配置中的refer
            String configRefer = CardUtil.getValue(aa.getConfig(), "refer");
            if (StringUtils.isNotEmpty(configRefer))
                refer = configRefer;
        } else {
            String analysisRefer = analysisSources(vcard.getRefer());
            if (StringUtils.isNotEmpty(analysisRefer))
                refer = analysisRefer;
        }

        vcard.setRefer(refer);

        //将VisitorCardData转为EC数据
        String template = "{\"81224611\":\"$mobile\",\"81225022\":\"$weixin\",\"81225807\":\"$mobile\",\"81227188\":\"$keyword\",\"customFieldMapping\":{\"81224611\":{\"type\":\"1\"},\"81225022\":{\"type\":\"1\"},\"81225807\":{\"type\":\"1\"},\"81227188\":{\"type\":\"1\"}},\"f_channel\":\"$refer\",\"f_company\":\"$companyName\",\"f_company_city\":\"$city\",\"f_company_province\":\"$province\",\"f_email\":\"$email\",\"f_memo\":\"$note\",\"f_mobile\":\"$mobile\",\"f_name\":\"$name\",\"f_phone\":\"$tel\",\"f_qq\":\"$qq\",\"followUserId\":\"$userCode\",\"optUserId\":\"$userCode\"}";
        template = vcard.replaceTemplateAllValues(template, null);

        return template;
    }


    /**
     * 分析来源
     *
     * @param refer 访客来源URL或来源名称，例如联展78返回refer值为：89178商机网
     * @return
     */
    public String analysisSources(String refer) {

        //TODO : 获取访客来源对应关系，是否将访客来源URL(refer)替换为EC中文名称
        return null;
    }
}
