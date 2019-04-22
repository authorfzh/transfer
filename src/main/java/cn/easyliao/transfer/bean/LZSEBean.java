package cn.easyliao.transfer.bean;

import java.io.Serializable;

/**
 * 联展78、89178
 */
public class LZSEBean implements Serializable {

    private static final long serialVersionUID = 1L;

    //数据编码
    private String data_id;
    //项目名称
    private String custom_name;
    //留言时间
    private String datetime;
    //访客姓名
    private String uname;
    //访客电话
    private long phone;
    //访客QQ
    private long qq;
    //访客邮箱
    private String eamil;
    //访客地址
    private String address;
    //项目ID
    private String project_id;
    //留言内容
    private String content;
    //访客地域
    private String area;
    //数据状态
    private String sta;
    //来源平台
    private String plat_name;
    //数据类型
    private String dtype;
    //通话时长（分）
    private String call_len;
    //绑定的接听电话
    private String call_answer;

    public LZSEBean() {
    }

    public LZSEBean(String data_id, String custom_name, String datetime, String uname, long phone, long qq, String eamil, String address, String project_id, String content, String area, String sta, String plat_name, String dtype, String call_len, String call_answer) {
        this.data_id = data_id;
        this.custom_name = custom_name;
        this.datetime = datetime;
        this.uname = uname;
        this.phone = phone;
        this.qq = qq;
        this.eamil = eamil;
        this.address = address;
        this.project_id = project_id;
        this.content = content;
        this.area = area;
        this.sta = sta;
        this.plat_name = plat_name;
        this.dtype = dtype;
        this.call_len = call_len;
        this.call_answer = call_answer;
    }

    public String getData_id() {
        return data_id;
    }

    public void setData_id(String data_id) {
        this.data_id = data_id;
    }

    public String getCustom_name() {
        return custom_name;
    }

    public void setCustom_name(String custom_name) {
        this.custom_name = custom_name;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public long getQq() {
        return qq;
    }

    public void setQq(long qq) {
        this.qq = qq;
    }

    public String getEamil() {
        return eamil;
    }

    public void setEamil(String eamil) {
        this.eamil = eamil;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSta() {
        return sta;
    }

    public void setSta(String sta) {
        this.sta = sta;
    }

    public String getPlat_name() {
        return plat_name;
    }

    public void setPlat_name(String plat_name) {
        this.plat_name = plat_name;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public String getCall_len() {
        return call_len;
    }

    public void setCall_len(String call_len) {
        this.call_len = call_len;
    }

    public String getCall_answer() {
        return call_answer;
    }

    public void setCall_answer(String call_answer) {
        this.call_answer = call_answer;
    }
}
