package com.logindemo.util;

import javax.servlet.http.Cookie;

public class CookieUtil {
    public static Cookie findCookieByName(Cookie [] cookies,String name){
        if (cookies !=null&& (!"".equals(name))){
            for (Cookie cookie:cookies){
                if (name.equals(cookie.getName())){
                    return cookie;
                }
            }
        }
        return null;
    }
}
