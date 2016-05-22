package ru.alfabank.oavdo.test;

import java.util.Arrays;
import java.util.Collections;

public class SortArray {

    static Integer[] arr = {1,5,2,2,7,6,9,22,11,0};

    public static void main(String[] args) {

        Arrays.sort(arr, Collections.reverseOrder());
        for(int i = 1; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
