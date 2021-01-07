package head.first.factories;

import head.first.ingredients.cheese.Cheese;
import head.first.ingredients.cheese.MozzarellaCheese;
import head.first.ingredients.clams.Clams;
import head.first.ingredients.clams.FrozenClams;
import head.first.ingredients.dough.Dough;
import head.first.ingredients.dough.ThickCrustDough;
import head.first.ingredients.pepperoni.Pepperoni;
import head.first.ingredients.pepperoni.SlicedPepperoni;
import head.first.ingredients.sauce.PlumTomatoSauce;
import head.first.ingredients.sauce.Sauce;
import head.first.ingredients.veggies.BlackOlives;
import head.first.ingredients.veggies.EggPlant;
import head.first.ingredients.veggies.Spinach;
import head.first.ingredients.veggies.Veggies;

/**
 * Конкретная фабрика ингредиентов Чикаго
 */
public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {

    @Override
    public Dough createDough() {
        return new ThickCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new PlumTomatoSauce();
    }

    @Override
    public Cheese createCheese() {
        return new MozzarellaCheese();
    }

    @Override
    public Veggies[] createVeggies() {
        return new Veggies[] {new BlackOlives(), new Spinach(), new EggPlant()};
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clams createClam() {
        return new FrozenClams();
    }
}
