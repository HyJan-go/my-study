package com.nowcoder.study;

/**
 * @program: algorithm
 * @description: 把只包含质因子2、3和5的数称作丑数（Ugly Number）。
 * 例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 *
 *根据丑数的定义，我们可以知道丑数可以由另外一个丑数乘以2，3或者5得到。因此我们可以创建一个数组，里面的数字是排好序的丑数，每一个丑数都是前面的丑数乘以2，3或者5得到的。
 *
 * 我们把得到的第一个丑数乘以2以后得到的大于M的结果记为M2。同样，我们把已有的每一个丑数乘以3和5，能得到第一个大于M的结果M3和M5。
 * 那么M后面的那一个丑数应该是M2,M3和M5当中的最小值：Min(M2,M3,M5)。比如将丑数数组中的数字按从小到大乘以2，直到得到第一个大于M的数为止，那么应该是2*2=4<M，3*2=6>M，所以M2=6。同理，M3=6，M5=10。所以下一个丑数应该是6。
 * @author: HyJan
 * @create: 2019-11-27 10:17
 **/
public class UglyNumber {

    public static int GetUglyNumber_Solution(int index) {

        //判断输入，看输入是否合法
        if(index <= 0){
            return 0;
        }

        //建立一个丑数数组，数组长度和位置一样
        int[] uglyNumbers = new int[index];
        uglyNumbers[0] = 1;  //初始化第一个丑数

        /**
         * 每次乘以2 ，3，5的初始基数
         */
        int m2 = 0;
        int m3 = 0;
        int m5 = 0;

        int nextIndex = 1; //下一个位置

        while (nextIndex < index){
            int min = Min(uglyNumbers[m2] * 2, uglyNumbers[m3] * 3, uglyNumbers[m5] * 5);
            uglyNumbers[nextIndex] = min;

            /**
             * 如果是最小了，则乘以下一个位置的
             */
            while (uglyNumbers[m2] * 2 <= uglyNumbers[nextIndex]){
                m2 ++;
            }

            while (uglyNumbers[m3] * 3 <= uglyNumbers[nextIndex]){
                m3 ++;
            }

            while (uglyNumbers[m5] * 5 <= uglyNumbers[nextIndex]){
                m5 ++;
            }

            nextIndex ++;
        }

        int result = uglyNumbers[index - 1];
        uglyNumbers = null;
        return result;
    }

    /**
     * 返回三个值的最小值
     * @param a 比较数1
     * @param b 比较数2
     * @param c 比较数3
     * @return
     */
    public static int Min(int a,int b,int c){
        return a < b ? ( a < c ? a : c ) : (b < c ? b : c);
    }

    public static void main(String[] args) {
        int i = GetUglyNumber_Solution(6);
        System.out.println(i);
    }

}
