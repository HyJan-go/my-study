package com.nowcoder.study;

import java.util.Arrays;

/**
 * @program: algorithm
 * @description: LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...
 * 他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！
 * “红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,他想了想,决定大\小 王可以看成任何数字,
 * 并且A看作1,J为11,Q为12,K为13。上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。LL决定去买体育彩票啦。
 * 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何， 如果牌能组成顺子就输出true，否则就输出false。为了方便起见,你可以认为大小王是0。
 *
 * 思路： 其实就是分两种情况，一种是四个王和1个数字，这样肯定是顺子；另外一种就是多个数字，这时需要先对数字进行排序，
 * 对排序后的数字，计算最大值和最小值之间的差值。只要有两个数字以上，就排序后就找到最大和最小的数字，在差值内的方可组成顺子
 * 题目的意思就是仅仅算拿五个牌能不能成为一个顺子
 * @author: HyJan
 * @create: 2020-07-17 18:19
 **/
public class CashPai {

    /**
     * 判断是否能够成为一个顺子，大小王是0
     * @param numbers
     * @return
     */
    public static boolean isToOrder(int[] numbers){
        // 判断输入
        if (numbers.length < 1 || numbers.length > 5){
            return false;
        }
        // 使用jdk提供的排序对数组进行排序
        Arrays.sort(numbers);
        // 找到大小王的个数，并且记录最大最小值
        int max = -1,min = -1,kingNum = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != 0 && min == -1){
                min = numbers[i];
                continue;
            }
            if (numbers[i] != 0 && numbers[i] > max){
                max = numbers[i];
                continue;
            }
            kingNum ++;
        }
        // 判断是否有四个大小王
        if (kingNum == 4){
            return true;
        }
        // 判断最大最小值差是否在合适的范围内，这个还要注意，因为扑克是会重复的，所以得严谨
        if (max > 0 && min > 0 && max - min <= 4 && max - min > 0){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] array = {1,0,0,1,0};
        boolean toOrder = isToOrder(array);
        System.out.println(toOrder);
    }
}
