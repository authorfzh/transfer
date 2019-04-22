package cn.easyliao.transfer.service;


import cn.easyliao.transfer.Factory.SourcesTypeInterface;
import cn.easyliao.transfer.bean.ActionConfig;

/**
 * 第三方调用主动传入数据
 */
public class ActiveImportImpl implements SourcesTypeInterface {
    @Override
    public String setData(ActionConfig ac, String data) {
        return "调用成功";
    }

}
