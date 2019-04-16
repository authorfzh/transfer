package cn.easyliao.transfer.modular.system.warpper;

import cn.easyliao.transfer.common.constant.factory.ConstantFactory;
import cn.easyliao.transfer.common.select.constant.IsInvalid;
import cn.easyliao.transfer.common.warpper.BaseControllerWarpper;

import java.util.Map;

/**
 * 文件上传下载的包装
 * @author chy
 *
 */
public class SFileWrapper extends BaseControllerWarpper {

    public SFileWrapper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        Integer creater = (Integer) map.get("creater");
        map.put("createrName", ConstantFactory.me().getUserNameById(creater));
        map.put("statename",IsInvalid .valueOf((Integer) map.get("state")));
    }

}
