package cn.easyliao.transfer.service;

import cn.easyliao.transfer.Factory.SourcesTypeInterface;
import cn.easyliao.transfer.bean.ActionConfig;
import cn.easyliao.transfer.bean.VisitorExcelBean;
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * 第三方手动导入数据
 */
public class ManualImportImpl implements SourcesTypeInterface {

    private final static Log log = LogFactory.getLog(SourcesTypeService.class);

    /**
     * ac 配置信息
     * data ExcelUtil转换后JSON字符串化数据
     *
     * @param ac
     * @param data
     * @return
     */
    @Override
    public String setData(ActionConfig ac, String data) {

        List<VisitorExcelBean> visitorList = JSON.parseArray(data, VisitorExcelBean.class);

        //TODO : 手动导入数据List需处理后进行同步及写库
        for(VisitorExcelBean ve : visitorList){
            log.info(ve.getName());
        }

        return null;
    }
}
