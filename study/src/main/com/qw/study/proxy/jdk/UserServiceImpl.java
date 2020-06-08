package main.com.qw.study.proxy.jdk;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: study
 * @description: 实现类
 * @author: HyJan
 * @create: 2020-06-02 15:45
 **/
@Slf4j
public class UserServiceImpl implements IUserService {
    @Override
    public String get(String str) {
        System.out.println(str);
      log.info("the string is {}",str);
      return "就是";
    }
}
