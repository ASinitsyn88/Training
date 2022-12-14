package ru.myapp.algorithms.selection_search;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class SelectionSortTest {

    @Test
    public void selectionSortTest() {
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 3, 6, 2, 10));
        List<Integer> sortedList = SelectionSort.selectionSort(list);
        assertEquals(sortedList.size(), 5);
        assertEquals(sortedList, Arrays.asList(2, 3, 5, 6, 10));
    }
}