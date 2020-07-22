package com.tt.study;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @program: algorithm
 * @description: 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 * @author: HyJan
 * @create: 2020-07-22 19:03
 **/
public class NoRepeateTwoNumber {

    /**
     * num1,num2分别为长度为1的数组。传出参数
     * 将num1[0],num2[0]设置为返回结果
     * 这里采用haspMap的方式进行解决。key:数组元素 value:出现的次数
     * @param array
     * @param num1
     * @param num2
     */
    public static void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        // 判断输入
        if (Objects.isNull(array) || array.length < 1){
            return;
        }

        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (Objects.isNull(map.get(array[i]))){
                map.put(array[i], 1);
            }else {
                map.put(array[i],map.get(array[i]) + 1);
            }
        }
        // 做一个标记
        boolean isFirst = true;
        // 遍历一遍map,取value为1的key还要赋值给到num数组
        for (int i = 0; i < array.length; i++) {
            // 注意这里的判断不能够是if-else
            if (map.get(array[i]) == 1 && isFirst){
                num1[0] = array[i];
                isFirst = false;
            }
            if (map.get(array[i]) == 1 && !isFirst){
                num2[0] = array[i];
            }
        }
    }

}
