package cn.easyliao.transfer.mapper;

import cn.easyliao.transfer.entity.TfMenu;
import cn.easyliao.transfer.util.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface TfMenuMapper  extends BaseDao<TfMenu> {

}
