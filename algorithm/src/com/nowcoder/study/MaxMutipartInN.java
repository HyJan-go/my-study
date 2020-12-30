package com.nowcoder.study;

/**
 * @program: algorithm
 * @description: 给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1，m<=n），每段绳子的长度记为k[1],...,k[m]。请问k[1]x...xk[m]可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * 输入描述:
 * 输入一个数n，意义见题面。（2 <= n <= 60）
 * 返回值描述:
 * 输出答案。
 * 示例1
 * 输入
 * 8
 * 返回值
 * 18
 *
 * 分析：关键在于所有正整数字都可以用n个2和n个3相加所得（除了特殊的1）
 * 思路： 首先判断k[0]到k[m]可能有哪些数字，实际上只可能是2或者3。
 *
 * 当然也可能有4，但是4=2*2，我们就简单些不考虑了。
 *
 * 5 < 2 * 3， 6 < 3 * 3，比6更大的数字我们就更不用考虑了，肯定要继续分。
 *
 * 其次看2和3的数量，2的数量肯定小于3个，为什么呢？因为2 * 2 * 2 < 3 * 3，那么题目就简单了。
 *
 * 直接用n除以3，根据得到的余数判断是一个2还是两个2还是没有2就行了。
 *
 * 由于题目规定m>1，所以2只能是1 * 1，3只能是2 * 1，这两个特殊情况直接返回就行了。
 *
 * 乘方运算的复杂度为：O(log n)，用动态规划来做会耗时比较多。
 *
 * @author: HyJan
 * @create: 2020-12-29 17:58
 **/
public class MaxMutipartInN {

    public static int cutRope(int target) {
        if (target < 2){
            return 0;
        }

        // 特殊情况，2 只能是1 + 1
        if (target == 2){
            return 1;
        }

        int twoNum = 0;
        int threeNum = 0;

        // 只要 > 4 说明还能被3除，这个题目主要是计算出2和3的数量，然后相乘等到结果
        while (target > 4){
            target = target - 3;
            threeNum ++;
        }
        if (target == 4){
            twoNum += 2;
        }
        if (target == 3){
            threeNum ++;
        }
        if (target == 2){
            twoNum ++;
        }

        int twoTotal = 1;
        int threeTotal = 1;
        while (twoNum > 0){
            twoTotal *= 2;
            twoNum --;
        }
        while (threeNum > 0){
            threeTotal *= 3;
            threeNum --;
        }
        return twoTotal * threeTotal;
    }

    public static void main(String[] args) {
        System.out.println(cutRope(8));
    }

}
