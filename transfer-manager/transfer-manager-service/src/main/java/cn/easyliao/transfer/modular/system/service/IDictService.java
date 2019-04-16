package cn.easyliao.transfer.modular.system.service;

/**
 * 字典服务
 *
 * @author 
 */
public interface IDictService {

    /**
     * 添加字典
     *
     * @author 
     */
    void addDict(String dictName, String dictValues);

    /**
     * 编辑字典
     *
     * @author 
     */
    void editDict(Integer dictId, String dictName, String dicts);

    /**
     * 删除字典
     *
     * @author 
     */
    void delteDict(Integer dictId);

}
