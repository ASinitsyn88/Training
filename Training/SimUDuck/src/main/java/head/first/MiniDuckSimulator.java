package head.first;

import head.first.behaviour.fly.FlyRocketPowered;
import head.first.dto.Duck;
import head.first.dto.MallardDuck;
import head.first.dto.ModelDuck;

/**
 * Паттерн "Стратегия"
 */
public class MiniDuckSimulator {

    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();

        Duck model = new ModelDuck();
        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}
