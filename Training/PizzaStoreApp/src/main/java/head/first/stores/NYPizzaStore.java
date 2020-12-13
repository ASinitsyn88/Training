package head.first.stores;

import head.first.products.Pizza;
import head.first.products.ny.NYStyleCheesePizza;
import head.first.products.ny.NYStylePepperoniPizza;
import head.first.products.ny.NYStyleVeggiePizza;

public class NYPizzaStore extends PizzaStore {

    @Override
    Pizza createPizza(String type) {
        if ("cheese".equals(type)) {
            return new NYStyleCheesePizza();
        } else if ("veggie".equals(type)) {
            return new NYStyleVeggiePizza();
        } else if ("clam".equals(type)) {
            return new NYStyleVeggiePizza();
        } else if ("pepperoni".equals(type)) {
            return new NYStylePepperoniPizza();
        } else {
            return null;
        }
    }
}
