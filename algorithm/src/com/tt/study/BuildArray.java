package com.tt.study;

import java.util.Objects;

/**
 * @program: algorithm
 * @description: 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
 * 不能使用除法。（注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2];）
 * @author: HyJan
 * @create: 2020-07-22 20:53
 **/
public class BuildArray {

    /**
     * 其实这个题目要的就是构建的数组，每次构建的时候，计算b[i]的时候，剔除掉A[i]的值过来相乘
     * 此时只能使用暴力的双重循环解法了
     * @param A
     * @return
     */
    public static int[] multiply(int[] A) {
        if (Objects.isNull(A) || A.length < 1){
            return new int[]{};
        }
        int[] b = new int[A.length];
        for (int i = 0; i < b.length; i++) {
            int result = 1;
            for (int j = 0; j < A.length; j++) {
                if (i != j){
                    result *= A[j];
                }
            }
            b[i] = result;
        }
        return b;
    }

}
