package ru.alfabank.streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** Построчное чтение ввода с клавиатуры */
public class StringBR {

    public static void main(String[] args) {

        String str = "";
        // Считываю символы
        InputStreamReader isr = new InputStreamReader(System.in);
        // Буферизую символы
        BufferedReader br = new BufferedReader(isr);

        System.out.println("Введите строку.(exit - выход)");
        do {
            try {
                // читаю построчно
                str = br.readLine();
                System.out.println(str);
            } catch (IOException e) {
                System.out.println("Ошибка чтения");
                e.printStackTrace();
            }
        } while (!str.equals("exit"));
    }
}
