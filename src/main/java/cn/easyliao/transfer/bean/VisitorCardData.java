package cn.easyliao.transfer.bean;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class VisitorCardData {

    private Integer id;
    private String visitorStaticId;
    private Integer companyId;
    private String name;
    private String allocationTime;
    private String email;
    private String note;
    private String sex;
    private String repName;
    private String qq;
    private String weixin;
    private String url;
    private String companyName;
    private String area;
    private String userId;
    private String createUserId;
    private String extColumn1;
    private String extColumn2;
    private String extColumn3;
    private String extColumn4;
    private String extColumn5;
    private String extColumn6;
    private String extColumn7;
    private String extColumn8;
    private String extColumn9;
    private String extColumn10;
    private String searchEngine;
    private String keyword;
    private String refer;
    private String chatURL;
    private String searchHost;
    private String spreadFlag;
    private String firstUrl;
    private String promotionId;
    private String type;
    private String crmstate;
    private String createTime;
    private String editTime;
    private String hideMobile;
    private String tel;
    private String mobile;
    private String chatId;
    private String country;
    private String province;
    private String city;
    private String deviceType;
    private String siteId;

    private Map<String, Object> map;

    public VisitorCardData() {
        map = new HashMap<String, Object>();
    }

    public VisitorCardData(Map<String, Object> dbData) {
        map = new HashMap<String, Object>();

        //TODO : VisitorCardData参数都需要做三元运算判断
        setId(Integer.parseInt(dbData.get("id").toString()));
        setVisitorStaticId((String) dbData.get("visitorStaticId"));
        setCompanyId(Integer.parseInt(dbData.get("companyId").toString()));
        setName((String) dbData.get("name"));
        setAllocationTime((String) dbData.get("allocationTime"));
        setEmail((String) dbData.get("email"));
        setNote((String) dbData.get("note"));
        setSex((String) dbData.get("sex"));
        setRepName((String) dbData.get("repName"));
        setQq((String) dbData.get("qq"));
        setWeixin((String) dbData.get("msn"));
        setUrl((String) dbData.get("url"));
        setCompanyName((String) dbData.get("companyName"));
        setArea((String) dbData.get("area"));
        setUserId((String) dbData.get("userId"));
        setCreateUserId((String) dbData.get("createUserId"));
        setExtColumn1((String) dbData.get("extColumn1"));
        setExtColumn2((String) dbData.get("extColumn2"));
        setExtColumn3((String) dbData.get("extColumn3"));
        setExtColumn4((String) dbData.get("extColumn4"));
        setExtColumn5((String) dbData.get("extColumn5"));
        setExtColumn6((String) dbData.get("extColumn6"));
        setExtColumn7((String) dbData.get("extColumn7"));
        setExtColumn8((String) dbData.get("extColumn8"));
        setExtColumn9((String) dbData.get("extColumn9"));
        setExtColumn10((String) dbData.get("extColumn10"));
        setSearchEngine((String) dbData.get("searchEngine"));
        setKeyword((String) dbData.get("keyword"));
        setRefer((String) dbData.get("refer"));
        setChatURL((String) dbData.get("chatURL"));
        setSearchHost((String) dbData.get("searchHost"));
        setSpreadFlag((String) dbData.get("spreadFlag"));
        setFirstUrl((String) dbData.get("firstUrl"));
        setPromotionId(dbData.get("promotionId") == null ? null : dbData.get("promotionId").toString());
        setType((String) dbData.get("type"));
        setCrmstate(dbData.get("crmState") == null ? null : dbData.get("crmState").toString());
        setCreateTime((String) dbData.get("createTime"));
        setEditTime((String) dbData.get("editTime"));
        setHideMobile((String) dbData.get("hideMobile"));
        setTel((String) dbData.get("tel"));
        setMobile((String) dbData.get("mobile"));
        setChatId(dbData.get("chatId") == null ? null : dbData.get("chatId").toString());
        setCountry((String) dbData.get("country"));
        setProvince((String) dbData.get("province"));
        setCity((String) dbData.get("city"));
        setDeviceType((String) dbData.get("deviceType"));
        setSiteId(dbData.get("siteId") == null ? null : dbData.get("siteId").toString());
    }

    public Object getProperty(String key) {
        return map.get(key);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        map.put("id", id);
    }

    public String getVisitorStaticId() {
        return visitorStaticId;
    }

    public void setVisitorStaticId(String visitorStaticId) {
        this.visitorStaticId = visitorStaticId;
        map.put("visitorStaticId", visitorStaticId);
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
        map.put("companyId", companyId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        map.put("name", name);
    }

    public String getAllocationTime() {
        return allocationTime;
    }

    public void setAllocationTime(String allocationTime) {
        this.allocationTime = allocationTime;
        map.put("allocationTime", allocationTime);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        map.put("email", email);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
        map.put("note", note);
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
        map.put("sex", sex);
    }

    public String getRepName() {
        return repName;
    }

    public void setRepName(String repName) {
        this.repName = repName;
        map.put("repName", repName);
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
        map.put("qq", qq);
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
        map.put("weixin", weixin);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        map.put("url", url);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
        map.put("companyName", companyName);
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
        map.put("area", area);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        map.put("userId", userId);
    }

    public String getExtColumn1() {
        return extColumn1;
    }

    public void setExtColumn1(String extColumn1) {
        this.extColumn1 = extColumn1;
        map.put("extColumn1", extColumn1);
    }

    public String getExtColumn2() {
        return extColumn2;
    }

    public void setExtColumn2(String extColumn2) {
        this.extColumn2 = extColumn2;
        map.put("extColumn2", extColumn2);
    }

    public String getExtColumn3() {
        return extColumn3;
    }

    public void setExtColumn3(String extColumn3) {
        this.extColumn3 = extColumn3;
        map.put("extColumn3", extColumn3);
    }

    public String getExtColumn4() {
        return extColumn4;
    }

    public void setExtColumn4(String extColumn4) {
        this.extColumn4 = extColumn4;
        map.put("extColumn4", extColumn4);
    }

    public String getExtColumn5() {
        return extColumn5;
    }

    public void setExtColumn5(String extColumn5) {
        this.extColumn5 = extColumn5;
        map.put("extColumn5", extColumn5);
    }

    public String getExtColumn6() {
        return extColumn6;
    }

    public void setExtColumn6(String extColumn6) {
        this.extColumn6 = extColumn6;
        map.put("extColumn6", extColumn6);
    }

    public String getExtColumn7() {
        return extColumn7;
    }

    public void setExtColumn7(String extColumn7) {
        this.extColumn7 = extColumn7;
        map.put("extColumn7", extColumn7);
    }

    public String getExtColumn8() {
        return extColumn8;
    }

    public void setExtColumn8(String extColumn8) {
        this.extColumn8 = extColumn8;
        map.put("extColumn8", extColumn8);
    }

    public String getExtColumn9() {
        return extColumn9;
    }

    public void setExtColumn9(String extColumn9) {
        this.extColumn9 = extColumn9;
        map.put("extColumn9", extColumn9);
    }

    public String getExtColumn10() {
        return extColumn10;
    }

    public void setExtColumn10(String extColumn10) {
        this.extColumn10 = extColumn10;
        map.put("extColumn10", extColumn10);
    }

    public String getSearchEngine() {
        return searchEngine;
    }

    public void setSearchEngine(String searchEngine) {
        this.searchEngine = searchEngine;
        map.put("searchEngine", searchEngine);
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
        map.put("keyword", keyword);
    }

    public String getRefer() {
        return refer;
    }

    public void setRefer(String refer) {
        this.refer = refer;
        map.put("refer", refer);
    }

    public String getChatURL() {
        return chatURL;
    }

    public void setChatURL(String chatURL) {
        this.chatURL = chatURL;
        map.put("chatURL", chatURL);
    }

    public String getSearchHost() {
        return searchHost;
    }

    public void setSearchHost(String searchHost) {
        this.searchHost = searchHost;
        map.put("searchHost", searchHost);
    }

    public String getSpreadFlag() {
        return spreadFlag;
    }

    public void setSpreadFlag(String spreadFlag) {
        this.spreadFlag = spreadFlag;
        map.put("spreadFlag", spreadFlag);
    }

    public String getFirstUrl() {
        return firstUrl;
    }

    public void setFirstUrl(String firstUrl) {
        this.firstUrl = firstUrl;
        map.put("firstUrl", firstUrl);
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
        map.put("promotionId", promotionId);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        map.put("type", type);
    }

    public String getCrmstate() {
        return crmstate;
    }

    public void setCrmstate(String crmstate) {
        this.crmstate = crmstate;
        map.put("crmstate", crmstate);
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
        map.put("createTime", createTime);
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
        map.put("editTime", editTime);
    }

    public String getHideMobile() {
        return hideMobile;
    }

    public void setHideMobile(String hideMobile) {
        this.hideMobile = hideMobile;
        map.put("hideMobile", hideMobile);
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
        map.put("tel", tel);
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
        map.put("mobile", mobile);
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
        map.put("chatId", chatId);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
        map.put("country", country);
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
        map.put("province", province);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        map.put("city", city);
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
        map.put("deviceType", deviceType);
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
        map.put("siteId", siteId);
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
        map.put("createUserId", createUserId);
    }

    private Map<String, String> parseJSonBodyDataConfig(String bodyType, String template) {
        Map<String, String> datas = new HashMap();
        try {
            JSONObject data = (JSONObject) JSONValue.parse(template);

            Iterator it = data.keySet().iterator();

            while (it.hasNext()) {
                String key = it.next().toString();
                datas.put(key, (String) data.get(key));
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return datas;
    }

    private Map<String, String> parseHttpFormBodyDataConfig(String bodyType, String template) {
        Map<String, String> datas = new HashMap();


        return datas;
    }

    public String generateBody(String bodyType, String template) {
        if ("json".equals(bodyType)) {
            Map<String, String> datas = parseJSonBodyDataConfig(bodyType, template);

            return generateJSonBody(datas);
        } else if ("httpForm".equals(bodyType)) {
            Map<String, String> datas = parseHttpFormBodyDataConfig(bodyType, template);

            return generateHttpFormBody(datas);
        }

        return null;
    }

    public String generateBody(String bodyType, Map<String, String> datas) {
        if ("json".equals(bodyType)) {
            return generateJSonBody(datas);
        } else if ("httpForm".equals(bodyType)) {
            return generateHttpFormBody(datas);
        }
        return null;
    }

    protected String generateJSonBody(Map<String, String> datas) {
        JSONObject json = new JSONObject();
        Iterator<String> it = datas.keySet().iterator();

        while (it.hasNext()) {
            String key = it.next();
            String value = datas.get(key);
            try {
                if (key.startsWith("$"))
                    json.put(key, getProperty(key));
                else
                    json.put(key, value);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return json.toString();
    }

    protected String generateHttpFormBody(Map<String, String> datas) {
        StringBuffer sb = new StringBuffer();

        Iterator<String> it = datas.keySet().iterator();

        while (it.hasNext()) {
            String key = it.next();

            String value = datas.get(key);

            if (key.startsWith("$")) {

                Object v = getProperty(key);
                if (v == null)
                    value = "";

                value = v.toString();
            }

            try {
                String decodeValue = URLEncoder.encode(value.toString(), "utf-8");

                if (sb.length() > 0) {
                    sb.append("&");
                }

                sb.append(key);
                sb.append("=");
                sb.append(decodeValue);
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

    public String replaceTemplateAllValues(String template, String bodyType) {

        template = replaceTemplateValue(template, "visitorStaticId", bodyType, getVisitorStaticId());
        template = replaceTemplateValue(template, "companyId", bodyType, getCompanyId().toString());
        template = replaceTemplateValue(template, "name", bodyType, getName());

        template = replaceTemplateValue(template, "email", bodyType, getEmail());
        template = replaceTemplateValue(template, "note", bodyType, getNote());
        template = replaceTemplateValue(template, "sex", bodyType, getSex());
        template = replaceTemplateValue(template, "repName", bodyType, getRepName());
        template = replaceTemplateValue(template, "qq", bodyType, getQq());
        template = replaceTemplateValue(template, "weixin", bodyType, getWeixin());
        template = replaceTemplateValue(template, "url", bodyType, getUrl());
        template = replaceTemplateValue(template, "companyName", bodyType, getCompanyName());
        template = replaceTemplateValue(template, "area", bodyType, getArea());
        template = replaceTemplateValue(template, "userId", bodyType, getUserId());
        template = replaceTemplateValue(template, "createUserId", bodyType, getCreateUserId());
        template = replaceTemplateValue(template, "extColumn1", bodyType, getExtColumn1());
        template = replaceTemplateValue(template, "extColumn2", bodyType, getExtColumn2());
        template = replaceTemplateValue(template, "extColumn3", bodyType, getExtColumn3());
        template = replaceTemplateValue(template, "extColumn4", bodyType, getExtColumn4());
        template = replaceTemplateValue(template, "extColumn5", bodyType, getExtColumn5());
        template = replaceTemplateValue(template, "extColumn6", bodyType, getExtColumn6());
        template = replaceTemplateValue(template, "extColumn7", bodyType, getExtColumn7());
        template = replaceTemplateValue(template, "extColumn8", bodyType, getExtColumn8());
        template = replaceTemplateValue(template, "extColumn9", bodyType, getExtColumn9());
        template = replaceTemplateValue(template, "extColumn10", bodyType, getExtColumn10());
        template = replaceTemplateValue(template, "searchEngine", bodyType, getSearchEngine());
        template = replaceTemplateValue(template, "keyword", bodyType, getKeyword());
        template = replaceTemplateValue(template, "refer", bodyType, getRefer());
        template = replaceTemplateValue(template, "chatURL", bodyType, getChatURL());
        template = replaceTemplateValue(template, "searchHost", bodyType, getSearchHost());
        template = replaceTemplateValue(template, "spreadFlag", bodyType, getSpreadFlag());
        template = replaceTemplateValue(template, "firstUrl", bodyType, getFirstUrl());
        template = replaceTemplateValue(template, "promotionId", bodyType, getPromotionId());
        template = replaceTemplateValue(template, "type", bodyType, getType());
        template = replaceTemplateValue(template, "crmState", bodyType, getCrmstate());
        template = replaceTemplateValue(template, "createTime", bodyType, getCreateTime());
        template = replaceTemplateValue(template, "editTime", bodyType, getEditTime());
        template = replaceTemplateValue(template, "hideMobile", bodyType, getHideMobile());
        template = replaceTemplateValue(template, "tel", bodyType, getTel());
        template = replaceTemplateValue(template, "mobile", bodyType, getMobile());
        template = replaceTemplateValue(template, "chatId", bodyType, getChatId());
        template = replaceTemplateValue(template, "country", bodyType, getCountry());
        template = replaceTemplateValue(template, "province", bodyType, getProvince());
        template = replaceTemplateValue(template, "city", bodyType, getCity());
        template = replaceTemplateValue(template, "deviceType", bodyType, getDeviceType());
        template = replaceTemplateValue(template, "siteId", bodyType, getSiteId());
        template = replaceTemplateValue(template, "allocationTime", bodyType, getAllocationTime());

        return template;
    }

    public String replaceTemplateJSonValue(String template, String attr, String value) {
        return template.replace("$" + attr, value == null ? "" : value);
    }

    public String replaceTemplateValue(String template, String attr, String bodyType, String value) {

        if(StringUtils.isEmpty(bodyType)){
            return replaceTemplateJSonValue(template,attr,value);
        }else {
            return template.replace("$" + attr, value == null ? "" : JSONObject.escape(value));
        }
    }

    private static Map initTestMapData() {
        Map map = new HashMap();

        map.put("id", 1);
        map.put("visitorStaticId", "visitorStaticIdTestValue");
        map.put("companyId", 1);
        map.put("name", "nameTestValue");
        map.put("allocationTime", "allocationTimeTestValue");
        map.put("email", "emailTestValue");
        map.put("note", "noteTestValue");
        map.put("sex", "sexTestValue");
        map.put("repName", "repNameTestValue");
        map.put("qq", "qqTestValue");
        map.put("msn", "weixinTestValue");
        map.put("url", "urlTestValue");
        map.put("companyName", "companyNameTestValue");
        map.put("area", "areaTestValue");
        map.put("userId", "userIdTestValue");
        map.put("extColumn1", "extColumn1TestValue");
        map.put("extColumn2", "extColumn2TestValue");
        map.put("extColumn3", "extColumn3TestValue");
        map.put("extColumn4", "extColumn4TestValue");
        map.put("extColumn5", "extColumn5TestValue");
        map.put("extColumn6", "extColumn6TestValue");
        map.put("extColumn7", "extColumn7TestValue");
        map.put("extColumn8", "extColumn8TestValue");
        map.put("extColumn9", "extColumn9TestValue");
        map.put("extColumn10", "extColumn10TestValue");
        map.put("searchEngine", "searchEngineTestValue");
        map.put("keyword", "keywordTestValue");
        map.put("refer", "referTestValue");
        map.put("keyword", "chatURLTestValue");
        map.put("searchHost", "searchHostTestValue");
        map.put("spreadFlag", "spreadFlagTestValue");
        map.put("firstUrl", "firstUrlTestValue");
        map.put("promotionId", "promotionIdTestValue");
        map.put("promotionId", "typeTestValue");
        map.put("crmstate", "crmstateTestValue");
        map.put("createTime", "createTimeTestValue");
        map.put("editTime", "editTimTestValuee");
        map.put("hideMobile", "hideMobileTestValue");
        map.put("tel", "telTestValue");
        map.put("mobile", "mobileTestValue");
        map.put("chatId", "chatIdTestValue");
        map.put("country", "countryTestValue");
        map.put("province", "provinceTestValue");
        map.put("city", "cityTestValue");
        map.put("deviceType", "deviceTypeTestValue");
        map.put("siteId", "siteIdTestValue");


        return map;
    }


}
