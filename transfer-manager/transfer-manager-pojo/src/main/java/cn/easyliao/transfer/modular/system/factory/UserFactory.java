package cn.easyliao.transfer.modular.system.factory;

import cn.easyliao.transfer.modular.system.transfer.UserDto;
import cn.easyliao.transfer.common.persistence.model.User;
import org.springframework.beans.BeanUtils;

/**
 * 用户创建工厂
 *
 * @author 
 */
public class UserFactory {

    public static User createUser(UserDto userDto){
        if(userDto == null){
            return null;
        }else{
            User user = new User();
            BeanUtils.copyProperties(userDto,user);
            return user;
        }
    }
}
