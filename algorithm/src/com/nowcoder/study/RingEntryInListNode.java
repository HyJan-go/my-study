package com.nowcoder.study;

import com.nowcoder.study.util.ListNode;

import java.util.Objects;

/**
 * @description: 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 * @author: HyJan
 * @create: 2020-08-20 14:40
 **/
public class RingEntryInListNode {

    /**
     * 第一步，用两个快慢指针找环中相汇点。分别用slow, fast指向链表头部，slow每次走一步，fast每次走二步，直到fast == slow找到在环中的相汇点。
     * 第二步，找环的入口。当fast == slow时，假设slow走过x个节点，则fast走过2x个节点。设环中有n个节点，因为fast比slow多走一圈（n个节点），
     * 所以有等式2x = n + x，可以推出n = x，即slow实际上走了一个环的步数。这时，我们让fast重新指向链表头部pHead，slow的位置不变，
     * 然后slow和fast一起向前每次走一步，直到fast == slow，此时两个指针相遇的节点就是环的入口。
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        // 判断输入，无二不成环
        if (Objects.isNull(pHead) || Objects.isNull(pHead.getNext())) {
            return null;
        }
        ListNode slow = pHead.getNext();
        ListNode fast = pHead.getNext().getNext();

        // 找相遇点
        while (!Objects.equals(fast, slow) && Objects.nonNull(fast)) {
            // 没相遇之前，都是slow走一步，fast走两步
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        // fast不为空，说明找到了
        if (Objects.nonNull(fast)){
            // 如果到了fast可以重头走找环入口了
            fast = pHead;
            while (!Objects.equals(fast, slow)) {
                fast = fast.getNext();
                slow = slow.getNext();
            }
            // 返回第二次相遇的点（环入口）
            return fast;
        }

        // 找不到则返回null
        return null;
    }
}
