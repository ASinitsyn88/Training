package head.first.ingredients.factories;

import head.first.ingredients.cheese.Cheese;
import head.first.ingredients.clams.Clams;
import head.first.ingredients.dough.Dough;
import head.first.ingredients.pepperoni.Pepperoni;
import head.first.ingredients.sauce.Sauce;
import head.first.ingredients.veggies.Veggies;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

    @Override
    public Dough createDough() {
        return null;
    }

    @Override
    public Sauce createSause() {
        return null;
    }

    @Override
    public Cheese createCheese() {
        return null;
    }

    @Override
    public Veggies[] createVeggies() {
        return new Veggies[0];
    }

    @Override
    public Pepperoni createPepperoni() {
        return null;
    }

    @Override
    public Clams createClam() {
        return null;
    }
}
