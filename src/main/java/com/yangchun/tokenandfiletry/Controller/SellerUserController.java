package com.yangchun.tokenandfiletry.Controller;

import com.yangchun.tokenandfiletry.config.ProjectUrlConfig;
import com.yangchun.tokenandfiletry.constant.CookieConstant;
import com.yangchun.tokenandfiletry.constant.RedisConstant;
/*import com.imooc.dataobject.SellerInfo;*/
import com.yangchun.tokenandfiletry.enums.ResultEnum;
import com.yangchun.tokenandfiletry.repository.UserRepository;
import com.yangchun.tokenandfiletry.service.SellerService;
import com.yangchun.tokenandfiletry.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 卖家用户
 * Created by 廖师兄
 * 2017-07-30 15:28
 */
@Controller
@RequestMapping("/seller")
@Slf4j
public class SellerUserController {

    @Autowired
    private UserRepository userRepository;

    /*@Autowired
    private SellerService sellerService;*/

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("userId") Integer userId,
                              HttpServletResponse response,
                              Map<String, Object> map) {
        log.info("into login()");
        //1. openid去和数据库里的数据匹配 TODO
        if (userRepository.findOne(userId)==null){
            log.info("【用户不存在】");
        }

        //2. 设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;

        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), String.valueOf(userId), expire, TimeUnit.SECONDS);

        //3. 设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

        return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/jump/Centre");

    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String, Object> map) {
        //1. 从cookie里查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //2. 清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

            //3. 清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }

        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }
}
