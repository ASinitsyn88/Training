package head.first.ingredients.factories;

import head.first.ingredients.cheese.Cheese;
import head.first.ingredients.clams.Clams;
import head.first.ingredients.dough.Dough;
import head.first.ingredients.pepperoni.Pepperoni;
import head.first.ingredients.sauce.Sauce;
import head.first.ingredients.veggies.Veggies;

public interface PizzaIngredientFactory {
    Dough createDough();
    Sauce createSause();
    Cheese createCheese();
    Veggies[] createVeggies();
    Pepperoni createPepperoni();
    Clams createClam();
}
