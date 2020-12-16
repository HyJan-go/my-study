package com.tt.study;

import com.tt.study.util.TreeNode;

import java.util.Objects;

/**
 * @program: algorithm
 * @description: 二叉树的序列化与反序列化
 * 请实现两个函数，分别用来序列化和反序列化二叉树
 *
 * 二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#），以 ！ 表示一个结点值的结束（value!）。
 *
 * 二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
 *
 * 例如，我们可以把一个只有根节点为1的二叉树序列化为"1,"，然后通过自己的函数来解析回这个二叉树
 * @author: HyJan
 * @create: 2020-12-15 18:03
 **/
public class TreeSerializeAndDeSerialize {

    // 要定义成全局的变量
    StringBuilder builder = new StringBuilder();

    // 初始化序列化完成之后的数组位置
    int index = -1;

    /**
     * 序列化方法，有数值先填入数值，没有数字，填入 #, 逗号用于分隔各个节点
     * @param root
     * @return
     */
    String Serialize(TreeNode root) {

        // 有值就填入值，没有值就填入#，补充完逗号
        if (Objects.isNull(root)){
            builder.append("#");
        }else {
            builder.append(root.getVal());
        }
        builder.append(",");

        // 先根遍历法，先遍历根节点，再遍历左右子节点
        if (Objects.nonNull(root)){
            Serialize(root.getLeft());
            Serialize(root.getRight());
        }

        // 返回序列化完成的字符串
        return builder.toString();
    }


    /**
     * 反序列化方法，根据逗号分隔拿到所有的节点。然后重新填充节点组装成二叉树
     * @param str
     * @return
     */
    TreeNode Deserialize(String str) {
        if ("".equals(str.trim())){
            return null;
        }

        String[] split = str.split(",");

        return myDeserialize(split);
    }

    /**
     * 自定义反序列化方式
     * @param serialize
     * @return
     */
    TreeNode myDeserialize(String[] serialize) {
        // 每进来一次，位置往下移动一次
        index ++;

        // 每进来一次，都要新建一个节点
        TreeNode root = null;

        // 只要不是空，则继续递归左右节点
        if (!Objects.equals("#",serialize[index])){
            root = new TreeNode(Integer.valueOf(serialize[index]));
            root.setLeft(myDeserialize(serialize));
            root.setRight(myDeserialize(serialize));
        }
        return root;
    }

}
