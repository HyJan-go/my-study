package com.tt.study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * @description: 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
 * @author: HyJan
 * @create: 2020-08-05 17:40
 **/
public class MinNNumber {

    /**
     * 求最小的n个数，就应该使用一个限定长度的k的最大堆来存放
     * 如果比最大堆的root小，由于限定个数，只要每次跟root比，小的话把root踢了，然后重新调整成大顶堆，继续递归
     *
     * 如果堆需要自己实现，还不如先拍序，再拿前面四个
     * @param input
     * @param k
     * @return
     */
    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        // 判断输入
        if (Objects.isNull(input) || input.length < 0 || input.length < k){
            return list;
        }
        // 使用jdk的排序手段
        Arrays.sort(input);
        // 去除最小的四个
        for (int i = 0; i < k; i++) {
            list.add(input[i]);
        }
        return list;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4,5,1,6,2,7,3,8};
        ArrayList<Integer> list = GetLeastNumbers_Solution(array, 4);
        System.out.println(list.toString());
    }
}
