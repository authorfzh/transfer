package cn.easyliao.transfer.service;


import cn.easyliao.transfer.Factory.ChannelFactory;
import cn.easyliao.transfer.Factory.SourcesTypeInterface;
import cn.easyliao.transfer.bean.ActionConfig;

/**
 * 主动获取第三方数据
 */
public class ActiveAcquirementImpl implements SourcesTypeInterface {

    @Override
    public String setData(ActionConfig ac, String data) {
        return ChannelFactory.getInstance(ac).getData(ac);
    }
}
