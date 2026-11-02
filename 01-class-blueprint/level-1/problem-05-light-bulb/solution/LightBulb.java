public class LightBulb {

    private boolean isOn = false;

    public void turnOn() { isOn = true; }

    public void turnOff() { isOn = false; }

    public void toggle() { isOn = !isOn; }

    public boolean isOn() { return isOn; }

    public boolean isValid() { return true; }
}
