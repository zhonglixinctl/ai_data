package org.jeecg.modules.zhy_ai.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AIUtil {

    /**
     * 判断字符串中是否含有数字
     * @param str
     */
    public static boolean hasNum(String str){
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 取出字符串中的数值
     * @param str
     * @return
     */
    public static String takeNum(String str){
        int num = -1;//定义一个int值，用来表示是否包含数字
//        String str = "12sdfsdf";
        StringBuffer msg = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {  //用char包装类中的判断数字的方法判断每一个字符
                String numStr = String.valueOf(str.charAt(i));
                num = Integer.parseInt(numStr); //如果有数字，那么拿出来，肯定不是－1
                msg.append(num);
//                break;
            }
        }
        System.out.println(num);
        System.out.println(msg);
        return msg.toString();
    }
}
