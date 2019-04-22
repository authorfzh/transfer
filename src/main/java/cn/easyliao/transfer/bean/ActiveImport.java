package cn.easyliao.transfer.bean;

import java.io.Serializable;

/**
 * 第三方数据来源分类 1第三方主动传入
 */
public class ActiveImport implements Serializable {

    private static final long serialVersionUID = 1L;

    //主键
    private int id;
    //公司ID
    private int companyId;
    //路径项目-路径渠道
    private String pathProject;
    //状态 0关 1开
    private int status;
    //渠道ID
    private int channelId;
    //项目ID
    private int groupId;

    public ActiveImport() {
    }

    public ActiveImport(int id, int companyId, String pathProject, int status, int channelId, int groupId) {
        this.id = id;
        this.companyId = companyId;
        this.pathProject = pathProject;
        this.status = status;
        this.channelId = channelId;
        this.groupId = groupId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getPathProject() {
        return pathProject;
    }

    public void setPathProject(String pathProject) {
        this.pathProject = pathProject;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
