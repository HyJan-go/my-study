package com.tt.study;

/**
 * @program: algorithm
 * @description: 题目描述
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 * 示例1
 * 输入
 * 5,10,10
 * 返回值
 * 21
 *
 * 思路： 每一次只能向左，右，上，下四个方向移动一格
 * 这个是一个限定条件，所以绝对不能够一个一个的进行判断，那肯定是错误的，所以使用的还是递归，并且还不能重复走格子
 * @author: HyJan
 * @create: 2020-12-25 10:03
 **/
public class RombotInRectangle {

    /**
     * 计算个数并返回
     * @param threshold
     * @param rows
     * @param cols
     * @return
     */
    public int movingCount(int threshold, int rows, int cols) {
        int count = 0;
        // 判断输入
        if (threshold < 0 || rows < 0 || cols < 0) {
            return count;
        }

        boolean[][] visited = new boolean[rows][cols];

        // 从第一个点开始走，所以初始为0,0
        return movingAction(threshold,rows,cols,0,0,visited);
    }


    /**
     * 递归判断所有经过有可能的结点，计算能走到的点
     * @param threshold
     * @param rows
     * @param cols
     * @param i
     * @param j
     * @param visited
     * @return
     */
    private static int movingAction(int threshold, int rows, int cols,int i,int j,boolean[][] visited){

        // 判断退出条件,不符合条件直接返回0，说明不能到这个节点，或者是已经经过了
        if (i < 0 || j < 0 || i >= rows || j >= cols || (visited[i][j]==true) || (getNumberSum(i) + getNumberSum(j) > threshold)){
            return 0;
        }

        // 如果满足条件，则设置当前节点为经过了
        visited[i][j] = true;

        // 然后继续递归判断当前节点的上下左右看看是否符合，并且算上自身个数 1
        return 1 + movingAction(threshold,rows,cols,i,j -1,visited) + movingAction(threshold,rows,cols,i,j + 1,visited)
                + movingAction(threshold,rows,cols,i - 1,j ,visited) + movingAction(threshold,rows,cols,i + 1,j ,visited);
    }


    /**
     * 获取一个数字的数位数字之和   eg: 18 : 1 + 8 = 9     596 : 5 + 9 + 6 = 20
     * @param number
     * @return
     */
    private static int getNumberSum(int number){
        int count = 0;
        if(number == 0) {
            return count;
        }else {
            do {
                count += number % 10;
                number = number / 10;
            }while (number != 0);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(getNumberSum(1));
        System.out.println(new RombotInRectangle().movingCount(10,1,100));
    }

}
