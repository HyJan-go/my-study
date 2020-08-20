package com.tt.study;

import com.tt.study.util.ListNode;

import java.util.List;
import java.util.Objects;

/**
 * @description: 个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 * @author: HyJan
 * @create: 2020-08-20 15:16
 **/
public class DeleteRepeatNodeInListNode {

    /**
     * 删除一个链表中有重复的节点，并且重复的节点是要完全的删除，不进行保留
     * 思路： 定义两个指针，第一个指向当前不重复的节点，第二个指向当前不重复的下一个，并判断是否重复
     * 如果重复，则找到第一个不出现不重复的然后将当前第二个节点执向它，第一个暂时不变
     * 只有第二个节点确定为不重复节点之后，才把第一个节点执向第二个节点
     *
     * @param pHead
     */
    public static ListNode deleteDuplication(ListNode pHead) {
        // 判断输入
        if (Objects.isNull(pHead)) {
            return pHead;
        }

        ListNode h = pHead, pre = new ListNode(0);
        pre.setNext(pHead);
        ListNode post = null;

        while (Objects.nonNull(h) && Objects.nonNull((post = h.getNext()))) {
            // 如果不相同，则三者同时下移一位
            if (!Objects.equals(h.getVal(), post.getVal())) {
                pre = h;
            }else {
                // 如果是相等的，post就一直往下走
                while (Objects.nonNull(post) && Objects.equals(h.getVal(),post.getVal())){
                    post = post.getNext();
                }
                // 确定不重复的指针指向，从第一个开始就是不确定的
                if (pre.getNext() == pHead){
                    // 要改变head指针的指向
                    pHead = post;
                }
                // pre 的下一个指向post，pre最终都是要将post连接起来，只有pre=h这个操作执行了，pre才成为phead的引用
                pre.setNext(post);
            }
            h = post;
        }
        return pHead;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(3);
        ListNode listNode5 = new ListNode(3);
        ListNode listNode6 = new ListNode(25);
        listNode.setNext(listNode1);
        listNode1.setNext(listNode2);
        listNode2.setNext(listNode3);
        listNode3.setNext(listNode4);
        listNode4.setNext(listNode5);
        listNode5.setNext(listNode6);
        ListNode listNode7 = deleteDuplication(listNode);
        System.out.println("===========================");
        while (listNode7 != null) {
            System.out.println(listNode7.getVal());
            listNode7 = listNode7.getNext();
        }
    }
}
