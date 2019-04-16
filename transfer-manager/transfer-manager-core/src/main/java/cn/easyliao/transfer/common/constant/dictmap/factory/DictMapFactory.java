package cn.easyliao.transfer.common.constant.dictmap.factory;

import cn.easyliao.transfer.common.constant.dictmap.base.AbstractDictMap;
import cn.easyliao.transfer.common.constant.dictmap.base.SystemDict;
import cn.easyliao.transfer.common.exception.BizExceptionEnum;
import cn.easyliao.transfer.common.exception.BussinessException;

/**
 * 字典的创建工厂
 *
 * @author 
 */
public class DictMapFactory {

    private static final String basePath = "cn.easyliao.transfer.common.constant.dictmap.";

    private static final String baseBizPath = "cn.easyliao.transfer.common.constant.bizmap.";
    /**
     * 通过类名创建具体的字典类
     */
    public static AbstractDictMap createDictMap(String className) {
        if("SystemDict".equals(className)){
            return new SystemDict();
        }else{
            try {
            	String path = basePath;
            	 if(className.startsWith("T")){
            		 path = baseBizPath;
            	 }
                Class<AbstractDictMap> clazz = (Class<AbstractDictMap>) Class.forName(path + className);
                return clazz.newInstance();
            } catch (Exception e) {
                throw new BussinessException(BizExceptionEnum.ERROR_CREATE_DICT);
            }
        }
    }
}
