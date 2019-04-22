package cn.easyliao.transfer.controller;



import cn.easyliao.transfer.service.SourcesTypeService;
import cn.easyliao.transfer.util.COMMON;
import cn.easyliao.transfer.util.CommunalUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * 第三方主动调用接口传入或手动导入数据
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/sources")
public class SourcesTypeController {

    @Autowired
    private SourcesTypeService service;

    /**
     * 第三方主动调用接口传入
     *
     * @param piid    tf_push_interface推送接口表的id值
     * @param request
     * @return
     */
    @RequestMapping(value = "/import/{piid}", method = RequestMethod.POST)
    public String activeImportData(@RequestBody String data, @PathVariable("piid") int piid, HttpServletRequest request) {

        if (StringUtils.isEmpty(data) || piid == 0)
            return CommunalUtils.hint(request, COMMON.CODE_err, "传入参数错误，请求失败", null, 0, false);

        return service.activeImportData(data, piid, request);
    }

    /**
     * 第三方手动导入
     *
     * @param file      文件
     * @param companyId 公司id
     * @param channelId 渠道
     * @param groupId   业务组
     * @param saleId    销售人员
     * @param request
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public String manualImportData(@RequestParam(required = false) MultipartFile file, int companyId, int channelId, int groupId, int saleId, HttpServletRequest request) {

        try {
            if (companyId == 0 || channelId == 0 || groupId == 0 || saleId == 0)
                return CommunalUtils.hint(request, COMMON.CODE_err, "参数错误，请求失败", null, 0, false);

            if (file == null)
                return CommunalUtils.hint(request, COMMON.CODE_err, "上传文件为空，请求失败", null, 0, false);

            InputStream is = file.getInputStream();

            return service.manualImportData(is, companyId, channelId, groupId, saleId, request);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
