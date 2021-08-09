package head.first.equipment;

public class Light {

    private final String value;

    public Light(String value) {
        this.value = value;
    }

    public void on() {
        System.out.println("Light is On");
    }

    public void off() {
        System.out.println("Light is Off");
    }
}
