package cn.easyliao.transfer.Factory;


import cn.easyliao.transfer.bean.ActionConfig;
import cn.easyliao.transfer.service.KmwayService;
import cn.easyliao.transfer.service.LianZhanSEService;
import cn.easyliao.transfer.service.LianZhanTOFService;
import cn.easyliao.transfer.service.TouTiaoService;

/**
 * 数据来源渠道工厂
 */
public abstract class ChannelFactory {

    private static ChannelInterface tts = new TouTiaoService();
    private static ChannelInterface ks = new KmwayService();
    private static ChannelInterface lztofs = new LianZhanTOFService();
    private static ChannelInterface lzses = new LianZhanSEService();

    public static ChannelInterface getInstance(ActionConfig ac) {

        if (ac == null)
            return null;

        /*
         * 今日头条、抖音  touTiao
         * 联展-78、联展-89178  lianZhanSE
         * 联展-快马网 kmway
         * 联展-好315 lianZhanTOF
         */
        String sourceType = ac.getActiveAcquirement().getDataSource();

        if (sourceType.equals("touTiao")) {
            return tts;
        } else if (sourceType.equals("lianZhanSE")) {
            return lzses;
        } else if (sourceType.equals("kmway")) {
            return ks;
        } else if (sourceType.equals("lianZhanTOF")) {
            return lztofs;
        }

        return null;
    }
}
