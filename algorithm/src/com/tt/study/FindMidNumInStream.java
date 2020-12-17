package com.tt.study;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * @program: algorithm
 * @description: 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法读取数据流，
 * 使用GetMedian()方法获取当前读取数据的中位数。
 *
 * 思路：使用大顶堆（左边）和小顶堆（右边）两个数据结构来同时完成    堆：其实就是优先队列
 * @author: HyJan
 * @create: 2020-12-17 16:53
 **/
public class FindMidNumInStream {

    // 构建一个小顶堆，默认的优先队列
    private static PriorityQueue<Integer> rqueue = new PriorityQueue<>();

    // 构建一个大顶堆（倒序的，那就得重写比较器）
    private static PriorityQueue<Integer> lqueue = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            // 默认是o1 -o2，现在要进行逆转，如果后面的o2越大，越优先
            return o2 - o1;
        }
    });

    /**
     * 当一个数字进入流，由于要的是中位数，所以先判断两个堆的数量，必须保持一致，或者差值为左边比右边大1
     * 先按大小进行分都两个堆，保证顺序，然后再平衡两个堆的数量
     * @param num
     */
    public static void Insert(Integer num) {
        if (Objects.isNull(num)){
            return;
        }

        // 先根据大小进行分堆
        if (lqueue.isEmpty() || num < lqueue.peek()){
            lqueue.offer(num);
        }else {
            rqueue.offer(num);
        }

        // 根据两个堆的数量进行调整，保证两个堆的数量，必须保持一致，或者差值为左边比右边大1
        if (rqueue.size() > lqueue.size()){
            lqueue.offer(rqueue.poll());
        }else if (lqueue.size() - rqueue.size() >= 2){  // 左堆的数量超了2个
            rqueue.offer(lqueue.poll());
        }
    }

    /**
     * 根据堆的长度来判断返回
     * 如果两个堆的数量是一样的，则取出顶头的两个数字进行平均值
     * 如果不一样，直接返回左堆的顶部
     * @return
     */
    public static Double GetMedian() {
        // 这里要特别注意返回的是double类型
        if (lqueue.size() == rqueue.size()){
            Double left = new Double(lqueue.peek());
            Double right = new Double(rqueue.peek());
            return new Double((left + right) / 2);
        }else {
            return new Double(lqueue.peek());
        }
    }

    public static void main(String[] args) {
        // 两者都是往队列尾部插入元素，不同的时候，当超出队列界限的时候，add（）方法是抛出异常让你处理，而offer（）方法是直接返回false
//        lqueue.add(4);
//        lqueue.add(1);
//        lqueue.add(2);
//        lqueue.add(3);
//        lqueue.offer(5);
//        lqueue.peek();
//        // poll()方法用于返回第一个元素，并从此PriorityQueue中删除一个元素。
//        lqueue.poll();
//        System.out.println(lqueue.peek());

        Insert(5);
        Insert(2);
        System.out.println(GetMedian());
    }
}
