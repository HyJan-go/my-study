package com.nowcoder.study;

import java.util.Objects;

/**
 * @program: algorithm
 * @description: 统计一个数字在排序数组中出现的次数。
 *  这里要特别的注意，这个数组是一个排序的数组
 * @author: HyJan
 * @create: 2020-06-30 18:06
 **/
public class TimesInArray {

    /**
     * 两路开算，时间复杂度O(n)
     * 思路： 左右数组的最左边和最有边算，这个方法是自己想的，但是还是有问题，就是题目是排序数组
     * @param array
     * @param k
     * @return
     */
    public static int GetNumberOfK(int[] array , int k) {
        if (Objects.isNull(array)){
            return 0;
        }
        int i = 0,j = array.length - 1;
        int leftSum =0, rightSum = 0;
        while (i < j){
            if (array[i++] == k) {
                leftSum ++;
            }
            if (array[j--] == k){
                rightSum ++;
            }
        }
        if (i == j){
            if (array[i] == k){
                leftSum ++;
            }
        }
        return leftSum + rightSum;
    }

    public static void main(String[] args) {
        int[] array = {3,3,3,3};
        int i = GetNumberOfKey(array, 3);
        System.out.println(i);
    }

    /**
     * 由于数组已经是有序的了，所以最好的办法就是二分查找
     * 思路：将个数转化为找位置，找到了最后位置和开始的位置，然后再计算个数，避免整个数组都是它的特殊情况
     * @param array
     * @param k
     * @return
     */
    public static int GetNumberOfKey(int[] array,int k){
        if (array == null || array.length < 1){
            return 0;
        }
        int num = 0;
        int firstIndex = getFirstIndex(array, k, 0, array.length - 1);
        int lastIndex = getLastIndex(array, k, 0, array.length - 1);
        if (firstIndex > -1 && lastIndex > -1){
            num = lastIndex - firstIndex + 1;
        }
        return num;
    }

    /**
     * 从执行范围中，找到一个key的值的位置
     * @param array 统计数组
     * @param k 匹配的值
     * @param startIndex 数组开始位置
     * @param endIndex 数组结束位置
     * @return
     */
    public static int getFirstIndex(int[] array,int k,int startIndex,int endIndex){

        if (startIndex < 0  || endIndex > array.length - 1 || startIndex > endIndex){
            return -1;
        }
        int middleIndex = startIndex + (endIndex - startIndex) / 2;
        if (array[middleIndex] == k && middleIndex == 0){
            return middleIndex;
        } else if (middleIndex == 0) {
            return -1;
        }
        if (array[middleIndex] < k){
            return getFirstIndex(array,k,middleIndex+1,endIndex);
        }else if (array[middleIndex] > k){
            return getFirstIndex(array,k,startIndex,middleIndex - 1);
        }else {
            if (array[middleIndex - 1] < k) {
                return middleIndex;
            }else {
                return getFirstIndex(array,k,startIndex,middleIndex -1);
            }
        }
    }

    /**
     * 获取匹配键值的最后一个位置
     * @param array 排序数组
     * @param k 匹配值
     * @param startIndex 开始匹配位置
     * @param endIndex 结束匹配位置
     * @return
     */
    public static int getLastIndex(int[] array,int k,int startIndex,int endIndex){

        if (startIndex < 0 || endIndex >array.length -1 || startIndex > endIndex){
            return -1;
        }
        int middleIndex = startIndex + (endIndex - startIndex) / 2;
        if (array[middleIndex] == k && middleIndex == array.length -1){
            return middleIndex;
        }
        if (middleIndex == array.length - 1){
            return -1;
        }
        if (array[middleIndex] < k){
            return getLastIndex(array,k,middleIndex + 1,endIndex);
        }else if (array[middleIndex] > k){
            return getLastIndex(array,k,startIndex,middleIndex - 1);
        }else {
            if (array[middleIndex + 1] > k){
                return middleIndex;
            }
            return getLastIndex(array,k,middleIndex+1,endIndex);
        }
    }
}
