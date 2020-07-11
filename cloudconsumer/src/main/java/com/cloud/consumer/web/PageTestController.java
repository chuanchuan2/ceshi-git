package com.cloud.consumer.web;

import com.cloud.consumer.pojo.PublicKeyMap;
import com.cloud.consumer.pojo.Subject;
import com.cloud.consumer.service.SubjectService;
import com.cloud.consumer.service.UsersService;
import com.cloud.consumer.utils.PropertiesUtils;
import com.cloud.consumer.utils.RSAUtil;
import com.cloud.consumer.utils.ResultUtils;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.PrivateKey;
import java.util.List;

@Controller
@RequestMapping("test")
//访问static下面页面测试类
//1.引入依赖 spring-boot-starter-thymeleaf
//2.thymeleaf: prefix: classpath:/static/
//注意访问页面注解要用@Controller
public class PageTestController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UsersService usersService;

    @RequestMapping("pageindex")
        public String testPage2(HttpServletRequest request,HttpServletResponse response) throws Exception {
        //response.sendRedirect(request.getContextPath()+"/index.html");
        return "uploadtodata";
    }

    //访问页面测试
    @RequestMapping("page")
    public String testPage(String id) {
        return "index";
    }

    //测试mybatis的pageHelper分页插件
    @RequestMapping(value = "pageHelper",method = RequestMethod.GET)
    @ResponseBody
    public List<Subject> testPageHelper(String page, String rows) {
        //返回当前页信息
        return subjectService.subjectList(page,rows);
    }

    //提前获取公钥传到前台
    @RequestMapping(value = "/loginKeyPair",method = RequestMethod.GET)
    public void loginKeyPair(HttpServletRequest request, HttpServletResponse response) {
       //获取ServletContext域中的值
        PublicKeyMap publicKeyMap = (PublicKeyMap) request.getServletContext().getAttribute("publicKeyMap");
        ResultUtils.sendObject(response,publicKeyMap);
    }

    @RequestMapping(value = "/loginuser",method = RequestMethod.POST)
    @ResponseBody
    public String loginUser(String username,String password2,HttpServletRequest request,HttpServletResponse response) throws Exception {
        System.out.println(username);
        System.out.println(password2);
        //因为加密采用的是十六进制，这里将使用Hex类将十六进制字符串转字节数字（byte[]）
        byte[] en_password = Hex.decodeHex(password2.toCharArray());
        //获取私钥
        PrivateKey privateKey = (PrivateKey) request.getServletContext().getAttribute("privatKey");
        //开始解密操作
        byte[] de_password=null;
        try {
            de_password = RSAUtil.decrypt(privateKey, en_password);
        }catch (Exception e){
            System.out.println("解密失败！");
        }
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(new String(de_password));
        String pssword = stringBuffer.reverse().toString();
        //读取配置文件中的strength的值，使用BC进行加密strength为10增加破解时间
        String strength = PropertiesUtils.getValue("bcryptFile.properties", "strength");
        BCryptPasswordEncoder bC=new BCryptPasswordEncoder(Integer.valueOf(strength));
        String encode = bC.encode(pssword);
        System.out.println(pssword);
        System.out.println("BC加密："+encode);
        return usersService.addUser(username,encode);

        //response.sendRedirect(request.getContextPath()+"/login.html");
    }

    //成功页面
    @RequestMapping("/loginsuccess")
    public String loginSuccess() {
        return "login";
    }

    //登录操作
    @RequestMapping(value = "/encodelogin",method ={RequestMethod.GET,RequestMethod.POST})
    //@ResponseBody
    public void loginsuccess(String usernameadd,String passwordadd2,HttpServletRequest request,HttpServletResponse response) throws Exception {
        System.out.println(usernameadd);
        System.out.println(passwordadd2);
        //因为加密采用的是十六进制，这里将使用Hex类将十六进制字符串转字节数字（byte[]）
        byte[] en_password = Hex.decodeHex(passwordadd2.toCharArray());
        //获取私钥
        PrivateKey privateKey = (PrivateKey) request.getServletContext().getAttribute("privatKey");
        //开始解密操作
        byte[] de_password=null;
        try {
            de_password = RSAUtil.decrypt(privateKey, en_password);
        }catch (Exception e){
            System.out.println("解密失败！");
        }
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(new String(de_password));
        String pssword = stringBuffer.reverse().toString();
        request.setAttribute("password",pssword);
        request.getRequestDispatcher("/security/securitysuccess").forward(request,response);
    }

    //登录成功操作
    @RequestMapping(value = "/loginAfter",method = RequestMethod.GET)
    @ResponseBody
    public void loginSuccessAfter(HttpServletRequest request,HttpServletResponse response) throws Exception {
        System.out.println("登录成功了。。。。。");
    }

    //登录成功操作
    @RequestMapping(value = "/loginFailure",method = RequestMethod.GET)
    @ResponseBody
    public void loginFailure(HttpServletRequest request,HttpServletResponse response) throws Exception {

        System.out.println("登录失败了。。。。。");
    }
}
