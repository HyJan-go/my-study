package com.tt.study.util;

/**
 * @description: 三指向的二叉树
 * @author: HyJan
 * @create: 2020-09-07 16:01
 **/
public class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    /**
     * 指向父节点
     */
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeLinkNode getLeft() {
        return left;
    }

    public void setLeft(TreeLinkNode left) {
        this.left = left;
    }

    public TreeLinkNode getRight() {
        return right;
    }

    public void setRight(TreeLinkNode right) {
        this.right = right;
    }

    public TreeLinkNode getNext() {
        return next;
    }

    public void setNext(TreeLinkNode next) {
        this.next = next;
    }
}
