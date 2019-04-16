package cn.easyliao.transfer.modular.system.warpper;

import cn.easyliao.transfer.common.constant.factory.ConstantFactory;
import cn.easyliao.transfer.common.warpper.BaseControllerWarpper;

import java.util.Map;

/**
 * 部门列表的包装
 *
 * @author zhangchunming
 * @date 2017年4月25日 18:10:31
 */
public class JColllogWrapper extends BaseControllerWarpper {

    public JColllogWrapper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        Integer creater = (Integer) map.get("creater");
        map.put("createrName", ConstantFactory.me().getUserNameById(creater));
    }

}
