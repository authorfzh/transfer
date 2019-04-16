package cn.easyliao.transfer.modular.system.dao;

import cn.easyliao.transfer.common.persistence.model.Dict;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 字典的dao
 *
 * @author 
 */
public interface DictDao {

    /**
     * 根据编码获取词典列表
     *
     * @param code
     * @return
     * @date 2017年2月13日 下午11:11:28
     */
    List<Dict> selectByCode(@Param("code") String code);

    /**
     * 查询字典列表
     * 
     * @author 
     */
    List<Map<String,Object>> list(@Param("condition") String conditiion);
}
