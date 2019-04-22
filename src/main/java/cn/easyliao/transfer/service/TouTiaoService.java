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
 * 今日头条、抖音
 */
public class TouTiaoService implements ChannelInterface {

    @Autowired
    private static AllocationCardService acService = new AllocationCardService();

    @Autowired
    private static ReplaceParameterService rpService = new ReplaceParameterService();

    @Override
    public String getData(ActionConfig ac) {
        System.out.println("今日头条、抖音");

        ActiveAcquirement aa = ac.getActiveAcquirement();
        int companyId = ac.getCompanyId();

        //TODO : 今日头条、抖音获取数据
        String data = "";
        //TODO : 今日头条、抖音转换数据
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
        return "今日头条、抖音";
    }

    /**
     * 参数替换
     */
    public String replaceParameter(ActiveAcquirement aa, String data) {

        //TODO : 将data转为map
        Map<String, Object> dataMap = new HashMap<>();

        return rpService.replaceParameter(aa, dataMap);
    }
}
