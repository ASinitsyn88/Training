package ru.myapp.exercise;

import java.util.HashMap;
import java.util.Map;

public class ValidParentheses {

    private static final HashMap<Character, Character> map = new HashMap<Character, Character>() {{
        put('(', ')');
        put('{', '}');
        put('[', ']');
    }};

    public static void main(String[] args) {
        String str = "()[]{}";
        //String str = "()";
        //String str = "([])";
        //String str = "(]";
        //String str = "([]))";
        boolean isValid = ValidParentheses.isValid(str);
        System.out.println(isValid);
    }

    public static boolean isValid(String s) {
        char[] chars = s.toCharArray();
        for (Map.Entry<Character, Character> entry : map.entrySet()) {
            boolean theSameNumberOfTimes = arePresentedTheSameNumberOfTimes(entry.getKey(), entry.getValue(), chars);
            if (!theSameNumberOfTimes) {
                return false;
            }
        }

        boolean isAllClosed = false;
        // opened char loop
        for (int i = 0; i < chars.length; i++) {
            char start = chars[i];
            Character end = map.get(start);
            if (end == null) {
                // 'start' is closed char and can't be processed
                continue;
            }

            // closed char loop
            for (int k = i; k < chars.length; k++) {
                char closed = chars[k];
                if (end == closed) {
                    isAllClosed = true;
                    break;
                }
                isAllClosed = false;
            }

            if (!isAllClosed) {
                return false;
            }
        }
        return isAllClosed;
    }

    private static boolean arePresentedTheSameNumberOfTimes(Character start, Character end, char[] chars) {
        int opened = 0;
        int closed = 0;
        for (int i = 0; i < chars.length; i++) {
            Character c = chars[i];
            if (start.equals(c)) {
                opened++;
            }
            if (end.equals(c)) {
                closed++;
            }
        }
        return opened == closed;
    }
}