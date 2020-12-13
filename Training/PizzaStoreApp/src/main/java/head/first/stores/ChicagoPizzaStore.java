package head.first.stores;

import head.first.products.Pizza;
import head.first.products.chicago.ChicagoStyleCheesePizza;
import head.first.products.chicago.ChicagoStyleClamPizza;
import head.first.products.chicago.ChicagoStylePepperoniPizza;
import head.first.products.chicago.ChicagoStyleVeggiePizza;

public class ChicagoPizzaStore extends PizzaStore {

    @Override
    Pizza createPizza(String type) {
        if ("cheese".equals(type)) {
            return new ChicagoStyleCheesePizza();
        } else if ("veggie".equals(type)) {
            return new ChicagoStyleVeggiePizza();
        } else if ("clam".equals(type)) {
            return new ChicagoStyleClamPizza();
        } else if ("pepperoni".equals(type)) {
            return new ChicagoStylePepperoniPizza();
        } else {
            return null;
        }
    }
}
