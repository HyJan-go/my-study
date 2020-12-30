package com.nowcoder.study;

/**
 * @program: algorithm
 * @description: 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 * 解题思路：
 * 累加不能用循环的话，那就试试递归吧。
 * 判断递归的终止条件不能用 if 和 switch，那就用短路与代替。
 * (n > 0) && (sum += Sum_Solution(n-1))>0
 * 只有满足n > 0的条件，&&后面的表达式才会执行。
 * 如果不限制，可以直接使用等差数列的公式，然后一步到位
 * @author: HyJan
 * @create: 2020-07-14 13:43
 **/
public class DoSumWithOrder {

    public static int sum(int n){
        int sum = n;
        if (n > 0){
            return sum += sum(n - 1);
        }else {
            return sum;
        }
    }

    public static void main(String[] args) {
        System.out.println(sum(1));
    }
}
