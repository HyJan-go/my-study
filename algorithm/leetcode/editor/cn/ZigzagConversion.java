//将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。 
//
// 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下： 
//
// 
//P   A   H   N
//A P L S I I G
//Y   I   R 
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。 
//
// 请你实现这个将字符串进行指定行数变换的函数： 
//
// 
//string convert(string s, int numRows); 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "PAYPALISHIRING", numRows = 3
//输出："PAHNAPLSIIGYIR"
// 
//示例 2：
//
// 
//输入：s = "PAYPALISHIRING", numRows = 4
//输出："PINALSIGYAHRPI"
//解释：
//P     I    N
//A   L S  I G
//Y A   H R
//P     I
// 
//
// 示例 3： 
//
// 
//输入：s = "A", numRows = 1
//输出："A"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 由英文字母（小写和大写）、',' 和 '.' 组成 
// 1 <= numRows <= 1000 
// 
// Related Topics 字符串 
// 👍 1000 👎 0

package cn;
/**
* @Description: Z 字形变换-Java
* @Author: HyJan
* @Date: 2021-02-07 11:46:28
**/
public class ZigzagConversion{
    public static void main(String[] args) {
       Solution solution = new ZigzagConversion().new Solution();
//        System.out.println(Solution.convert("PAYPALISHIRING",3));
}
//leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public  String convert(String s, int numRows) {
        // 特殊情况进行处理
        if (s.length() <= numRows || numRows == 1){
            return s;
        }

        StringBuilder sb = new StringBuilder();
        int firstLength = 0;
        int secondLength = 0;
        for (int i = 0; i < numRows; i++) {
            // 第一行 和 最后一行
            if (i == 0 || i == (numRows - 1)){
                sb.append(s.charAt(i) + "");
                secondLength = firstLength = 2 * (numRows - 1);
                int next = i + firstLength;
                while (next < s.length()){
                    sb.append(s.charAt(next) + "");
                    next = next + firstLength;
                }
            }

            // 其他行
            else {
                // 每往下一行，第二梯度递减2，因为往下的值递增了一，右边又回退了1
                secondLength = secondLength - 2;
                sb.append(s.charAt(i) + "");
                int secondSum = secondLength + i;
                int firstSum = firstLength + i;
                while (secondSum < s.length() || firstSum < s.length()){
                    if (secondSum < s.length()){
                        sb.append(s.charAt(secondSum) + "");
                        // 无论是那个，相差的差距都是第一个梯度，只是初始值不一样
                        secondSum = secondSum + firstLength;
                    }
                    if (firstSum < s.length()){
                        sb.append(s.charAt(firstSum) + "");
                        firstSum = firstSum + firstLength;
                    }
                }
            }
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}