package head.first.factories;

import head.first.ingredients.cheese.Cheese;
import head.first.ingredients.clams.Clams;
import head.first.ingredients.dough.Dough;
import head.first.ingredients.pepperoni.Pepperoni;
import head.first.ingredients.sauce.Sauce;
import head.first.ingredients.veggies.Veggies;

/**
 * Абстрактная фабрика ингредиентов
 */
public interface PizzaIngredientFactory {
    Dough createDough();
    Sauce createSauce();
    Cheese createCheese();
    Veggies[] createVeggies();
    Pepperoni createPepperoni();
    Clams createClam();
}
