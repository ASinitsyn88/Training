package ru.alfabank.exception;

public class ChainException {

    public int div(String a, String b) throws Exception {

        int total = 0;

        try {
            int first = Integer.parseInt(a);
            int second = Integer.parseInt(b);
            total = first / second;
        } catch (NumberFormatException nfe) {
            NumberFormatException e = new NumberFormatException("Верхний уровень");
            e.initCause(new NullPointerException("Передан пустой аргумент"));
            throw e;
        }

        return total;
    }
}
