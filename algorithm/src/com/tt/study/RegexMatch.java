package com.tt.study;

/**
 * @description: 请实现一个函数用来匹配包括'.'和'*'的正则表达式。
 * 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 * 貌似还有小问题，但是正则想跳过
 * @author: HyJan
 * @create: 2020-08-18 14:05
 **/
public class RegexMatch {

    public boolean match(char[] str, char[] pattern) {
        return isMatch(str, 0, pattern, 0);
    }

    public boolean isMatch(char[] str, int strIndex, char[] pattern, int patternIndex) {
        if (strIndex == str.length && patternIndex == pattern.length)   // 同时结束
            return true;
        if (patternIndex >= pattern.length)     // pattern 先结束
            return false;
        if (strIndex == str.length)     // str结束
            return false;

        // patternIndex 还没到达倒数第二位
        if (patternIndex < pattern.length - 1) {
            // 如果下一位是 *
            if (pattern[patternIndex + 1] == '*') {
                if ((strIndex < str.length) &&
                        (str[strIndex] == pattern[patternIndex] || pattern[patternIndex] == '.')) {
                    // 第一种，模式上移动两个字符，相当于忽略‘*’和它前面的字符
                    return isMatch(str, strIndex, pattern, patternIndex + 2) ||
                            // 第二种，当前字符匹配成功，模式上移动两个字符
                            isMatch(str, strIndex + 1, pattern, patternIndex + 2) ||
                            // 第三种，当前字符匹配成功，模式上保持不变
                            isMatch(str, strIndex + 1, pattern, patternIndex);
                } else
                    // 忽略'*'和它前面的字符
                    return isMatch(str, strIndex, pattern, patternIndex + 2);
            }
        }

        // 下一位不是‘*’，则只要匹配'.'，匹配成功后，同时移动一个字符
        if (str[strIndex] == pattern[patternIndex] || pattern[patternIndex] == '.')
            return isMatch(str, strIndex + 1, pattern, patternIndex + 1);

        return false;
    }
}
