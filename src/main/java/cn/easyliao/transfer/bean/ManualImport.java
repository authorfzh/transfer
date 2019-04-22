package cn.easyliao.transfer.bean;

import java.io.Serializable;

/**
 * 第三方数据来源分类 2手动导入Excel
 */
public class ManualImport implements Serializable {

    private static final long serialVersionUID = 1L;

    //渠道
    private int	channelId;
    //业务组
    private int groupId;
    //销售人员
    private int saleId;

    public ManualImport() {
    }

    public ManualImport(int channelId, int groupId, int saleId) {
        this.channelId = channelId;
        this.groupId = groupId;
        this.saleId = saleId;
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

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }
}
