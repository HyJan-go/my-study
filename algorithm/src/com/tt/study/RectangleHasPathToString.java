package com.tt.study;

/**
 * @program: algorithm
 * @description: 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子,例如：
 * ⎣ a b c e ⎤
 * ⎣ s f c s ⎤
 * ⎣ a d e e ⎦
 * 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 * 示例1
 * 输入
 * "ABCESFCSADEE",3,4,"ABCCED"
 * 返回值
 * true
 * 示例2
 * 输入
 * "ABCESFCSADEE",3,4,"ABCB"
 * 返回值
 * false
 * @author: HyJan
 * @create: 2020-12-21 17:42
 **/
public class RectangleHasPathToString {

    /**
     *  思路：回溯法：首先任意一个点都有可能成为起点，所以要获得任意一点的坐标(位于第几行，第几列)
     *  其次要有一个数组记录这个点是否被访问过，同时要有一个指针来记录字符串中字符的位置。
     *  当某个点成为合法的起点时，即这个点与字符串中第一个字符相等，则可以继续朝上下左右搜索下一个点；
     *  如果这个点不能成为合法的起点，则恢复现场(这个点没有被访问过且字符串指针后退)
     */
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {

    }
}
