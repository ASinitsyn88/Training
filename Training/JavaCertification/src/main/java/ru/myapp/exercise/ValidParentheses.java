package ru.myapp.exercise;

import java.util.HashMap;
import java.util.Stack;

public class ValidParentheses {

    private static final HashMap<Character, Character> map = new HashMap<Character, Character>() {{
        put('(', ')');
        put('{', '}');
        put('[', ']');
    }};

    public static void main(String[] args) {
        //String str = "()()";
        //String str = "()[]{}";
        //String str = "()";
        String str = "([])";
        //String str = "(]";
        //String str = "([]))";
        boolean isValid = ValidParentheses.isValid(str);
        System.out.println(isValid);
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
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