package com.nowcoder.study.util;

/**
 * @program: algorithm
 * @description: 模拟链表结构
 * @author: HyJan
 * @create: 2020-07-02 15:49
 **/
public class ListNode {
    int val;
    ListNode next = null;

    public ListNode(){

    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
