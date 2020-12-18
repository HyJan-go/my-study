package com.tt.study;

import java.util.*;

/**
 * @program: algorithm
 * @description: 题目描述
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 * 窗口大于数组长度的时候，返回空
 * 示例1
 * 输入
 * [2,3,4,2,6,2,5,1] , 3
 * 返回值
 * [4,4,6,6,6,5]
 *
 * 滑动窗口的问题，每次入都得记住当前最大值，使用两个指针，分别指向
 * @author: HyJan
 * @create: 2020-12-18 15:28
 **/
public class SliptWindowNArray {

    // 使用一个队列来存放正在被队列包围的数
    private static ArrayDeque<Integer> queue = new ArrayDeque();

    public static ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> result = new ArrayList<>();
        if (num.length <= 0 || num.length < size || size <= 0){
            return result;
        }

        int x = 0;
        Integer max = Integer.MIN_VALUE;

        while (x < num.length){
            queue.addLast(num[x]);
            if (num[x] > max){
                max = num[x];
            }
            if (x >= size - 1){
                result.add(max);
            }
            x ++;
            if (queue.size() >= size){
                Integer first = queue.pollFirst();
                if (first == max){
                    max = getMax();
                }
            }
        }
        return result;
    }

    public static int getMax(){
        int max = Integer.MIN_VALUE;
        for (int i = 0;i < queue.size(); i++){
            Integer integer = queue.pollFirst();
            if (integer > max) max = integer;
            queue.addLast(integer);
        }
        return max;
    }

    public static void main(String[] args) {
       // [2,3,4,2,6,2,5,1],3
        int[] arr = new int[]{10,14,12,11};
        ArrayList<Integer> integers = maxInWindows(arr, 1);
        System.out.println(integers);
    }

}
