package main.com.qw.study.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: study
 * @description: 学习一下Hash Map
 * 特点： 无序，key可以为 null，不可以出现重复的key，hash映射存储，O(1)时间复杂度
 * @author: HyJan
 * @create: 2020-06-05 09:55
 **/
public class HashMapSty {

    private static Map map = null;

    static {
        map = new HashMap<>();
        map.put("name","dafdfa");
        map.put("age","26");
        map.put("gender","male");
        map.put("height","178");
    }

    public static void main(String[] args) {
        System.out.println(map.get("name"));
        System.out.println("-------------------");
        System.out.println(map.size());
        System.out.println("-------------------");
        System.out.println(map.containsKey("name"));
        System.out.println("-------------------");
        // 获取所有的key
        System.out.println(map.keySet().toString());
        System.out.println("-------------------");
        // 获取所有的值
        System.out.println(map.values().toString());
        System.out.println("-------------------");
        // 查看是否包含某个值
        System.out.println(map.containsValue("25"));
        System.out.println("-------------------");
        // 返回此地图中包含的映射的Set视图( [key1=value1,key2=value2] 的这种set形式)
        System.out.println(map.entrySet().toString());
        System.out.println("-------------------");
        // 获取某个key的值，如果key不存在，则返回指定的默认值
        System.out.println(map.getOrDefault("hello","world"));
        System.out.println("-------------------");
        // 判断这个map是不是一个空的map
        System.out.println(map.isEmpty());
        System.out.println("-------------------");
        // 从map中移除某一个值
        System.out.println(map.remove("age"));
        System.out.println("-------------------");
        // 只有当目标映射到某个值时，才能替换指定键的条目,替换成功返回原来的value值
        System.out.println(map.replace("gender","female"));
        // 仅当当前映射到指定的值时，才能替换指定键的条目，替换成功返回true，否则返回false
        System.out.println(map.replace("gender","male","female"));
        System.out.println("-------------------");
    }

}
