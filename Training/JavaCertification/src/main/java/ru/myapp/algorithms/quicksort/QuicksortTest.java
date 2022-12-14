package ru.myapp.algorithms.quicksort;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class QuicksortTest {

    @Test
    public void quicksortTest() {
        List<Integer> sortedList = Quicksort.quicksort(Arrays.asList(10, 5, 2, 3));
        assertEquals(sortedList.size(), 4);
        assertEquals(sortedList, Arrays.asList(2, 3, 5, 10));
    }
}