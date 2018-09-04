package com.yu.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created By Yu On 2018/9/4
 * Description：正则匹配验证工具类
 **/
public class IsXXLegal {

    public IsXXLegal() {
    }

    /**
     * 验证手机号
     *
     * @param str 手机号
     * @return 手机号正确, true, 手机号不正确 false
     */
    public static boolean isChinaPhoneLegal(String str) {
        String regExp = "^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[6-8])|(18[0-9]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
