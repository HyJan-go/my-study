package com.nowcoder.study;

import com.nowcoder.study.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @description: 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 * @author: HyJan
 * @create: 2020-09-21 18:32
 **/
public class PrintBinaryTreeInZhizi {

    /**
     * 按之字形顺序打印二叉树需要两个栈。我们在打印某一行结点时，把下一层的子结点保存到相应的栈里。
     * 如果当前打印的是奇数层，则先保存左子结点再保存右子结点到一个栈里；
     * 如果当前打印的是偶数层，则先保存右子结点再保存左子结点到第二个栈里。
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        if (Objects.isNull(pRoot)){
            return new ArrayList<>();
        }
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        // 当前层的值
        List<TreeNode> current = new LinkedList<>();
        // 当前层的下一层的值
        List<TreeNode> reverse = new LinkedList<>();
        // 假设当前位于第一层
        int depth = 1;
        current.add(pRoot);
        ArrayList<Integer> temp = new ArrayList<>();
        while (current.size() > 0){
            // 从尾部开始取，否则反向的顺序取不到准确的
            TreeNode remove = current.remove(current.size() - 1);
            temp.add(remove.getVal());
            // 如果当前是基数层，则下一层将反向插入（正向插入reverse，取得时候是反向的）
            if (depth % 2 == 1){
                if (Objects.nonNull(remove.getLeft())){
                    reverse.add(remove.getLeft());
                }
                if (Objects.nonNull(remove.getRight())){
                    reverse.add(remove.getRight());
                }
            }else {
                if (Objects.nonNull(remove.getRight())){
                    reverse.add(remove.getRight());
                }
                if (Objects.nonNull(remove.getLeft())){
                    reverse.add(remove.getLeft());
                }
            }
            if (current.size() == 0){
                // 轮询变0 1的写法
                depth = 1 - depth;
                current = reverse;
                lists.add(temp);
                temp = new ArrayList<>();
                reverse = new ArrayList<>();
            }
        }
        return lists;
    }
}
