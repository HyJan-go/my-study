package com.leetcode;

import java.util.Objects;

/**
 * @program: algorithm
 * @description: 隐藏个人信息
 * 给你一条个人信息字符串 S，它可能是一个 邮箱地址 ，也可能是一串 电话号码 。
 * <p>
 * 我们将隐藏它的隐私信息，通过如下规则:
 * <p>
 *  
 * <p>
 * 1. 电子邮箱
 * <p>
 * 定义名称 name 是长度大于等于 2 （length ≥ 2），并且只包含小写字母 a-z 和大写字母 A-Z 的字符串。
 * <p>
 * 电子邮箱地址由名称 name 开头，紧接着是符号 '@'，后面接着一个名称 name，再接着一个点号 '.'，然后是一个名称 name。
 * <p>
 * 电子邮箱地址确定为有效的，并且格式是 "name1@name2.name3"。
 * <p>
 * 为了隐藏电子邮箱，所有的名称 name 必须被转换成小写的，并且第一个名称 name 的第一个字母和最后一个字母的中间的所有字母由 5 个 '*' 代替。
 * <p>
 *  
 * <p>
 * 2. 电话号码
 * <p>
 * 电话号码是一串包括数字 0-9，以及 {'+', '-', '(', ')', ' '} 这几个字符的字符串。你可以假设电话号码包含 10 到 13 个数字。
 * <p>
 * 电话号码的最后 10 个数字组成本地号码，在这之前的数字组成国际号码。注意，国际号码是可选的。我们只暴露最后 4 个数字并隐藏所有其他数字。
 * <p>
 * 本地号码是有格式的，并且如 "***-***-1111" 这样显示，这里的 1 表示暴露的数字。
 * <p>
 * 为了隐藏有国际号码的电话号码，像 "+111 111 111 1111"，我们以 "+***-***-***-1111" 的格式来显示。在本地号码前面的 '+' 号和第一个 '-' 号仅当电话号码中包含国际号码时存在。例如，一个 12 位的电话号码应当以 "+**-" 开头进行显示。
 * <p>
 * 注意：像 "("，")"，" " 这样的不相干的字符以及不符合上述格式的额外的减号或者加号都应当被删除。
 * <p>
 *  
 * <p>
 * 最后，将提供的信息正确隐藏后返回。
 * <p>
 * 解题思路： 方法一：模拟
 * 我们首先判断 S 是邮箱还是电话号码。显然，如果 S 中有字符 '@'，那么它是邮箱，否则它是电话号码。
 * <p>
 * 如果 S 是邮箱，我们将 S 的 '@' 之前的部分保留第一个和最后一个字符，中间用 '*****' 代替，并将整个字符串转换为小写。
 * <p>
 * 如果 S 是电话号码，我们只保留 S 中的所有数字。首先将最后 10 位本地号码变成 '***-***-abcd' 的形式，再判断 S 中是否有额外的国际号码。如果有，则将国际号码之前添加 '+' 号并加到本地号码的最前端。
 * <p>
 * 其实国际号码的*号表现在电话号码的超出10个本地号码的部分，然后前后加上 ‘+’ ‘-’
 * @author: HyJan
 * @create: 2021-01-12 10:38
 **/
public class HidePersionInfo {

    /**
     * 隐藏个人信息专用方法
     *
     * @param S
     * @return
     */
    public static String maskPII(String S) {
        // 判断当前字符串是邮箱还是电话号码
        int i = S.indexOf('@');
        // 如果i的值不为-1，说明包含@，为邮箱
        if (i >= 0) {
            return new StringBuilder().append(S, 0, 1).append("*****").append(S.substring(i-1)).toString().toLowerCase();
//            return (S.substring(0, 1) + "*****" + S.substring(i - 1)).toLowerCase();
        } else {
            // 如果为电话号码，先把所有的数字提取出来
            String numbers = S.replaceAll("\\D+", "");
            // 电话号码只显示末尾四位
            String phone = new StringBuilder().append("***-***-").append(numbers,numbers.length() - 4,numbers.length()).toString();
//            String phone = "***-***-" + numbers.substring(numbers.length() - 4);
            // 判断是否为本地电话号码
            if (Objects.equals(numbers.length(), 10)) {
                return phone;
            }
            // 如果是国际号码
            StringBuilder builder = new StringBuilder("+");
//            String builder = "+";
            for (int j = 0; j < numbers.length() - 10; j++) {
                builder.append("*");
//                builder += "*";
            }
            return builder.append("-").append(phone).toString();
//            return builder + "-" + phone;
        }
    }


    public static void main(String[] args) {
        String s = "1(234)567-890";
//        s = "AB@qq.com";
//        s = "86-(10)12345678";
        // replaceAll 方法支持传入 regex （支持正则匹配替换）\d 数字  \D 非数字 这里是把非数字替换掉
//        String s1 = s.replaceAll("\\D+", "");
//        System.out.println(s.indexOf('@'));
        System.out.println(maskPII(s));
    }

}
