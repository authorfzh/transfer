package cn.easyliao.transfer.Factory;

import cn.easyliao.transfer.bean.ActionConfig;
import cn.easyliao.transfer.service.ActiveAcquirementImpl;
import cn.easyliao.transfer.service.ActiveImportImpl;
import cn.easyliao.transfer.service.ManualImportImpl;
import com.alibaba.fastjson.JSON;

/**
 * 数据来源分类工厂
 */
public abstract class SourcesTypeFactory {

    private static SourcesTypeInterface aaImpl = new ActiveAcquirementImpl();
    private static SourcesTypeInterface aiImpl = new ActiveImportImpl();
    private static SourcesTypeInterface miImpl = new ManualImportImpl();

    public static SourcesTypeInterface getInstance(ActionConfig ac, String data) {

        System.out.println("SourcesTypeFactory:" + JSON.toJSONString(ac));

        if (ac == null)
            return null;

        ////第三方数据来源分类 0主动获取第三方数据 1第三方主动传入 2手动导入Excel
        if (ac.getType() == 0)
            return aaImpl;
        if (ac.getType() == 1)
            return aiImpl;
        if (ac.getType() == 2)
            return miImpl;

        return null;
    }
}
