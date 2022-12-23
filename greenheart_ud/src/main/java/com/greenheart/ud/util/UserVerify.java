package com.greenheart.ud.util;

import javax.servlet.http.HttpSession;

//获取session中用户Id
public class UserVerify {
    public static  Integer verify( HttpSession session){
        return (Integer)session.getAttribute("userId");
    }
}