package head.first.stores;

import head.first.products.Pizza;

public abstract class PizzaStore {

    /**
     * Заказ пиццы. Бизнес-процесс одинаков во всех регионах
     * @param type - тип пиццы
     * @return приготовленная и обработанная в соответствии с бизнес-процессом пицца
     */
    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    };

    /**
     * Фабричный метод. Приготовление пиццы различается в зависимости от региона
     * @param type - тип пиццы
     * @return пицца, приготовленная в соответствии с региональными стандартами
     */
    abstract Pizza createPizza(String type);
}
