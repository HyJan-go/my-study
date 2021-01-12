package com.leetcode;

import java.util.Arrays;
import java.util.Objects;

/**
 * @program: algorithm
 * @description: 翻转图像
 * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
 *
 * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
 *
 * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
 *
 * 思路：遍历数组，然后获取每个数组内部数组，对每一个内部数据进行一个翻转
 *                双向指针，数据交换位置
 * @author: HyJan
 * @create: 2021-01-12 16:08
 **/
public class ReverseArray {

    public int[][] flipAndInvertImage(int[][] A) {
        if (Objects.isNull(A) || A.length < 0){
            return new int[0][0];
        }
        for (int i = 0; i < A.length; i++) {
            swapIndexAndValue(A[i]);
//            System.out.println(Arrays.toString(A[i]));
        }
        return A;
    }

    /**
     * 变更数组的值和翻转的值
     * @param array
     */
    public void swapIndexAndValue(int[] array){
//        System.out.println("交换前 ：" + Arrays.toString(array));
        if (Objects.isNull(array) || array.length <= 0){
            return;
        }
        // 首尾交换，并交换值
        int i = 0, j = array.length -1;
        while (i <= j){

            if (i == j){
                if (array[i] == 1){
                    array[i] = 0;
                }else {
                    array[i] = 1;
                }
                break;
            }

            /**   数值变化  */
            if (array[i] == 1){
                array[i] = 0;
            }else {
                array[i] = 1;
            }

            if (array[j] == 1){
                array[j] = 0;
            }else {
                array[j] = 1;
            }


            /**  位置交换  **/
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;

            i ++;
            j --;
        }
    }

    public static void main(String[] args) {
        int[][] array = new int[4][];
        array[0] = new int[]{1,1,0};
        array[1] = new int[]{1,0,1};
        array[2] = new int[]{0,0,0};

        // [1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]
        array[0] = new int[]{1,1,0,0};
        array[1] = new int[]{1,0,0,1};
        array[2] = new int[]{0,1,1,1};
        array[3] = new int[]{1,0,1,0};
        ReverseArray reverseArray = new ReverseArray();
        int[][] image = reverseArray.flipAndInvertImage(array);
        System.out.println("--------------------------------------------------------");
        for (int i = 0; i < image.length; i++) {
            System.out.println(Arrays.toString(image[i]));
        }
    }
}
