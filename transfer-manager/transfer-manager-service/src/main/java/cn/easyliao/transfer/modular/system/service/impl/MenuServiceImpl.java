package cn.easyliao.transfer.modular.system.service.impl;

import cn.easyliao.transfer.modular.system.dao.MenuDao;
import cn.easyliao.transfer.modular.system.service.IMenuService;
import cn.easyliao.transfer.common.persistence.dao.MenuMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 菜单服务
 *
 * @author 
 */
@Service
public class MenuServiceImpl implements IMenuService {

    @Resource
    MenuMapper menuMapper;

    @Resource
    MenuDao menuDao;

    @Override
    public void delMenu(Integer menuId) {

        //删除菜单
        this.menuMapper.deleteById(menuId);

        //删除关联的relation
        this.menuDao.deleteRelationByMenu(menuId);
    }
}
