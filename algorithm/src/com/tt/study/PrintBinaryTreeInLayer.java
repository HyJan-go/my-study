package com.tt.study;

import com.tt.study.util.TreeNode;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @description: 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 * @author: HyJan
 * @create: 2020-10-12 20:34
 **/
public class PrintBinaryTreeInLayer {

    /**
     * 按层级打印，只要每层从左到右存起来，从左到右读取，就可以了
     * 当前层和下一层，然后下一层成为当前层，下一层继续往下，把移除节点的左右节点接入
     * @param pRoot
     * @return
     */
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        if (Objects.isNull(pRoot)){
            return lists;
        }
        // 当前层节点
        ArrayList<TreeNode> currentLayer = new ArrayList<>();
        // 下一层节点
        ArrayList<TreeNode> nextLayer = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        currentLayer.add(pRoot);
        while (currentLayer.size() > 0){
            // 正着读
            TreeNode remove = currentLayer.remove(0);
            temp.add(remove.getVal());
            // 正着插入
            if (Objects.nonNull(remove.getLeft())){
                nextLayer.add(remove.getLeft());
            }
            if (Objects.nonNull(remove.getRight())){
                nextLayer.add(remove.getRight());
            }
            if (currentLayer.size() == 0){
                currentLayer = nextLayer;
                nextLayer = new ArrayList<>();
                lists.add(temp);
                temp = new ArrayList<>();
            }
        }
        return lists;
    }
}
