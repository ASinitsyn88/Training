package ru.myapp.exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * You are given the heads of two sorted linked lists list1 and list2
 * Merge the two lists into one sorted list
 * The list should be made by splicing together the nodes of the first two lists
 * Return the head of the merged linked list
 */
public class MergeTwoSortedLists {

    public static void main(String[] args) {
        ListNode thirdRed = new ListNode(4, null);
        ListNode secondRed = new ListNode(2, thirdRed);
        ListNode firstRed = new ListNode(1, secondRed);

        ListNode thirdBlack = new ListNode(4, null);
        ListNode secondBlack = new ListNode(3, thirdBlack);
        ListNode firstBlack = new ListNode(1, secondBlack);

        ListNode resultNode = mergeTwoLists(firstRed, firstBlack);
        while (resultNode != null) {
            System.out.print(resultNode.val + " ");
            resultNode = resultNode.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {

        }
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        List<Integer> integers = new ArrayList<>();
        integers.addAll(getElements(list1));
        integers.addAll(getElements(list2));
        Collections.sort(integers);

        ListNode nextNode = null;
        for (int i = integers.size() - 1; i >= 0; i--) {
            nextNode = new ListNode(integers.get(i), nextNode);
        }
        return nextNode;
    }

    private static List<Integer> getElements(ListNode node) {
        List<Integer> list = new ArrayList<>();
        ListNode current = node;
        while (current != null) {
            list.add(current.val);
            current = current.next;
        }
        return list;
    }
}