package cn.easyliao.transfer.modular.system.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 通知dao
 *
 * @author 
 */
public interface NoticeDao {

    List<Map<String, Object>> list(@Param("condition") String condition);
}
