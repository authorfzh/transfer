package cn.easyliao.transfer.modular.system.warpper;

import cn.easyliao.transfer.common.constant.factory.ConstantFactory;
import cn.easyliao.transfer.common.warpper.BaseControllerWarpper;

import java.util.Map;

/**
 * 部门列表的包装
 *
 * @author 
 */
public class EDeptWrapper extends BaseControllerWarpper {

    public EDeptWrapper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        Integer creater = (Integer) map.get("creater");
        map.put("createrName", ConstantFactory.me().getUserNameById(creater));
    }

}
