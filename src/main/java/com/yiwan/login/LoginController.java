package com.yiwan.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//该注解表示可以对外提供api接口 @Controllerb则用于MVC对外提供模板引擎
@RestController
public class LoginController {
//    常用的两种提交方式
//    @PathVariable：获取路径参数。即url/{id}这种形式。
//    @RequestParam：获取查询参数。即url?name=这种形式 url获取参数(适合get请求)
//    @RequestBody ：获取查询参数。从请求体获取参数(适合post请求)

    //依赖注入 不需要new
    @Autowired
    UserDao userdao;

    //登录验证第一步
    //@RequestMapping(value = "/providers",method = RequestMethod.GET)
    @PostMapping("/user/login")
    public Map login(@RequestParam("username") String username, @RequestParam("password") String password){
        System.out.println(username+"&"+password);

        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,String> map2 = new HashMap<>();

        //判断用户名和密码不为空
        if(!StringUtils.isEmpty(username)&&!StringUtils.isEmpty(password)){
            UserList findusername = userdao.findusername(username,password);
            System.out.println(findusername);
            if (findusername==null){
                map.put("code",200);
                map.put("flag",false);
                map.put("message","账号或密码错误");
                map2.put("token","0");
                map.put("data",map2);
            }else {
                map.put("code",200);
                map.put("flag",true);
                map.put("message","验证成功");
                map2.put("token",String.valueOf(findusername.getId()));
                map.put("data",map2);
            }
            return map;
        }else{

            return map;
        }
    }

    //登录验证第二步
    @GetMapping("/user/info/{token}")
    public Map login2(@PathVariable("token") String token){
        System.out.println("/user/info/"+token);
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,String> map2 = new HashMap<>();
        if(!StringUtils.isEmpty(token)){
            UserList one = userdao.getOne(Integer.parseInt(token));
            map.put("code",200);
            map.put("flag",true);
            map.put("message","成功获取用户信息");
            map2.put("id",String.valueOf(one.getId()));
            map2.put("name",one.getUsername());
            map2.put("time",String.valueOf(new Date().getTime()));//时间戳，验证超时登录
            map.put("data",map2);
            return map;
        }else{
            map.put("code",200);
            map.put("flag",false);
            map.put("message","获取用户信息失败");
            map.put("data",null);
            return map;
        }
    }

}
