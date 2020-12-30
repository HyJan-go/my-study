package com.nowcoder.study;

import com.nowcoder.study.util.ListNode;

import java.util.Objects;

/**
 * @program: algorithm
 * @description: 输入两个链表，找出它们的第一个公共结点。（注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
 * @author: HyJan
 * @create: 2020-07-02 15:58
 **/
public class LinkFirstNode {

    /**
     * 首先要了解这两个链表是怎么样的
     * 思路：从后往前比较，找到一个不相同的，那不相同的下一个则为第一个节点，这里采用下面博客的第二种方式编写
     * 如果长度不一，则先让长的先走，后面再一起走（此方法要先获得每个节点的长度）
     * https://blog.csdn.net/Forlogen/article/details/104895234
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        // 判断输入
        if(Objects.isNull(pHead1) || Objects.isNull(pHead2)){
            return null;
        }
        int listNum = getListNum(pHead1);
        int num = getListNum(pHead2);
        ListNode result = null;
        int first = 0,second = 0;
        if (listNum > num){
            first = listNum - num;
        }
        if (listNum < num){
            second = num - listNum;
        }

        if (first > second){
            for (int i = 0; i < first; i++) {
                pHead1 = pHead1.getNext();
            }
            result = getFirstSameNode(pHead1,pHead2);
        }else if (first < second){
            for (int i = 0; i < second; i++) {
                pHead2 = pHead2.getNext();
            }
            result = getFirstSameNode(pHead1,pHead2);
        }else {
            result = getFirstSameNode(pHead1,pHead2);
        }
        return result;
    }

    /**
     * 找出第一个相同的节点并返回
     * @return
     */
    private ListNode getFirstSameNode(ListNode pHead1,ListNode pHead2){
        while (pHead1.getVal() != pHead2.getVal() && pHead1.getNext() != null){
            pHead1 = pHead1.getNext();
            pHead2 = pHead2.getNext();
        }
        if (pHead1.getVal() != pHead2.getVal()){
            return null;
        }
        return pHead1;
    }

    /**
     * 获取某个节点的长度
     * @param node
     * @return
     */
    public int getListNum(ListNode node){
        int num = 0;
        if (Objects.isNull(node)){
            return num;
        }
        ListNode listNode = node;
        while (Objects.nonNull(listNode.getNext())){
            listNode = listNode.getNext();
            num++;
        }
        return num;
    }

}
