package com.tt.study;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @description: 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 * @author: HyJan
 * @create: 2020-08-05 18:21
 **/
public class HalfPassArraySize {

    /**
     * 这个太简单了，使用map记录每个数字出现的次数，然后超过一半了就返回
     * @param array
     * @return
     */
    public static int MoreThanHalfNum_Solution(int [] array) {
        // 判断输入
        if (Objects.isNull(array) || array.length < 0){
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>(array.length);
        int half = array.length / 2;
        for (int i = 0; i < array.length; i++) {
            Integer integer = map.get(array[i]);
            if (Objects.isNull(integer)){
                // 考虑数组只有一个值的情况
                if (1 > half){
                    return array[i];
                }
                map.put(array[i],1);
            }else {
                if (integer + 1 > half){
                    return array[i];
                }
                map.put(array[i],integer + 1);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,2,2,2,5,4,2};
        int i = MoreThanHalfNum_Solution(array);
        System.out.println(i);
    }
}
