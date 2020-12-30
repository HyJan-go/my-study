package com.nowcoder.study;

import com.nowcoder.study.util.TreeLinkNode;

import java.util.Objects;

/**
 * @description: 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 * @author: HyJan
 * @create: 2020-09-07 16:48
 **/
public class BinaryTreeTheNextNode {

    /**
     * 思路： （一共可以分为三种情况，这三个情况的顺序是不能打乱的）
     * 1. 二叉树为空，则返回空；
     * 2. 节点右孩子存在，则指针从该节点的右孩子出发，一直沿着指向左子结点的指针找到的叶子节点即为下一个节点；
     * 3. 节点不是根节点。如果该节点是其父节点的左孩子，则返回父节点；否则继续向上遍历其父节点的父节点，重复之前的判断，返回结果。(要画图理解这个部分)
     * @param pNode
     * @return
     */
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (Objects.isNull(pNode)){
            return null;
        }

        if (Objects.nonNull(pNode.getRight())){
            pNode = pNode.getRight();
            while (Objects.nonNull(pNode.getLeft())){
                pNode = pNode.getLeft();
            }
            return pNode;
        }

        while (Objects.nonNull(pNode.getNext())){
            if (Objects.equals(pNode.getNext().getLeft(),pNode)){
                return pNode.getNext();
            }
            pNode = pNode.getNext();
        }

        return null;
    }


}
