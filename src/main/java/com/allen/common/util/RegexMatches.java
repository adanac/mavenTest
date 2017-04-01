package com.allen.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by allen on 2017/3/22.
 */
public class RegexMatches {

    public static void main(String args[]) {
        String str = "-123";
        String pattern = "[1-9]\\d*";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());
    }

}
