package com.tt.study;

/**
 * @description: 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 * @author: HyJan
 * @create: 2020-08-18 14:25
 **/
public class StringIsNumber {

    /**
     * 设置三个标志符分别记录“+/-”、“e/E”和“.”是否出现过。
     *
     * 对于“+/-”： 正常来看它们第一次出现的话应该出现在字符串的第一个位置，如果它第一次出现在不是字符串首位，而且它的前面也不是“e/E”，那就不符合规则；如果是第二次出现，那么它就应该出现在“e/E”的后面，如果“+/-”的前面不是“e/E”，那也不符合规则。
     * 对于“e/E”： 如果它的后面不接任何数字，就不符合规则；如果出现多个“e/E”也不符合规则。
     * 对于“.”： 出现多个“.”是不符合规则的。还有“e/E”的字符串出现“.”也是不符合规则的。 同时，要保证其他字符均为 0-9 之间的数字。
     */
    public boolean isNumeric(char[] str) {
        int len = str.length;
        boolean sign = false, decimal = false, hasE = false;
        for(int i = 0; i < len; i++){
            if(str[i] == '+' || str[i] == '-'){
                if(!sign && i > 0 && str[i-1] != 'e' && str[i-1] != 'E')
                    return false;
                if(sign && str[i-1] != 'e' && str[i-1] != 'E')
                    return false;
                sign = true;
            }else if(str[i] == 'e' || str[i] == 'E'){
                if(i == len - 1)
                    return false;
                if(hasE)
                    return false;
                hasE = true;
            }else if(str[i] == '.'){
                if(hasE || decimal)
                    return false;
                decimal = true;
            }else if(str[i] < '0' || str[i] > '9')
                return false;
        }
        return true;
    }

}
