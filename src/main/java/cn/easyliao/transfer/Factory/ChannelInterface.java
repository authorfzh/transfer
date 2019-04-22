package cn.easyliao.transfer.Factory;

import cn.easyliao.transfer.bean.ActionConfig;

/**
 * 数据来源渠道工厂接口
 */
public interface ChannelInterface {
    public String getData(ActionConfig ac);
}
