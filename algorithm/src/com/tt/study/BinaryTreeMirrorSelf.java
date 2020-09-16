package com.tt.study;

import com.tt.study.util.TreeNode;

import java.util.Objects;

/**
 * @description: 请实现一个函数，用来判断一棵二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 * @author: HyJan
 * @create: 2020-09-15 15:26
 **/
public class BinaryTreeMirrorSelf {

    /**
     * 思路：
     * 首先判断这棵树是不是空树以及是否只是根节点。如果满足则是是对称树。左右子树不同时为空时不是
     * 如果不是上述情况，就要递归遍历树的每个节点(左子树中每个节点的左孩子 = 同层右子树中每个节点的右孩子 &&
     * 左子树中每个节点的右孩子 = 同层右子树中每个节点的左孩子)
     * @param pRoot
     * @return
     */
    boolean isSymmetrical(TreeNode pRoot) {
        boolean isMirrorTree = Objects.isNull(pRoot) || Objects.isNull(pRoot.getLeft()) && Objects.isNull(pRoot.getRight());
        if (isMirrorTree){
            return true;
        }
        return Symmetrical(pRoot.getLeft(),pRoot.getRight());
    }

    /**
     * 递归判断左右子树是否对称
     * @param left
     * @param right
     * @return
     */
    boolean Symmetrical(TreeNode left,TreeNode right){
        if (Objects.isNull(left) && Objects.isNull(right)){
            return true;
        }

        // 判断是否相同或者出现单边为空
        if (Objects.isNull(left) || Objects.isNull(right) || left.getVal() != right.getVal()){
            return false;
        }

        return Symmetrical(left.getLeft(),right.getRight()) && Symmetrical(left.getRight(),right.getLeft());
    }
}
