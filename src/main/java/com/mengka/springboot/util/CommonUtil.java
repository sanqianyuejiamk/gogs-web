package com.mengka.springboot.util;

import lombok.extern.log4j.Log4j2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
public class CommonUtil {

    public static String getRepositoryByUri(String uri) {
        Pattern pattern = Pattern.compile(".*/(.*).git");
        Matcher matcher = pattern.matcher(uri);
        if (matcher.matches()) {
            String day = matcher.group(1);

            //log.info("day = " + day);
            return day;
        }
        return null;
    }
}
