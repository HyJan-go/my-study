package com.tt.study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * @description: 题目描述
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则按字典序打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 * 输入描述:
 * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
 * @author: HyJan
 * @create: 2020-08-11 15:48
 **/
public class NumberOfString {

    /**
     * 字符串内部交换问题，可以转化为char数组代为执行
     * 思路：定住一个，然后只要求出它后面有多少种排列即可。递归
     * 然后首个字符，一定是每一个每个字符都得当一下。for一下
     * @param str
     * @return
     */
    public static ArrayList<String> Permutation(String str) {
        ArrayList<String> list = new ArrayList<>();
        // 判断输入
        if (Objects.equals("",str.trim()) || str.length() < 1){
            return list;
        }
        char[] chars = str.toCharArray();
        fibCalculateNumber(list,chars,0);
        // 升序排序一下(默认)
        Collections.sort(list);
        return list;
    }

    /**
     * 循环递归的组合每一种可能
     * @param list
     * @param str
     * @param index
     */
    public static void fibCalculateNumber(ArrayList<String> list,char[] str,int index){
        // 所有递归先判结束条件
        if (index == str.length - 1){
            // 正确将char数组转化为string
            if (Objects.isNull(list)){
                list.add(String.valueOf(str));
            }else {
                // 手动去重
                if (!list.contains(String.valueOf(str))){
                    list.add(String.valueOf(str));
                }
            }
            return;
        }
        for (int i = index; i < str.length; i++) {
            // 跳过重复的字符，防止重复计算
            if (i != index && str[i] == str[index]) {
                continue;
            }
            // 字段在字符串中交换
            char temp = str[index];
            str[index] = str[i];
            str[i] = temp;
            // 递归
            fibCalculateNumber(list,str,index + 1);
            // 一定要恢复原来的排列顺序，不然最后出现的首个字符都会不齐全，因为相当于字符串一直都是在变的
            temp = str[index];
            str[index] = str[i];
            str[i] = temp;
        }
    }

    /**
     * 这个是一个正确的方法，上面的方法返回的结果顺序不对，所以没有在牛客通过，而这个是通过了的。
     * 字符串内部交换问题，可以转化为char数组代为执行
     * 思路：定住一个，然后只要求出它后面有多少种排列即可。递归
     * 然后首个字符，一定是每一个每个字符都得当一下。for一下
     * @param str
     * @return
     */
    public ArrayList<String> PermutationOK(String str) {
        ArrayList<String> list = new ArrayList<String>();
        char[] chars = str.toCharArray();
        permu(chars,0,list);   //递归进行排列
        Collections.sort(list);  //进行升序，就是字典序进行排序
        return list;
    }

    /**
     * 使用递归方式进行
     * @param chars 字符串数组
     * @param i  比较到的位置
     * @param list 存放序列的集合
     */
    public static void permu(char[] chars,int i,ArrayList<String> list){
        if (chars == null){
            return;
        }
        if (i == chars.length - 1){
            if (list.contains(String.valueOf(chars))){  //避免出现重复的排序
                return;
            }
            //如果没有重复
            list.add(String.valueOf(chars));
        }else {
            for (int j = i; j < chars.length; j++) {
                char temp = chars[j];   //交换
                chars[j] = chars[i];
                chars[i] = temp;

                permu(chars,i + 1,list);  //递归

                temp = chars[j];
                chars[j] = chars[i];
                chars[i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Permutation("abcp"));
    }
}
