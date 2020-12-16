package com.tt.study;

import com.tt.study.util.TreeNode;

import java.util.Objects;
import java.util.Stack;

/**
 * @program: algorithm
 * @description: 给定一棵二叉搜索树，请找出其中的第k小的结点。
 * 示例1
 * 输入
 * {5,3,7,2,4,6,8},3
 * 返回值
 * {4}
 * 说明
 * 按结点数值大小顺序第三小结点的值为4
 *
 * 其实这个就是中根遍历，遍历到地k个
 * @author: HyJan
 * @create: 2020-12-16 11:39
 **/
public class FindBinSearchTreeN {

    /**
     * 使用非递归的中序遍历方式实现
     * 使用栈把遍历过的节点保存起来
     * @param pRoot
     * @param k
     * @return
     */
    TreeNode KthNode(TreeNode pRoot, int k) {
        // 判断前置条件
        if (Objects.isNull(pRoot) || k <= 0){
            return null;
        }

        TreeNode node = pRoot;
        // 新建一个栈
        Stack<TreeNode> stack = new Stack<>();
        // 计算当前是第几个出栈的
        int count = 0;

        do {
            if (Objects.nonNull(node)){
                stack.push(node);
                node = node.getLeft();
            }else { //如果为空，表示要弹出了
                node = stack.pop();
                // 弹出一个计算一次
                count ++;
                if (count == k){
                    return node;
                }
                // 把node指向弹出节点的
                node = node.getRight();
            }
        }while (Objects.nonNull(node) || !stack.isEmpty());

        return null;
    }

}
