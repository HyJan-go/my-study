package main.com.qw.study.control;

/**
 * @program: study
 * @description: 测试java中的使用标签进行跳转，类似goto的一种实现方式
 * @author: HyJan
 * @create: 2020-04-26 09:46
 **/
public class JavaLableControlTest {

    public static void main(String[] args) {
        testLabel();
    }

    /**
     * 标签测试
     */
    public static void testLabel(){
        //定义一个外部标签,如果满足条件，可以直接出到外部，名字:  这个形式定义一个标签
        outer:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println(i +  " " + j);
                if (j == 3){
                    break outer;
                }
            }
        }
    }
}
