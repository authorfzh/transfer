package cn.easyliao.transfer.bean;

import java.io.Serializable;

/**
 * 第三方配置
 */
public class ActionConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    //公司ID
    private int companyId;

    //第三方数据来源分类 0主动获取第三方数据 1第三方主动传入 2手动导入Excel
    private int type;

    //0主动获取第三方数据
    private ActiveAcquirement activeAcquirement;

    //1第三方主动传入
    private ActiveImport activeImport;

    //2手动导入Excel
    private ManualImport manualImport;

    public ActionConfig() {
    }

    public ActionConfig(int companyId, int type, ActiveAcquirement activeAcquirement, ActiveImport activeImport, ManualImport manualImport) {
        this.companyId = companyId;
        this.type = type;
        this.activeAcquirement = activeAcquirement;
        this.activeImport = activeImport;
        this.manualImport = manualImport;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ActiveAcquirement getActiveAcquirement() {
        return activeAcquirement;
    }

    public void setActiveAcquirement(ActiveAcquirement activeAcquirement) {
        this.activeAcquirement = activeAcquirement;
    }

    public ActiveImport getActiveImport() {
        return activeImport;
    }

    public void setActiveImport(ActiveImport activeImport) {
        this.activeImport = activeImport;
    }

    public ManualImport getManualImport() {
        return manualImport;
    }

    public void setManualImport(ManualImport manualImport) {
        this.manualImport = manualImport;
    }
}
