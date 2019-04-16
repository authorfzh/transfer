package cn.easyliao.transfer.modular.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.easyliao.transfer.common.controller.BaseController;
import cn.easyliao.transfer.modular.system.dao.NoticeDao;
import cn.easyliao.transfer.modular.system.service.IDictService;

/**
 * 总览信息
 *
 * @author 
 */
@Controller
@RequestMapping("/blackboard")
public class BlackboardController extends BaseController {

    @Autowired
    NoticeDao noticeDao;

  
    @Autowired
    IDictService dictService;
    /**
     * 跳转到黑板
     */
    @RequestMapping("")
    public String blackboard(Model model) {
    	
    	
        List<Map<String, Object>> notices = noticeDao.list(null);
        model.addAttribute("noticeList",notices);
        return "/blackboard.html";
    }
}
