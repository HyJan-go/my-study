//给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。 
//
// 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？ 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 示例 3： 
//
// 输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
// 
//
// 示例 4： 
//
// 输入：nums1 = [], nums2 = [1]
//输出：1.00000
// 
//
// 示例 5： 
//
// 输入：nums1 = [2], nums2 = []
//输出：2.00000
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -106 <= nums1[i], nums2[i] <= 106 
// 
// Related Topics 数组 二分查找 分治算法 
// 👍 3630 👎 0

package cn;

import java.util.Objects;

/**
* @Description: 寻找两个正序数组的中位数-Java
* @Author: HyJan
* @Date: 2021-01-28 14:16:36
**/

public class MedianOfTwoSortedArrays{
    public static void main(String[] args) {
       Solution solution = new MedianOfTwoSortedArrays().new Solution();
}
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        // 把两个数组归并成一个数组，然后通过二分查找解决问题
        int[] nums = new int[length];

        int i = 0,j = 0,k = 0;
        while (i < nums1.length || j < nums2.length){
            Integer left = i < nums1.length ? nums1[i] : null;
            Integer right = j < nums2.length ? nums2[j] : null;
            if (Objects.nonNull(left) && Objects.nonNull(right)){
                if (left < right){
                    nums[k] = left;
                    i ++;
                }else {
                    nums[k] = right;
                    j ++;
                }
            }else if (Objects.isNull(left) && Objects.nonNull(right)){
                nums[k] = right;
                j ++;
            }else if (Objects.isNull(right) && Objects.nonNull(left)){
                nums[k] = left;
                i ++;
            }
            k ++;
        }

        // 判断原来两个数组加起来的长度是奇数还是偶数,来选择是中间，还是左右值（注意下标从零开始）
        if (length % 2 == 1){
            return new Double(nums[length / 2]);
        }else {
            return (new Double(nums[length/2 - 1]) + new Double(nums[length/2])) / new Double(2);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}