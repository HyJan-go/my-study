package com.tt.study;

import com.sun.deploy.uitoolkit.impl.fx.AppletStageManager;
import com.tt.study.util.TreeNode;

import java.util.Objects;

/**
 * @program: algorithm
 * @description: 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 * 在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树
 * @author: HyJan
 * @create: 2020-07-24 13:53
 **/
public class IsBinaryBanlanceTree {

    /**
     * 所有节点上的左右字数高度数值差 <= 1,要注意空的树也是一个平衡二叉树
     * 利用两个递归实现
     * 1. 第一个递归是递归自身，自己以及左右字数都是平衡的才是平衡
     * 2. 第二个递归是递归求数的高度
     * @param root
     * @return
     */
    public static boolean IsBalanced_Solution(TreeNode root) {
        if(Objects.isNull(root)){
            return true;
        }
        // 左子树高度
        int leftHeight = 1 + getDepth(root.getLeft());
        // 有子树高度
        int rightHeight = 1 + getDepth(root.getRight());
        if (Math.abs(leftHeight - rightHeight) > 1){
            // 如果不符合直接返回
            return false;
        }else {
            // 如果上一个符合，还得继续判断是不是符合，直到遍历完每一个节点
            return IsBalanced_Solution(root.getLeft()) && IsBalanced_Solution(root.getRight());
        }
    }

    /**
     * 这里从0开始，如果根节点不存在，高度为0
     * @param root
     * @return
     */
    public static int getDepth(TreeNode root){
        if (Objects.isNull(root)){
            return 0;
        }
        // 当前高度多1 加上当前节点的左右子树的较大值
        return 1 + Math.max(getDepth(root.getLeft()),getDepth(root.getRight()));
    }

}
