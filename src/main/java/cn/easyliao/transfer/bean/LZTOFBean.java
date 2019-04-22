package cn.easyliao.transfer.bean;

import java.io.Serializable;

/**
 * 联展-好315
 */
public class LZTOFBean implements Serializable {

    private static final long serialVersionUID = 1L;

    //名称
    private String m_title;
    //手机(备注：有可能是字符串，需处理。如：qwangpingp)
    private String m_tel;
    //留言
    private String ly;
    //创建日期(如：2019-04-09 15:20:51)
    private String m_addtime;
    //地域
    private String m_addr;
    //项目
    private String mnames;
    //消息类型
    private String type;

    public LZTOFBean() {
    }

    public LZTOFBean(String m_title, String m_tel, String ly, String m_addtime, String m_addr, String mnames, String type) {
        this.m_title = m_title;
        this.m_tel = m_tel;
        this.ly = ly;
        this.m_addtime = m_addtime;
        this.m_addr = m_addr;
        this.mnames = mnames;
        this.type = type;
    }

    public String getM_title() {
        return m_title;
    }

    public void setM_title(String m_title) {
        this.m_title = m_title;
    }

    public String getM_tel() {
        return m_tel;
    }

    public void setM_tel(String m_tel) {
        this.m_tel = m_tel;
    }

    public String getLy() {
        return ly;
    }

    public void setLy(String ly) {
        this.ly = ly;
    }

    public String getM_addtime() {
        return m_addtime;
    }

    public void setM_addtime(String m_addtime) {
        this.m_addtime = m_addtime;
    }

    public String getM_addr() {
        return m_addr;
    }

    public void setM_addr(String m_addr) {
        this.m_addr = m_addr;
    }

    public String getMnames() {
        return mnames;
    }

    public void setMnames(String mnames) {
        this.mnames = mnames;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
