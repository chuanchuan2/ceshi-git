package com.cloud.consumer.config;

import com.cloud.consumer.pojo.PublicKeyMap;
import com.cloud.consumer.utils.RSAUtil;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.security.Key;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class WebConfig {
    @Bean
    public ServletContextInitializer initializer(){
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                PublicKeyMap publicKeyMap=new PublicKeyMap();
                //获取密匙对
                KeyPair keyPair;
                try {
                    keyPair=RSAUtil.generateKeyPair();
                    /** 1.得到公钥 */
                    RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
                    /** 2.得到私钥 */
                    Key privateKey = keyPair.getPrivate();
                    System.out.println("初始化公钥为："+publicKey);
                    System.out.println("初始化私钥为："+privateKey);
                    /** 3.公钥模量 */
                    /*问题一：在login.jsp中，公钥的Exponent,和modulus输出格式问题开始总是什么：长度过大，
                    必须以0开始之类的异常。我想到很可能是js加密和纯java加密那些地方不同导致的。后来发现，
                    原来是我公钥的Exponent,和modulus输出直接用的toString()方法，实际上应该用toString(16)，
                    用16进制输出，因为在security.js中，那个RSAUtils.getKeyPair(publicKeyExponent, "",
                    ${publicKeyModulus);方法内部，明显是从16进制进行转换的*/
                    String module = publicKey.getModulus().toString(16);
                    /** 4.公钥指数 */
                    String empoent = publicKey.getPublicExponent().toString(16);
                    //将以上产生的 私钥放置
                    servletContext.setAttribute("privatKey",privateKey);
                    //将公钥模量，公钥指数封装到publicKeyMap中
                    publicKeyMap.setModulus(module);
                    publicKeyMap.setExponent(empoent);
                    servletContext.setAttribute("publicKeyMap",publicKeyMap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
