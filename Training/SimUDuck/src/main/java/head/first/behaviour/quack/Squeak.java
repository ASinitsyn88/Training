package head.first.behaviour.quack;

import head.first.behaviour.fly.FlyBehavior;

public class Squeak implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("Squeak");
    }
}
