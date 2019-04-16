package cn.easyliao.transfer.modular.system.controller;

import com.google.code.kaptcha.Constants;
import cn.easyliao.transfer.common.controller.BaseController;
import cn.easyliao.transfer.common.exception.InvalidKaptchaException;
import cn.easyliao.transfer.common.node.MenuNode;
import cn.easyliao.transfer.common.persistence.dao.UserMapper;
import cn.easyliao.transfer.common.persistence.model.User;
import cn.easyliao.transfer.common.pojo.IpMsg;
import cn.easyliao.transfer.common.utils.CommonUtil;
import cn.easyliao.transfer.core.shiro.ShiroKit;
import cn.easyliao.transfer.core.shiro.ShiroUser;
import cn.easyliao.transfer.core.util.ToolUtil;
import cn.easyliao.transfer.modular.system.dao.MenuDao;
import cn.easyliao.transfer.service.log.LogServiceManager;
import cn.easyliao.transfer.service.log.factory.LogServiceTaskFactory;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import static cn.easyliao.transfer.core.support.HttpKit.getIp;

/**
 * 登录控制器
 *
 * @author 
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    MenuDao menuDao;

    @Autowired
    UserMapper userMapper;
    
    /**
     * 管理员跳转到主页
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        //获取菜单列表
    	
    	
        List<Integer> roleList = ShiroKit.getUser().getRoleList();
       
        List<MenuNode> menus = menuDao.getMenusByRoleIds(roleList);
        List<MenuNode> titles = MenuNode.buildTitle(menus);
        model.addAttribute("titles", titles);
        
        //获取用户头像
        Integer id = ShiroKit.getUser().getId();
        User user = userMapper.selectById(id);
        String avatar = "";
        if(null!=user.getAvatar()){
        	avatar = user.getAvatar();
        }
        model.addAttribute("avatar", avatar);
//        model.addAttribute("avatar", "");

        return "/index.html";
    }

    /**
     * 跳转到登录页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
    	
    	
        if (ShiroKit.isAuthenticated() || ShiroKit.getUser() != null) {
            return REDIRECT + "/";
        } else {
             return "/login.html";
        }
    }

    /**
     * 点击登录执行的动作
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginVali() {

        String username = super.getPara("username").trim();
        String password = super.getPara("password").trim();

        //验证验证码是否正确
        if(ToolUtil.getKaptchaOnOff()){
            String kaptcha = super.getPara("kaptcha").trim();
            String code = (String) super.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            if(ToolUtil.isEmpty(kaptcha) || !kaptcha.equals(code)){
                throw new InvalidKaptchaException();
            }
        }

        Subject currentUser = ShiroKit.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());
        token.setRememberMe(true);

        currentUser.login(token);

        
        ShiroUser shiroUser = ShiroKit.getUser();
        super.getSession().setAttribute("shiroUser", shiroUser);
        super.getSession().setAttribute("username", shiroUser.getAccount());

        LogServiceManager.me().executeLog(LogServiceTaskFactory.loginLog(shiroUser.getName(),"", getIp()));

        ShiroKit.getSession().setAttribute("sessionFlag",true);

        return REDIRECT + "/";
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut() {
       ShiroKit.getSubject().logout();
        return REDIRECT + "/login";
    }
}
