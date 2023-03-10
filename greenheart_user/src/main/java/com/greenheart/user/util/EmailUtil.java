package com.greenheart.user.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import entity.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class EmailUtil {
    @Resource
    private RedisUtil redisUtil;

    private static final String symbols="01234564789ADBCGPOLKJ";
    private static final Random random=new SecureRandom();

    private static MailAccount account=new MailAccount();
    static {
        account.setHost("smtp.qq.com");
        account.setPort(25);
        account.setAuth(true);
        account.setFrom("1628311741@qq.com");
        account.setUser("1628311741@qq.com");
        account.setPass("ebxwenqazlacdecc");
        //POP3/SMTP服务bbnjyeqcjrxwfcha
        //IMAP/SMTP服务ehwwfavaunhpbcec
    }

    //发送验证码
    public String registerCode(String to){
        //account.setHost(to);
        if (!Validator.isEmail(to)){
            return  String.valueOf(StatusCode.ERROR);
        }
        String code=getCode();
        log.info("[email code]:{}",code);
        System.out.println(to);
        String ans= MailUtil.send(account, CollUtil.newArrayList(to), "验证码:", code, false);
        redisUtil.setValue(to,code,120, TimeUnit.MINUTES);
        return code;
    }

    //发送找回的密码
    public String registerPwd(String to,String userPwd){
        //account.setHost(to);
        if (!Validator.isEmail(to)){
            return  String.valueOf(StatusCode.ERROR);
        }
        log.info("[email code]:{}",userPwd);
        System.out.println(to);
        String ans= MailUtil.send(account, CollUtil.newArrayList(to), "密码:", userPwd, false);
        return "邮件发送成功";
    }

    //校对验证码
    public String checkCode(String to,String code){
        String value=redisUtil.getValue(to);
        if(ObjectUtil.isEmpty(value)){
            return String.valueOf(StatusCode.ERROR);
        } else {
            if(code.equals(value)){
                return "验证成功";
            }else {
                return "验证失败";
            }
        }
    }

    //生成6位验证码
    public String getCode(){
        char[] codeTemp=new char[6];
        for(int i=0;i<6;i++){
            codeTemp[i]=symbols.charAt(random.nextInt(symbols.length()));
        }
        return new String(codeTemp);
    }

}
