package cn.easyliao.transfer.bean;

import cn.easyliao.transfer.util.excelutil.ExcelVO;

import java.io.Serializable;

public class VisitorExcelBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelVO(name = "名称", column = "A")
    private String name;

    @ExcelVO(name = "手机号", column = "B")
    private long mobile;

    @ExcelVO(name = "邮箱", column = "C")
    private String email;

    @ExcelVO(name = "地区", column = "D")
    private String region;

    @ExcelVO(name = "关键词", column = "E")
    private String keywords;

    @ExcelVO(name = "渠道来源", column = "F")
    private String source;

    @ExcelVO(name = "留言时间", column = "G")
    private String messageTime;

    @ExcelVO(name = "留言内容", column = "H")
    private String message;

    @ExcelVO(name = "扩展", column = "I")
    private String extension;

    public VisitorExcelBean() {
    }

    public VisitorExcelBean(String name, long mobile, String email, String region, String keywords, String source, String messageTime, String message, String extension) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.region = region;
        this.keywords = keywords;
        this.source = source;
        this.messageTime = messageTime;
        this.message = message;
        this.extension = extension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
