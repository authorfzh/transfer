package cn.easyliao.transfer.service;

import cn.easyliao.transfer.entity.TfMenu;
import cn.easyliao.transfer.entity.TfUser;
import cn.easyliao.transfer.mapper.TfMenuMapper;
import cn.easyliao.transfer.mapper.TfUserMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    private TfUserMapper userMapper;

    @Autowired
    private TfMenuMapper menuMapper;

    public boolean validUser(TfUser tf)  throws Exception {
        tf.setUserPass(DigestUtils.md5Hex(tf.getUserPass()));
        List<TfUser> userList = userMapper.validUser(tf);
        if(userList == null||userList.size()==0) return false;
        return true;
    }

    public TfUser queryUser(String username) {
        TfUser tfUser = userMapper.selectById(username);
        return tfUser;
    }

    public List<TfMenu> queryMenu() {
        List<TfMenu> menuList = menuMapper.selectList(null);
        return menuList;
    }
}
