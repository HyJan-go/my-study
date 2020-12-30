package com.nowcoder.study;

import java.util.*;

/**
 * @description: 题目描述
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 * 输出描述:
 * 如果当前字符流没有存在出现一次的字符，返回#字符。
 * @author: HyJan
 * @create: 2020-08-19 17:32
 **/
public class FindFirstCharAndOneTime {

    /**
     * 利用LinkedHashMap，可以保持输出顺序和输入顺序（也可以是自定义顺序）一致，记录字符或者数字及其出现的次数，在O(1)内找到出现1测的目标。
     * 这里同样可以借鉴之前的思路，使用LinkedHashMap保存字符及其出现的次数，使用ArrayList保存字符流。
     * 既保证了顺序，又能知道每个字符出现的次数
     */
    // 对每一个字符个数进行计算存储
    private Map<Character,Integer> map = new LinkedHashMap<>();

    // 存储字符流
    private List<Character> list = new ArrayList<>();

    public void Insert(char ch)
    {
        if (Objects.isNull(map.get(ch))){
            map.put(ch,1);
        }else {
            map.put(ch,map.get(ch) + 1);
        }
        list.add(ch);
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        char c = '#';
        for (Character key: list
             ) {
            if (map.get(key) == 1){
                c = key;
                break;
            }
        }
        return c;
    }
}
