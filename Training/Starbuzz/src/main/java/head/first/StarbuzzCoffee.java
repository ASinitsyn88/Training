package head.first;

import head.first.beverages.Beverage;
import head.first.beverages.DarkRoast;
import head.first.beverages.Espresso;
import head.first.beverages.HouseBlend;
import head.first.condiments.Mocha;
import head.first.condiments.Soy;
import head.first.condiments.Whip;

/**
 * Паттерн "Декоратор"
 */
public class StarbuzzCoffee {

    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        // Каждое предыдущее состояние напитка (beverage2) "декорируется" новым состоянием (добавкой к кофе)
        // DarkRoast - декорируемые объект
        // Mocha, Whip - декораторы, накладываемые на DarkRoast
        Beverage beverage2 = new DarkRoast();
        beverage2 = new Mocha(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription() + " $" + beverage2.cost());

        // Каждое предыдущее состояние напитка (beverage3) "декорируется" новым состоянием (добавкой к кофе)
        // HouseBlend - декорируемые объект
        // Soy, Mocha, Whip - декораторы, накладываемые на DarkRoast
        Beverage beverage3 = new HouseBlend();
        beverage3 = new Soy(beverage3);
        beverage3 = new Mocha(beverage3);
        beverage3 = new Whip(beverage3);
        System.out.println(beverage3.getDescription() + " $" + beverage3.cost());
    }
}
