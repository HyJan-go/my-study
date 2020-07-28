package com.tt.study;

/**
 * @program: algorithm
 * @description: 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
 * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
 * ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
 * @author: HyJan
 * @create: 2020-07-28 17:05
 **/
public class OneNumbers {

    /**
     * 求1到某个数字直接1的个数
     * 将每个数字循环，然后转为string，再遍历string每个char，看看为1的个数
     * @param n
     * @return
     */
    public static int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        // 判断输入
        if (n < 1){
            return count;
        }
        for (int i = 1; i <= n; i++) {
            String s = String.valueOf(i);
            for (int j = 0;j < s.length();j++){
                if (s.charAt(j) == '1'){
                    count ++;
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        System.out.println(NumberOf1Between1AndN_Solution(13));
    }
}
