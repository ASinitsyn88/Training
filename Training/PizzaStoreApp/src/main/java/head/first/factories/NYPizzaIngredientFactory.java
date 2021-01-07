package head.first.factories;

import head.first.ingredients.cheese.Cheese;
import head.first.ingredients.cheese.ReggianoCheese;
import head.first.ingredients.clams.Clams;
import head.first.ingredients.clams.FreshClams;
import head.first.ingredients.dough.Dough;
import head.first.ingredients.dough.ThinCrustDough;
import head.first.ingredients.pepperoni.Pepperoni;
import head.first.ingredients.pepperoni.SlicedPepperoni;
import head.first.ingredients.sauce.MarinaraSauce;
import head.first.ingredients.sauce.Sauce;
import head.first.ingredients.veggies.*;

/**
 * Конкретная фабрика ингредиентов Нью-Йорка
 */
public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    @Override
    public Veggies[] createVeggies() {
        return new Veggies[] {new Garlic(), new Onion(), new Mushroom(), new RedPepper()};
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clams createClam() {
        return new FreshClams();
    }
}
