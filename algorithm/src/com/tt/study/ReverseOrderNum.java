package com.tt.study;

import java.util.Objects;

/**
 * @program: algorithm
 * @description: 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。
 * 并将P对1000000007取模的结果输出。 即输出P%1000000007
 * 输入描述:
 * 题目保证输入的数组中没有的相同的数字
 * 数据范围：
 * <p>
 * 对于%50的数据,size<=10^4
 * 对于%75的数据,size<=10^5
 * 对于%100的数据,size<=2*10^5
 * <p>
 * 示例1
 * 输入
 * 1,2,3,4,5,6,7,0
 * 输出
 * 7
 * @author: HyJan
 * @create: 2020-07-27 15:48
 **/
public class ReverseOrderNum {

    private static int count = 0;

    /**
     * 求逆序对的个数
     *
     * @param array
     * @return
     */
    public static int InversePairs(int[] array) {
        // 判断输入
        if (Objects.isNull(array) || array.length < 2) {
            return count;
        }
        int[] tmp = new int[array.length];
        split(array, 0, array.length - 1, tmp);
        return count;
    }

    /**
     * 归并的拆解过程
     *
     * @param array
     * @param start
     * @param end
     * @param tmp
     */
    public static void split(int[] array, int start, int end, int[] tmp) {
        // 分隔到只有一个值的时候就不需要再分隔了
        if (start >= end) {
            return;
        }
        // 位运算的优先级是比较低的
        int mid = start + ((end - start) >> 1);
        split(array, start, mid, tmp);
        split(array, mid+1, end, tmp);

        // 当上面的拆解完成之后进入归并过程
        merge(array, start, mid, end, tmp);
    }

    /**
     * 归并排序的归并过程
     *
     * @param array 原数组
     * @param start 开始计算位置
     * @param mid   第一段数组的结束位置
     * @param end   第二段数组的结束位置
     * @param tmp   临时辅助数组
     */
    public static void merge(int[] array, int start, int mid, int end, int[] tmp) {
        int tmpIndex = start;
        int start2 = mid + 1;
        int i = start, j = start2;
        while (i <= mid && j <= end) {
            // 这里的i和j不能交换，因为逆序后的排序后就应该是逆序的，因为下面的tmp还是要赋值回array的，所以这里不是想正序或者逆序都行
            if (array[i] > array[j]) {
                tmp[tmpIndex++] = array[i++];
                count = (count + end - j + 1) % 1000000007;
            } else {
                tmp[tmpIndex++] = array[j++];
            }
        }
        // 循环完之后，将末尾的有序的补充进排序数组中
        if (i <= mid) {
            // 系统自带的数组复制，第一个是从哪个数组开始复制，从哪个位置开始，复制的长度是多少
            System.arraycopy(array,i,tmp,tmpIndex,mid - i + 1);
        }

        if (j <= end) {
           System.arraycopy(array,j,tmp, tmpIndex,end-j + 1);
        }

        // 要理解清楚，归并后的位置肯定是连续的，然后还要将tmp里面的位置值复制回array中，不然比较了就没有意义
        System.arraycopy(tmp,start,array,start,end - start + 1);

    }

    public static void main(String[] args) {
        int[] array = new int[]{364,637,341,406,747,995,234,971,571,219,993,407,416,366,315,301,601,650,418,355,460,505,360,965,516,648,727,667,465,849,455,181,486,149,588,233,144,174,557,67,746,550,474,162,268,142,463,221,882,576,604,739,288,569,256,936,275,401,497,82,935,983,583,523,697,478,147,795,380,973,958,115,773,870,259,655,446,863,735,784,3,671,433,630,425,930,64,266,235,187,284,665,874,80,45,848,38,811,267,575};
        int[] array1 = new int[]{1,2,3,4,1,5,6};
        int i = InversePairs(array);
        System.out.println(i);
    }
}
