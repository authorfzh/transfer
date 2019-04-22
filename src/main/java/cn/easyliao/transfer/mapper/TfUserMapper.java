package cn.easyliao.transfer.mapper;

import cn.easyliao.transfer.entity.TfUser;
import cn.easyliao.transfer.util.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface TfUserMapper extends BaseDao<TfUser> {

    public List<TfUser> validUser(TfUser user)  throws Exception;

}
