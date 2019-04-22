package cn.easyliao.transfer.controller;

import cn.easyliao.transfer.entity.MenuTree;
import cn.easyliao.transfer.entity.TfMenu;
import cn.easyliao.transfer.entity.TfUser;
import cn.easyliao.transfer.result.ResultMessage;
import cn.easyliao.transfer.service.LoginService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class LoginController extends  BaseController{

    private static Logger _logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginHtml(){
        return REDIRECT + "/login.html";
    }

    /**
     * 跳转到登录页面
     */
    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        TfUser tfUser = getSessionCustomer(session);

        if (tfUser != null) {
            return REDIRECT + "/index.html";
        } else {
            return "/login";
        }
    }

    @RequestMapping(value = "/index.html")
    public String index(HttpServletRequest request, HttpServletResponse response){
        List<TfMenu> tfMenuList = service.queryMenu();
        Map<String, Object> map = new HashMap<String, Object>();
        List<MenuTree> menuTrees = new ArrayList<MenuTree>();
        for(TfMenu tfMenu : tfMenuList){
            String parentId = tfMenu.getParentId();
            if(parentId == null){
                MenuTree tree = new MenuTree();
                tree.setId(tfMenu.getId());
                tree.setpId(tfMenu.getParentId());
                tree.setText(tfMenu.getMenuName());
                tree.setType(null);
                tree.setUrl(tfMenu.getMenuUrl());
                tree.setIcon(null);
                map.put(tree.getId(), tree);
                menuTrees.add(tree);
            }else{
                MenuTree pTree = (MenuTree)map.get(tfMenu.getParentId());
                MenuTree tree = new MenuTree();
                tree.setId(tfMenu.getId());
                tree.setpId(tfMenu.getParentId());
                tree.setText(tfMenu.getMenuName());
                tree.setType(null);
                tree.setUrl(tfMenu.getMenuUrl());
                tree.setIcon(null);
                pTree.getTreeJson().add(tree);
            }
        }
        request.setAttribute("menuJson", JSONObject.toJSONString(menuTrees));
        return "/index";
    }

    /**
     * 点击登录执行的动作
     */
    @RequestMapping(value = "/loginVali", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage loginVali(HttpServletRequest request, HttpServletResponse response) {
        ResultMessage result = new ResultMessage();
        try{
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            TfUser tf = new TfUser();
              tf.setUserId(username);
            tf.setUserName(username);
            tf.setUserPass(password);
            boolean flag = service.validUser(tf);
            if(flag){
                //验证成功
                TfUser user = service.queryUser(username);
                if(user != null){
                    request.getSession().setAttribute("tfUser", user);
                    result.setResult(ResultMessage.SUCCESS, "验证通过");
                }else{
                    result.setResult(202, "用户不存在!");
                }
            }else{
                //验证失败
                result.setResult(ResultMessage.AUTH_ERROR, "验证失败,用户名或者密码错误");
            }
        }catch(Exception e){
            result.setResult(ResultMessage.SYSTEM_ERROR, "验证失败,系统验证异常");
            _logger.error(e.getMessage(), e);
        }
        return result;
    }

    public TfUser getSessionCustomer (HttpSession session)
    {
        return (TfUser) session.getAttribute("tfUser");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("tfUser", null);
        return REDIRECT+"/login.html";
    }

}
