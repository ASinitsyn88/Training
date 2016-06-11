package ru.alfabank.lambda.listing11;

public class MyStringOps {

    /**
     * Статический метод для инверсии переданной строки
     * @param str
     * @return
     */
    public String strReverse(String str) {

        String result = "";
        int i;

        for (i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i);
        }
        return result;
    }
}
