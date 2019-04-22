package cn.easyliao.transfer.bean;

import java.io.Serializable;

/**
 * 第三方数据来源分类 0主动获取第三方数据
 */
public class ActiveAcquirement implements Serializable {

    private static final long serialVersionUID = 1L;

    //主键
    private int id;
    //数据来源
    private String dataSource;
    //配置
    private String config;
    //渠道ID
    private int	channelId;
    //项目ID
    private int groupId;
    //状态 0关 1开
    private int status;

    public ActiveAcquirement() {
    }

    public ActiveAcquirement(int id, String dataSource, String config, int channelId, int groupId, int status) {
        this.id = id;
        this.dataSource = dataSource;
        this.config = config;
        this.channelId = channelId;
        this.groupId = groupId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}


