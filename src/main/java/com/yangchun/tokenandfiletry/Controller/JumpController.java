package com.yangchun.tokenandfiletry.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author tianyi
 * @date 2018-02-12 17:49
 */
@Controller
public class JumpController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String toHome(){
        return "home";
    }

    //springboot项目好像不支持POST方法
    @RequestMapping(value = "/centre",method = {RequestMethod.POST,RequestMethod.GET})
    public String toCentre(){
        System.out.println("toCentre");
        return "home";
    }
}
