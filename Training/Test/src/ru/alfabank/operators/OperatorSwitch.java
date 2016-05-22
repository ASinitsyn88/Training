package ru.alfabank.operators;

public class OperatorSwitch {

    public static void main(String[] args) {

        String[] monthArr = {"январь","февраль","март","апрель","май","июнь",
                "июль","август","сентябрь","октябрь","ноябрь","декабрь"};

        for (String str : monthArr) {
            switch (str) {
                case "декабрь" : System.out.println(str + " это зима");
                    break;
                case "январь" : System.out.println(str + " это зима");
                    break;
                case "февраль" : System.out.println(str + " это зима");
                    break;
                case "март" : System.out.println(str + " это весна");
                    break;
                case "апрель" : System.out.println(str + " это весна");
                    break;
                case "май" : System.out.println(str + " это весна");
                    break;
                case "июнь" : System.out.println(str + " это лето");
                    break;
                case "июль" : System.out.println(str + " это лето");
                    break;
                case "август" : System.out.println(str + " это лето");
                    break;
                case "сентябрь" : System.out.println(str + " это осень");
                    break;
                case "октябрь" : System.out.println(str + " это осень");
                    break;
                case "ноябрь" : System.out.println(str + " это осень");
                    break;
            }
        }
    }
}
