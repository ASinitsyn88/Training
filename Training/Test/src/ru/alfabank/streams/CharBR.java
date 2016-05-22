package ru.alfabank.streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** Посимвольное чтение ввода с клавиатуры */
public class CharBR {

    public static void main(String[] args) {

        char c = 0;
        // Считываю символы
        InputStreamReader isr = new InputStreamReader(System.in);
        // Буферизую символы
        BufferedReader br = new BufferedReader(isr);

        System.out.println("Введите символы.(q - выход)");
        do {
            try {
                // читаю посимвольно
                c = (char)br.read();
                System.out.println(c);
            } catch (IOException e) {
                System.out.println("Ошибка чтения");
                e.printStackTrace();
            }
        } while (c != 'q');
    }
}
