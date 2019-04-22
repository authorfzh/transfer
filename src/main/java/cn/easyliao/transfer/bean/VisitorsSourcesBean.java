package cn.easyliao.transfer.bean;

import java.io.Serializable;

/**
 * 访客来源
 */
public class VisitorsSourcesBean implements Serializable {

    private static final long serialVersionUID = 1L;

    //id
    private int id;
    //来源url
    private String sourcesUrl;
    //第三方来源中文名称
    private String sourcesName;

    public VisitorsSourcesBean() {
    }

    public VisitorsSourcesBean(int id, String sourcesUrl, String sourcesName) {
        this.id = id;
        this.sourcesUrl = sourcesUrl;
        this.sourcesName = sourcesName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSourcesUrl() {
        return sourcesUrl;
    }

    public void setSourcesUrl(String sourcesUrl) {
        this.sourcesUrl = sourcesUrl;
    }

    public String getSourcesName() {
        return sourcesName;
    }

    public void setSourcesName(String sourcesName) {
        this.sourcesName = sourcesName;
    }
}
