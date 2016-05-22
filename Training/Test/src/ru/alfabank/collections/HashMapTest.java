package ru.alfabank.collections;

import java.util.*;

public class HashMapTest {

    public static void main(String[] args) {

        Map<String, List<String>> map = new HashMap();
        map.put("1", new ArrayList<String>());
        map.put("2", new ArrayList<String>());
        map.put("3", new ArrayList<String>());
        map.put("4", new ArrayList<String>());
        map.put("5", new ArrayList<String>());

        map.get("1").add("Obj1");
        map.get("1").add("Obj2");

        List<String> strList = map.get("1");
        System.out.println("Size: " + strList.size());
        for (String s : strList) {
            System.out.println("Element: " + s);
        }
        Arrays.sort(strList.toArray());
    }
}
