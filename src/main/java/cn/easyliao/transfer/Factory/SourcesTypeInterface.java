package cn.easyliao.transfer.Factory;


import cn.easyliao.transfer.bean.ActionConfig;

/**
 * 数据来源分类工厂接口
 */
public interface SourcesTypeInterface {
    public String setData(ActionConfig ac, String data);
}
