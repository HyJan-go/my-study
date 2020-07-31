package com.tt.study;

import java.util.Objects;

/**
 * @description: HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:在古老的一维模式识别中,
 * 常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。但是,如果向量中包含负数,是否应该包含某个负数,
 * 并期望旁边的正数会弥补它呢？例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。给一个数组，
 * 返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
 * @author: HyJan
 * @create: 2020-07-31 18:09
 **/
public class MaxListSum {

    /**
     * 求最大连续序列的值
     * 每个数字都求它所在位置开始到最后序列的最大值，找到更好的则进行交换
     * 如果首在的值为负数，则不用开始了，因为绝对不会存在开始就负债的连续最大值
     * @param array
     * @return
     */
    public static int FindGreatestSumOfSubArray(int[] array) {
        // 判断输入
        if (Objects.isNull(array) || array.length < 1){
            return Integer.MIN_VALUE;
        }
        int max = array[0];
        for (int i = 0 ; i < array.length; i++){
            int sum = array[i];
             if (i != array.length - 1 && array[i] > 0){
                 for (int j = i + 1;j < array.length; j ++){
                     sum += array[j];
                     max = sum > max ? sum : max;
                 }
             }
             // 考虑到负数都有可能是可以大于max的特殊情况
             max = sum > max ? sum : max;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = new int[]{6,-3,-2,7,-15,1,2,2};
        System.out.println(FindGreatestSumOfSubArray(array));
    }
}
