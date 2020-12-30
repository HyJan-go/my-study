package com.nowcoder.study;

import java.util.Objects;

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
        // 判断输入
        if (matrix.length < 0 || rows < 0 || cols < 0 || str.length < 0){
            return false;
        }

        // 初始化要寻找的字符串的位置
        int[] index = {0};
        // 记录已经路过的位置
        boolean[] visited = new boolean[rows * cols];

        // 遍历每一个节点，寻找每一个可能
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isPath(matrix,rows,cols,str,i,j,visited,index)){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 递归判断是否有这样的一条路径
     * @param matrix
     * @param rows
     * @param cols
     * @param str
     * @param row
     * @param col
     * @param visited
     * @param index
     * @return
     */
    public static boolean isPath(char[] matrix, int rows, int cols, char[] str,int row,int col,boolean[] visited,int[] index){

        // 如果指针指向了判断字符串的长度位置，说明全部都遍历完了
        if (Objects.equals(index[0],str.length)){
            return true;
        }

        boolean flag = false;

        // 当前点折算到原数组的位置是：row * cols + col
        // 正常位置的，并且还没访问过的，并且跟对应位置的字符串匹配上，才能继续往下执行
        if (row < rows && row >= 0 && col < cols && col >= 0 && !visited[row * cols + col] && matrix[row * cols + col] == str[index[0]]){
            // 判断下一个位置
            index[0] ++;
            // 当前位置设置为已经遍历过
            visited[row * cols + col] = true;

            // 递归上下左右判断是否有一个可以满足要求
            flag = isPath(matrix,rows,cols,str,row - 1,col,visited,index)
                    || isPath(matrix,rows,cols,str,row + 1,col,visited,index)
                    || isPath(matrix,rows,cols,str,row,col - 1,visited,index)
                    || isPath(matrix,rows,cols,str,row,col + 1,visited,index);

            // 如果找不到，则得恢复，说明此路不通
            if (!flag){
                index[0] --;
                visited[row * cols + col] = false;
            }
        }

        return flag;
    }

    public static void main(String[] args) {
        String str = "ABCEHJIGSFCSLOPQADEEMNOEADIDEJFMVCEIFGGS";
        char[] matrix = str.toCharArray();
        String str2 = "SGGFIECVAASABCEEJIGOEM";
        char[] c = str2.toCharArray();
        System.out.println(new RectangleHasPathToString().hasPath(matrix, 5, 8, c));
    }

}
