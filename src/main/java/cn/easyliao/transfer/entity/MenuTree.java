package cn.easyliao.transfer.entity;

import java.util.ArrayList;
import java.util.List;

public class MenuTree {

    private String id ;

    private String text;

    private String type;

    private String pId;

    private String url;

    private List<MenuTree> treeJson = new ArrayList<MenuTree>();

    private String icon;

    public List<MenuTree> getTreeJson() {
        return treeJson;
    }

    public void setTreeJson(List<MenuTree> treeJson) {
        this.treeJson = treeJson;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
