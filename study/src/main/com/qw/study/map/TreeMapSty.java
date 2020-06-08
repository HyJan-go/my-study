package main.com.qw.study.map;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @program: study
 * @description: TreeMap 学习
 * 特点:
 * 不允许出现重复的key
 * 可以插入null键，null值
 * 可以对元素进行排序，默认就是自然排序的
 * 无序集合（插入和遍历顺序不一致）
 *
 *  api 地址: https://www.matools.com/api/java8
 * @author: HyJan
 * @create: 2020-06-05 10:22
 **/
public class TreeMapSty {
    private static TreeMap map;

    static {
        map = new TreeMap();
        map.put("age","29");
        map.put("weight","42");
        map.put("money","15982");
        map.put("height","175");
    }

    public static void main(String[] args) {
        // 删除所有映射
//        map.clear();
        // 返回与大于或等于给定键的最小键相关联的键值映射，如果没有此键，则 null 。返回的是对应的键值试图（key=value）
        System.out.println(map.ceilingEntry("age"));
        System.out.println("=================================");
        // 返回大于或等于给定键的 key键，如果没有此键，则返回 null 。
        System.out.println(map.ceilingKey("age"));
        System.out.println("=================================");
        // 返回与小于或等于给定键的最大键相关联的键值映射，如果没有此键，则 null 。
        System.out.println(map.floorEntry("age"));
        System.out.println("=================================");
        // 返回小于或等于给定键的最大键，如果没有这样的键，则返回 null 。
        System.out.println(map.floorKey("age"));
        System.out.println("=================================");
        //  TreeMap实例的浅拷贝
        Object clone = map.clone();
        System.out.println(clone.toString());
        System.out.println("=================================");
        // 遍历treeMap
        Set<Map.Entry<String,Object>> set = map.entrySet();
        set.forEach(System.out::print);
        System.out.println();
        System.out.println("=================================");
        // 返回用于订购此地图中的键的比较器，或null如果此地图使用其键的natural ordering
        System.out.println(map.comparator());
        System.out.println("=================================");
        // 判断map中是否包含某个key或者是value
        System.out.println(map.containsKey("age") + ":" + map.containsValue("age"));
        System.out.println("=================================");
        // 返回此地图中包含的键的相反顺序NavigableSet 。(返回反自然排序的所有key值)
        System.out.println(map.descendingKeySet().toString());
        System.out.println("=================================");
        // 返回此映射中包含的映射的反向排序视图。(获取与entrySet相反的顺序结果)
        System.out.println(map.descendingMap());
        System.out.println("=================================");
        // map中排序后的第一个键值对
        System.out.println(map.firstEntry());
        System.out.println("=================================");
        // map中排序后的第一个key和最后一个key
        System.out.println(map.firstKey());
        System.out.println("=================================");
        // 返回此地图部分的视图，其密钥严格小于 toKey
        System.out.println(map.headMap("gender"));
        System.out.println("=================================");
        // 返回map中所有的key
        System.out.println(map.keySet());
        System.out.println("=================================");
        // 返回与该地图中最大关键字关联的键值映射，如果地图为空，则返回 null, 貌似就是排序在最后的那个
        System.out.println(map.lastEntry());
        System.out.println("=================================");
        // 返回当前在此地图中的最后（最高）键。貌似就是排序在最后的那个key
        System.out.println(map.lastKey());
        System.out.println("=================================");
        // 删除并返回与该地图中的最小键相关联的键值映射，如果地图为空，则返回 null
        System.out.println(map.pollFirstEntry());
        // 删除并返回与该地图中的最大键相关联的键值映射，如果地图为空，则返回 null
        System.out.println(map.pollLastEntry());
        System.out.println("=================================");
        // 返回此地图部分的视图，其关键字范围从 fromKey （含）到 toKey ，独占。
        System.out.println(map.subMap("age","gender"));
    }
}
