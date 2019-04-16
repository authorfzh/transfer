package cn.easyliao.transfer.modular.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.easyliao.transfer.common.controller.BaseController;
import cn.easyliao.transfer.core.shiro.ShiroKit;
import cn.easyliao.transfer.modular.system.dao.NoticeDao;
import cn.easyliao.transfer.modular.system.service.IDictService;

/**
 * 单点登录的
 * @author chy
 *
 */
@Controller
@RequestMapping("/blackboardsso")
public class BlackboardSsoController extends BaseController {

    @Autowired
    NoticeDao noticeDao;

    
    @Autowired
    IDictService dictService;
    
    /**
     * 跳转到黑板
     */
    @RequestMapping("")
    public String blackboard(Model model) {
    	Integer userId = ShiroKit.getUser().getId();
    	//List<Map<String, Object>> applicationList = userAuthorityDao.selectByAuthorityForApplication(userId);

        return "/blackboardsso.html";
    }
}
