package com.tt.study;

import java.util.LinkedList;

/**
 * @program: algorithm
 * @description: 每年六一儿童节, 牛客都会准备一些小礼物去看望孤儿院的小朋友, 今年亦是如此。HF作为牛客的资深元老,自然也准备了一些小游戏。
 * 其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。
 * 每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,
 * 继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
 * 请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
 * 如果没有小朋友，请返回-1
 *
 * 这道题的背景是约瑟夫环
 *
 * @author: HyJan
 * @create: 2020-07-16 17:18
 **/
public class LastPerson {

    /**
     * 约瑟夫环的计算方式
     * @param n
     * @param m
     * @return
     */
    public static int getLast(int n,int m){
        if (n < 1 || m < 1){
            return -1;
        }
        int last = 0;
        for (int i = 2; i <= n; i ++){
            last = (last + m) % i;
            System.out.println(last);
        }
        return last;
    }

    public static void main(String[] args) {
        int last = getLast(5,2);
        System.out.println("======================");
        System.out.println(last);
    }

    /**
     * 利用约瑟夫环问题进行改造
     * @param n 学生总人数
     * @param m 
     * @return
     */
    public static int LastRemaining_Solution(int n,int m){
        if (n < 1 || m < 1){
            return -1;
        }
        // 使用链表的数据结构来模拟学生绕圈
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        // 定义一个删除的节点位置
        int removeIndex = 0;
        while (list.size() > 1){
            // list从零开始,所以位置要减1
            removeIndex = (removeIndex + m - 1) % list.size();
            // 选中的学生被剔除圈子
            list.remove(removeIndex);
        }
        // 最后数组中剩下的最后一个则是直接受益者
        return list.get(0);
    }
}
