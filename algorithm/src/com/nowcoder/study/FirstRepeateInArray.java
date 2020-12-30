package com.nowcoder.study;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @program: algorithm
 * @description: 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，
 * 但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 * @author: HyJan
 * @create: 2020-07-23 14:20
 **/
public class FirstRepeateInArray {

    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
    public static boolean duplicate(int numbers[],int length,int [] duplication) {
        List<Integer> list = new ArrayList<>(length);
        // 低于两个字都不会有重复
        if (Objects.isNull(numbers) || numbers.length < 2){
            return false;
        }
        // 做个小标记，看看是否找到重复数字
        boolean isRepeate = false;
        for (int i = 0; i < length; i++) {
            if (list.contains(numbers[i])){
                duplication[0] = numbers[i];
                isRepeate = true;
                break;
            }else {
                list.add(numbers[i]);
            }
        }
        return isRepeate;
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{1,2};
        int[] result = new int[1];
        boolean duplicate = duplicate(numbers, numbers.length, result);
        System.out.println(duplicate);
        if (duplicate){
            System.out.println(result[0]);
        }
    }
}
