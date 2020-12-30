package com.nowcoder.study;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @program: algorithm
 * @description: 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
 * 输出描述:
 * 对应每个测试案例，输出两个数，小的先输出。
 * @author: HyJan
 * @create: 2020-07-20 17:35
 **/
public class FindTwoNumbers {

    /**
     * 根据题目特点，就是排好序的数组，还有就是小的先输出，还要两个数的乘积最小（意思是找到就可以了，不用继续找了）
     * 从左边开始算计，有点进行二分查找，直到找到第一符合要求的就截止
     *
     * @param array
     * @param sum
     * @return
     */
    public static ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> list = new ArrayList<>();
        // 判断输入
        if (Objects.isNull(array) || array.length < 1) {
            return list;
        }

        for (int i = 0; i < array.length; i++) {
            // 如果相邻的两个数之和都大于sum了，则往后都找不到合适的值进行替换了
            if (array[i] + array[i + 1] > sum) {
                break;
            }
            find(array, sum, i, i + 1, array.length - 1, list);
            if (list.size() > 0) {
                break;
            }
        }

        return list;
    }

    /**
     * 构建一个递归函数，然后递归查找合适的两个值
     *
     * @param array 查询数据
     * @param sum   两个数字的和
     * @param i     最左的定位
     * @param left  二分查找左边的索引值
     * @param right 二分查找右边的索引值
     * @param list  返回的结果保存list
     */
    public static void find(int[] array, int sum, int i, int left, int right, ArrayList<Integer> list) {
        // 先考虑退出条件
        if (list.size() > 0 || left > right || left < 0 || right > array.length) {
            return;
        }

        int middle = left + (right - left) / 2;
        if (sum == array[i] + array[middle]) {
            list.add(array[i]);
            list.add(array[middle]);
            return;
        }
        // 要注意的就是中间的条件和结束的条件，因为当两者就是left=right的时候还是要判断的
        if (sum > array[i] + array[middle]) {
            find(array, sum, i, middle + 1, right, list);
        }
        if (sum < array[i] + array[middle]) {
            find(array, sum, i, left, middle - 1, list);
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 5, 6, 7, 8, 9};
        ArrayList<Integer> list = FindNumbersWithSum(array, 6);
        System.out.println(list);
    }

}
