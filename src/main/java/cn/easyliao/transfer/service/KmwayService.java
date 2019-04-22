package cn.easyliao.transfer.service;

import cn.easyliao.transfer.Factory.ChannelInterface;
import cn.easyliao.transfer.bean.ActionConfig;
import cn.easyliao.transfer.bean.ActiveAcquirement;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 联展-快马网
 */
public class KmwayService implements ChannelInterface {

    @Autowired
    private static AllocationCardService acService = new AllocationCardService();

    @Autowired
    private static ReplaceParameterService rpService = new ReplaceParameterService();

    @Override
    public String getData(ActionConfig ac) {
        System.out.println("联展-快马网");

        ActiveAcquirement aa = ac.getActiveAcquirement();
        int companyId = ac.getCompanyId();

        //TODO : 联展-快马网获取数据
        String data = "";
        //TODO : 联展-快马网转换数据
        data = replaceParameter(aa, data);
        try {
            acService.init(aa, data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "联展-快马网";
    }

    /**
     * 参数替换
     */
    public String replaceParameter(ActiveAcquirement aa, String data) {

        //TODO : 将data转为map
        Map<String, Object> dataMap = new HashMap<>();

        return rpService.replaceParameter(aa,dataMap);
    }
}
