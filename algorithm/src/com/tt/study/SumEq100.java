package com.tt.study;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @program: algorithm
 * @description: 小明很喜欢数学, 有一天他在做数学作业时, 要求计算出9~16的和,他马上就写出了正确答案是100。但是他并不满足于此,
 * 他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。
 * 现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 * 输出描述:
 * 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 * @author: HyJan
 * @create: 2020-07-21 14:47
 **/
public class SumEq100 {

    /**
     * 求和为100的连续序列
     * @param sum
     * @return
     */
    public static ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        // 判断输入
        if (sum < 1){
            return lists;
        }
        for (int i = 1; i < sum; i++) {
            // 因为是序列，所以至少是两个数，如果相邻相加就大于sum，往后是不会再有匹配了
            if (i + i+1 > sum){
                break;
            }
            ArrayList<Integer> fun = findFun(sum, i, 0, new ArrayList<Integer>());
            if (Objects.nonNull(fun)){
                lists.add(fun);
            }
        }
        return lists;
    }

    /**
     * 递归方式求序列只和等于100
     * @param sum
     * @param i
     * @param list
     * @return
     */
    public static ArrayList<Integer> findFun(int sum,int i,int iSum,ArrayList<Integer> list){
        // 所有递归函数，先判断退出条件
        if (iSum + i > sum){
            list.clear();
            return null;
        } else if (iSum + i == sum){
            list.add(i);
            return list;
        }else {
            list.add(i);
            return findFun(sum,i + 1,iSum + i,list);
        }
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> lists = FindContinuousSequence(100);
        for (ArrayList<Integer> list: lists
             ) {
            System.out.println(list.toString());
        }
    }
}
