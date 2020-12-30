package com.nowcoder.study;

import java.util.Comparator;

/**
 * @program: algorithm
 * @description: 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 * @author: HyJan
 * @create: 2019-11-26 09:04
 **/
public class MinValue {

    /**
     * 实现一个自定义的比较器
     * 用来实现算法的逻辑
     * 想要实现在组合后的字符串相对较小的放在前面，这样最后只要重组string数组即可
     */
    private static class MyComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            if(o1 == null || o2 == null){
                throw new IllegalArgumentException("比较参数不能为空！！！");
            }
            String str1 = o1 + o2;
            String str2 = o2 + o1;

            return str1.compareTo(str2);
        }
    }

    /**
     * 带着比较器进行的快速排序
     * @param arr 待排序的字符串数组
     * @param start 待排序数组的开始位置
     * @param end 待排序数组的结束位置
     * @param comparator 比较器
     */
    public static void quickSort(String[] arr,int start,int end,Comparator<String> comparator){
        if (start < end){
             String seed = arr[start];
             int left = start;
             int right = end;

            while (start < end){
                while (start < end && comparator.compare(arr[end],seed) >= 0){
                    end --;
                }

                //换位，跟种子结合之后，适合放前面的，覆盖一下位置
                arr[start] = arr[end];

                while (start < end && comparator.compare(arr[start],seed) <= 0){
                    start ++;
                }

                //换位，跟种子结合之后，适合放前面的，覆盖一下位置
                arr[end] = arr[start];
            }
            //start位置变成了最后种子应该存放的位置
            arr[start] = seed;

            /**
             * 对两边分别进行快排
             */
            quickSort(arr,left,start - 1,comparator);
            quickSort(arr,start + 1,right,comparator);
        }
    }

    /**
     * 打印最小的结果，传入的参数是一个数组
     * @param numbers 字符串数组
     * @return 返回一个最小组合数字
     */
    public static String PrintMinNumber(int[] numbers) {

        /**
         * 判断输入
         */
        if (numbers == null || numbers.length < 1){
            return "";
        }

        /**
         * 将数字类型的数组转化为字符串类型的数组
         */
        String str[] = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            str[i] = String.valueOf(numbers[i]);
        }

        MyComparator myComparator = new MyComparator();
        quickSort(str,0,str.length-1,myComparator);

        StringBuilder stringBuilder = new StringBuilder();
        for (String s: str
             ) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        int[] i = {3,32,321};
        String s = PrintMinNumber(i);
        System.out.println(s);
    }
}
