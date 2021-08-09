package head.first.equipment;

public class Stereo {

    private final String value;

    public Stereo(String value) {
        this.value = value;
    }

    public void on() {
        System.out.println("Stereo is On");
    }

    public void off() {
        System.out.println("Stereo is Off");
    }

    public void setCd() {
        System.out.println("Stereo is set for CD input");
    }

    public void setDvd() {
        System.out.println("Stereo is set for DVD input");
    }

    public void setRadio() {
        System.out.println("Stereo is set for Radio input");
    }

    public void setVolume(int value) {
        System.out.println("Stereo volume set to " + value);
    }
}
