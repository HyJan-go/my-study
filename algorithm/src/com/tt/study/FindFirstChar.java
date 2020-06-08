package com.tt.study;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: algorithm
 * @description: 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置,
 *     如果没有则返回 -1（需要区分大小写）
 *
 *     实现方法：使用hash的key，value属性，第一次遍历记录每一个字符出现的次数，第二次遍历找到第一出现一个的字符
 * @author: HyJan
 * @create: 2020-04-23 18:49
 **/
public class FindFirstChar {

    public static void main(String[] args) {
        int i = FirstNotRepeatingChar("fdsaf");
        System.out.println(i);
    }

    /**
     * 算法核心类
     * @param str
     * @return
     */
    public static int FirstNotRepeatingChar(String str) {

        // 判断输入
        if ("".equals(str)){
            return -1;
        }

        int defaultValue = -1;

        Map<String,Integer> record = new HashMap<>(str.length());

        //第一次遍历并做记录
        for (int i = 0; i < str.length(); i++) {
            String strIndex = String.valueOf(str.charAt(i));
            if (record.containsKey(strIndex)){
                record.put(strIndex,record.get(strIndex) + 1);
            }else {
                record.put(strIndex,1);
            }
        }

        //第二次遍历，顺序找出记录值为1的
        for (int j = 0; j < str.length(); j++) {
            String strIndex = String.valueOf(str.charAt(j));
            if (record.get(strIndex) == 1){
                defaultValue = j;
                break;
            }
        }
        return defaultValue;
    }
}
