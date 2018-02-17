package com.yangchun.tokenandfiletry.Controller;

import com.yangchun.tokenandfiletry.Entity.User;
import com.yangchun.tokenandfiletry.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tianyi
 * @date 2018-02-12 17:49
 */
@Controller
@RequestMapping(value = "/jump")
@Slf4j
public class JumpController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String toHome(){
        log.info("【跳转中】..");
        return "home";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String toLogin(@RequestParam("userName")String userName,@RequestParam("passWord")String passWord){
        log.info("into toLogin()");
        User user=userRepository.findByUserName(userName);
        if (user==null){
            log.info("用户不存在！");
            return "error/404";
        }else {
            if (!passWord.equals(user.getPassWord())){
                log.info("密码错误！");
                return "error/444";
            }else {
                String appendUrl = "?userId=" + user.getId();
                log.info("ready to jump:http://127.0.0.1:8080/seller/login" + appendUrl);
                return "redirect:http://127.0.0.1:8080/seller/login" + appendUrl;
                // TODO !!!!欠加密  链接直接暴露，极其不安全
            }
        }
    }

    @RequestMapping(value = "/Centre",method = RequestMethod.GET)
    public String toCentre(){
        return "Centre";
    }

    //springboot项目好像不支持POST方法
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public User toCentre(@RequestParam(value = "userName")String userName,
                           @RequestParam(value = "passWord")String passWord){
        System.out.println("registering");

        User user=new User();
        user.setUserName(userName);
        user.setPassWord(passWord);

        return userRepository.save(user);
    }
}
