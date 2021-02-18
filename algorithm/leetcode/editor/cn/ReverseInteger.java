//给你一个 32 位的有符号整数 x ，返回 x 中每位上的数字反转后的结果。 
//
// 如果反转后整数超过 32 位的有符号整数的范围 [−231, 231 − 1] ，就返回 0。 
//假设环境不允许存储 64 位整数（有符号或无符号）。
//
// 
//
// 示例 1： 
//
// 
//输入：x = 123
//输出：321
// 
//
// 示例 2： 
//
// 
//输入：x = -123
//输出：-321
// 
//
// 示例 3： 
//
// 
//输入：x = 120
//输出：21
// 
//
// 示例 4： 
//
// 
//输入：x = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// -231 <= x <= 231 - 1 
// 
// Related Topics 数学 
// 👍 2533 👎 0

package cn;
/**
* @Description: 整数反转-Java
* @Author: HyJan
* @Date: 2021-02-18 15:42:02
**/

public class ReverseInteger{
    public static void main(String[] args) {
       Solution solution = new ReverseInteger().new Solution();
//        int reverse = Solution.reverse(900000);
//        System.out.println(reverse);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public  int reverse(int x) {
        if (x == 0 || x < Integer.MIN_VALUE || x > Integer.MAX_VALUE){
            return 0;
        }
        StringBuilder result = new StringBuilder();

        String str = String.valueOf(x);
        // 新建一个数组来存储每个数字
//        char[] resultArr = new char[str.length()];
//        int i = 0, j = str.length() - 1;
        // 判断正负数
        if (str.charAt(0) == '-'){
            result.append("-");
//            resultArr[0] = '-';
//            i ++;
            str = str.substring(1);
        }

        // 使用暴力方式，倒读,这种反而更快一点~
        for (int i =str.length() - 1; i >= 0; i--){
            // 判断是不是第一个零
            if (str.charAt(i) == '0' && (result.length() <= 0 || (result.length() == 1 && result.toString().contains("-")))){
                // 如果是零则直接放弃
                continue;
            }
            result.append(str.charAt(i));
        }

        // 计算首位开始，连续为零的个数长度
//        int count = 0;
//        boolean isZero = true;
//        // 使用交换的方式
//        while (i <= j && i >= 0 && j <= str.length()){
//            if (isZero && str.charAt(i) == '0'){
//                count ++;
//            }else {
//                isZero = false;
//            }
//
//            // 交换位置，装进数据
//            resultArr[i] = str.charAt(j);
//            resultArr[j] = str.charAt(i);
//            i ++;
//            j --;
//        }

//        String string = String.valueOf(resultArr);
//        System.out.println(string);
//        if (string.contains("-")){
//            string = string.substring(0,1) + string.substring(1 + count);
//        }else {
//            string = string.substring(count);
//        }

        // 翻转后越界处理
        Integer integer = null;
        try {
            integer = Integer.valueOf(result.toString());
//            integer = Integer.valueOf(string);
        }catch (NumberFormatException e){
            integer = 0;
        }
        return integer;




    }
}
//leetcode submit region end(Prohibit modification and deletion)

}