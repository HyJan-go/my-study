package com.tt.study;

/**
 * @program: algorithm
 * @description: 字符串翻转，要注意它并不是简单的翻转，而是单词还是保证每个都是正常的顺序
 * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，
 * 有一天他向Fish借来翻看，但却读不懂它的意思。例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，
 * 正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 * @author: HyJan
 * @create: 2020-07-20 14:45
 **/
public class ReverseWords {

    /**
     * 字符串的替换是替换某个序列的或者是某个字符的，具体可以查看源码
     * 而动态字符串则可以实现，注意题目是字符串中单词顺序的翻转，单词则还是正确的
     * 这种方法的时间复杂度为O(n),可以选择入栈或者是倒序读取字符串的方式
     * @param str
     * @return
     */
    public static String ReverseSentence(String str) {
        if ("".equals(str) || str.length() < 1 || "".equals(str.trim())){
            return str;
        }
        //
        String[] s = str.split(" ");
        //使用可变字符串的方式来进行翻转,指定并初始化
        StringBuilder sb = new StringBuilder();
        // 定义首尾指针
        int j = s.length - 1;
        while (j >= 0){
            if (j == 0){
                sb.append(s[j]);
            }else {
                sb.append(s[j] + " ");
            }
            j --;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(ReverseSentence(""));
    }
}
