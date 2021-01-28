//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。 
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。 
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
// 
//
// 示例 2： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 每个链表中的节点数在范围 [1, 100] 内 
// 0 <= Node.val <= 9 
// 题目数据保证列表表示的数字不含前导零 
// 
// Related Topics 递归 链表 数学 
// 👍 5545 👎 0

package cn;


/**
* @Description: 两数相加-Java
* @Author: HyJan
* @Date: 2021-01-26 17:03:06
**/

public class AddTwoNumbers{
    public static void main(String[] args) {
       Solution solution = new AddTwoNumbers().new Solution();
//        ListNode listNode = new ListNode(2);
//        ListNode left1 = new ListNode(4);
//        ListNode left2 = new ListNode(9);
//        ListNode node = new ListNode(5);
//        ListNode r1 = new ListNode(6);
//        ListNode r2 = new ListNode(4);
//        ListNode r3 = new ListNode(9);
//        listNode.next = left1;
//        left1.next = left2;
//        node.next = r1;
//        r1.next = r2;
//        r2.next = r3;
//
//        ListNode node1 = new Solution().addTwoNumbers(listNode, node);
//        while (node1 != null){
//            System.out.println(node1.val);
//            node1 = node1.next;
//        }
    }
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode head = null,tail =null;

        // 进位
        int carry = 0;
        while (l1 != null || l2 != null){
            int left = l1 == null ? 0 : l1.val;
            int right = l2 == null ? 0 : l2.val;
            int sum = carry + left + right;

            // 判断进位
            if (sum >= 10){
                carry = 1;
            }else {
                carry = 0;
            }

            // 判断当前是不是空链子
            if (head == null){
                // 空链子则进行初始化
                head = tail = new ListNode(sum % 10);
            }else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }

            // 每次运算完了，就要往下移动
            if (l1 != null){
                l1 = l1.next;
            }
            if (l2 != null){
                l2 = l2.next;
            }
        }
        // 最后可能有越位进位
        if (carry != 0){
            tail.next = new ListNode(carry);
            tail = tail.next;
        }
        return head;
    }
}
public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}