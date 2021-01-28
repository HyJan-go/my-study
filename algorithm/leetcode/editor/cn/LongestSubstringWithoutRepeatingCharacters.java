//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 示例 4: 
//
// 
//输入: s = ""
//输出: 0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 104 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 4865 👎 0

package cn;

import java.util.HashSet;
import java.util.Set;

/**
* @Description: 无重复字符的最长子串-Java
* @Author: HyJan
* @Date: 2021-01-26 21:12:24
**/
public class LongestSubstringWithoutRepeatingCharacters{
    public static void main(String[] args) {
       Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
}
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();

        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1,maxLength = 0;

        // for循环更加能定位,每个字母开头都要判断完
        for (int i = 0; i < s.length(); ++i){
            // 如果不是第一次进来，进来都是
            if (i != 0){
                // 如果不是第一次进来，说明上一个位置可以被移除了
                set.remove(s.charAt(i - 1));
            }

            while (rk + 1 < s.length() && !set.contains(s.charAt(rk + 1))){
                // 如果子串都还没重复，添加进来
                set.add(s.charAt(rk + 1));
                // 继续判断下一个
                ++ rk;
            }

            maxLength = Math.max(maxLength, rk - i + 1);
        }
        return maxLength;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}