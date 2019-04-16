package cn.easyliao.transfer.modular.system.service;

/**
 * 角色相关业务
 *
 * @author zhangchunming
 * @Date 2017年1月10日 下午9:11:57
 */
public interface IRoleService {

    /**
     * 设置某个角色的权限
     *
     * @param roleId 角色id
     * @param ids    权限的id
     * 
     */
    void setAuthority(Integer roleId, String ids);

    /**
     * 删除角色
     *
     * @author 
     */
    void delRoleById(Integer roleId);
}
