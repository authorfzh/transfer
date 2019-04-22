package cn.easyliao.transfer.service;

import cn.easyliao.transfer.Factory.SourcesTypeFactory;
import cn.easyliao.transfer.bean.ActionConfig;
import cn.easyliao.transfer.bean.ManualImport;
import cn.easyliao.transfer.bean.VisitorExcelBean;
import cn.easyliao.transfer.util.COMMON;
import cn.easyliao.transfer.util.CommunalUtils;
import cn.easyliao.transfer.util.excelutil.ImportExcelUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class SourcesTypeService {

    private final static Log log = LogFactory.getLog(SourcesTypeService.class);

    /**
     * 第三方主动调用接口传入
     *
     * @param data
     * @param piid    tf_push_interface推送接口表的id值
     * @param request
     * @return
     */
    public String activeImportData(String data, int piid, HttpServletRequest request) {

        log.info("第三方主动调用接口传入:" + piid + "," + data);

        //TODO : piid需要获取数据库或者缓存数据
        ActionConfig ac = new ActionConfig();
        ac.setType(1);

        if (ac == null)
            return CommunalUtils.hint(request, COMMON.CODE_err, piid + "配置不存在，请求失败", null, 0, false);

        String info = SourcesTypeFactory.getInstance(ac, data).setData(ac, data);

        return info;
    }

    /**
     * 第三方手动导入
     *
     * @param file      文件流
     * @param companyId 公司id
     * @param channelId 渠道id
     * @param groupId   业务组id
     * @param saleId    销售人员id
     * @param request
     * @return
     */
    public String manualImportData(InputStream file, int companyId, int channelId, int groupId, int saleId, HttpServletRequest request) {

        log.info("第三方手动导入:" + companyId + "," + channelId + "," + groupId + "," + saleId);

        List<VisitorExcelBean> visitorList = new ArrayList<>();

        try {
            visitorList = ImportExcelUtil.importExcel(file, VisitorExcelBean.class);
        } catch (Exception e) {
            return CommunalUtils.hint(request, COMMON.CODE_err, "解析文件出错，请求失败", null, 0, false);
        }

        if (CollectionUtils.isEmpty(visitorList))
            return CommunalUtils.hint(request, COMMON.CODE_err, "上传文件中无数据，请求失败", null, 0, false);

        ActionConfig ac = new ActionConfig();
        ManualImport mi = new ManualImport();
        ac.setManualImport(mi);
        ac.setCompanyId(companyId);
        ac.setType(2);

        mi.setChannelId(channelId);
        mi.setGroupId(groupId);
        mi.setSaleId(saleId);

        String info = SourcesTypeFactory.getInstance(ac, null).setData(ac, JSON.toJSONString(visitorList));

        return info;
    }
}
