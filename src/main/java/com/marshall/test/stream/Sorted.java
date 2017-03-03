package com.marshall.test.stream;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yaojie.hou on 2017/2/27.
 * Stream流之排序
 */
public class Sorted {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("2", "23333");
        map.put("5", "排序测试");
        map.put("1", "测试测试");
        list.add(map);
        List<Object> list1 = list.stream().sorted((o1, o2) -> 1).collect(Collectors.toList());
        System.out.println(list1);
        System.out.println(list);
    }
}
