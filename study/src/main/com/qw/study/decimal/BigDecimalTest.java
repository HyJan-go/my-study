package main.com.qw.study.decimal;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @program: study
 * @description: 学习java 8 高精度计算方式
 * 出现的原因，主要有两个，一个是高精度，一个是对于spring类型的高精度
 * 如果
 * @author: HyJan
 * @create: 2020-04-14 17:39
 **/
public class BigDecimalTest {

    public static void main(String[] args) {
        testItsContruct();

        testItsCalculation(new BigDecimal(5), new BigDecimal(2));

        testCompare(new BigDecimal("23"), new BigDecimal("25"));

        testBigDecimalFormat();
    }

    /**
     * 测试BigDecimal的构造函数
     * <p>
     * 参数类型为double或者是float的构造方法的结果有一定的不可预知性。
     */
    public static void testItsContruct() {
        BigDecimal bigDecimal = new BigDecimal(1);
        System.out.println("整型的测试结果 : " + bigDecimal);
        BigDecimal decimal = new BigDecimal(0.12f);
        System.out.println("浮点型的测试结果为 ：" + decimal);
        BigDecimal bigDecimal1 = new BigDecimal(0.26d);
        System.out.println("double 型的测试结果为 ：" + bigDecimal1);
        BigDecimal bigDecimal2 = new BigDecimal("0.256");
        System.out.println("string 类型的测试结果为 ：" + bigDecimal2);
        BigDecimal bigDecimal3 = new BigDecimal(1L); //long 不能为小数
        System.out.println("Long 类型测试结果为 :" + bigDecimal3);
    }


    /**
     * 测试 BigDecimal 的计算方法
     *
     * @param a
     * @param b
     */
    public static void testItsCalculation(BigDecimal a, BigDecimal b) {
        //加法
        System.out.println("加法结果：" + a.add(b));
        //减法
        System.out.println("减法结果 ：" + a.subtract(b));
        //乘法
        System.out.println("乘法结果 ：" + a.multiply(b));
        //除法,当除不尽的时候，这个方法是会报错的 java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
        //解决办法，是传入第二个参数，保留多少个小数位。
        System.out.println("除法结果 ：" + a.divide(b));
        //转换成string类型
        System.out.println("a 转换成string类型的结果为 ：" + a.toString());
        //转换成double类型
        System.out.println("a 转换成double类型的结果为 : " + a.doubleValue());
        //转换成float类型
        System.out.println("a 转换成float类型的结果为 : " + a.floatValue());
        //转化成long类型
        System.out.println("a 转换成long类型的结果为 ： " + a.longValue());
        //转化成int类型
        System.out.println("a 转化成int类型的结果为 :" + a.intValue());
    }

    /**
     * 测试 BigDecimal 的大小比较
     *
     * @param a
     * @param b
     */
    public static void testCompare(BigDecimal a, BigDecimal b) {
        int i = a.compareTo(b);
        if (i == 1) {
            System.out.println("参数 a 大于 参数 b");
        } else if (i == 0) {
            System.out.println("参数 a 等于 参数 b");
        } else {
            System.out.println("参数 a 小于 参数 b");
        }
    }

    /**
     * 测试BigDecimal格式化
     */
    public static void testBigDecimalFormat() {
        //建立货币格式化引用
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        //建立百分比格式化引用
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        //百分比小数点最多3位,会采用四舍五入的方式，如果超过的话
        currencyInstance.setMaximumFractionDigits(3);

        //贷款金额
        BigDecimal loanAmount = new BigDecimal("15000.1569");
        //利率
        BigDecimal interestRate = new BigDecimal("0.008");
        //相乘
        BigDecimal interest = loanAmount.multiply(interestRate);

        System.out.println("贷款金额：\t" + currencyInstance.format(loanAmount));
        System.out.println("贷款利率：\t" + percentInstance.format(interestRate));
        System.out.println("利息:\t" + currencyInstance.format(interest));
    }

    /**
     * @param obj 传入的小数
     * @return
     * @desc 1.0~1之间的BigDecimal小数，格式化后失去前面的0,则前面直接加上0。
     * 2.传入的参数等于0，则直接返回字符串"0.00"
     * 3.大于1的小数，直接格式化返回字符串
     */
    public static String formatToNumber(BigDecimal obj) {
        DecimalFormat df = new DecimalFormat("#.00");
        if (obj.compareTo(BigDecimal.ZERO) == 0) {
            return "0.00";
        } else if (obj.compareTo(BigDecimal.ZERO) > 0 && obj.compareTo(new BigDecimal(1)) < 0) {
            return "0" + df.format(obj).toString();
        } else {
            return df.format(obj).toString();
        }
    }
}
