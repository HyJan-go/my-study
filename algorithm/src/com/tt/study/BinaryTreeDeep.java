package com.tt.study;

import com.tt.study.util.TreeNode;

import java.util.Objects;

/**
 * @program: algorithm
 * @description: 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 * @author: HyJan
 * @create: 2020-06-29 18:16
 **/
public class BinaryTreeDeep {

    /**
     * 递归求树的深度
     * 主要就是解决小的重复循环的问题变为递归
     * @return
     */
    public static int treeDepth(TreeNode root){
        if (Objects.isNull(root)){
            return 0;
        }
        // 某个节点为根节点的左子树深度
        int leftDepth = treeDepth(root.getLeft());
        // 某个节点为根节点的柚子树深度
        int rightDepth = treeDepth(root.getRight());
        // 判断左右子树深度再加上自身则是最高深度
        return 1 + (leftDepth > rightDepth ? leftDepth : rightDepth);
    }

    /**
     * 使用另外一种方式，在获取不到getLeft的情况下实现
     * 思路：记录当前计算所在行的高度，每往下走就加1，走到null那层已经超出了，-1就可以
     */
    public static int treeDepth(TreeNode root,int depth){
        if (Objects.isNull(root)){
            return depth - 1;
        }
        int leftDepth = treeDepth(root.getLeft(), depth + 1);
        int rightDepth = treeDepth(root.getRight(), depth + 1);
        return leftDepth > rightDepth ? leftDepth : rightDepth;
    }
}
