package ru.myapp.algorithms.binary_search;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class BinarySearchTest {

    @Test
    public void elementIndexFoundTest() {
        List<Integer> list = Arrays.asList(1, 3, 5, 7, 9);
        int foundIndex = new BinarySearch().binarySearch(list, 3);
        Assert.assertEquals(foundIndex, 1);
    }

    @Test
    public void elementIndexNotFoundTest() {
        List<Integer> list = Arrays.asList(1, 3, 5, 7, 9);
        int foundIndex = new BinarySearch().binarySearch(list, 50);
        Assert.assertEquals(foundIndex, -1);
    }
}