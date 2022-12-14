package ru.myapp.algorithms.recursion;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;

public class RecursionExampleTest {

    @Test
    public void sumTest() {
        int sum = new RecursionExample().sum(new ArrayList<>(Arrays.asList(2, 4, 6)));
        assertEquals(sum, 12);
    }

    @Test
    public void sumEmptyTest() {
        int sum = new RecursionExample().sum(new ArrayList<>());
        assertEquals(sum, 0);
    }

    @Test
    public void sumNullTest() {
        int sum = new RecursionExample().sum(null);
        assertEquals(sum, 0);
    }

    @Test
    public void countTest() {
        int count = new RecursionExample().count(new ArrayList<>(Arrays.asList(2, 4, 6)));
        assertEquals(count, 3);
    }

    @Test
    public void countEmptyTest() {
        int count = new RecursionExample().count(new ArrayList<>());
        assertEquals(count, 0);
    }

    @Test
    public void countNullTest() {
        int count = new RecursionExample().count(null);
        assertEquals(count, 0);
    }
}