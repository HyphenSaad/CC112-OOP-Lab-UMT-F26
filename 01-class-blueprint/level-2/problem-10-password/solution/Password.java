import chirp.text.Check;
import chirp.text.Text;

public class Password {

    private String value = "";

    public String getValue() { return value; }

    public void setValue(String value) {
        if (Check.notNull(value)) {
            this.value = value;
        }
    }

    public int length() {
        return value.length();
    }

    public boolean hasDigit() {
        return Check.containsDigit(value);
    }

    public boolean isStrong() {
        return Check.minLength(value, 8) && hasDigit();
    }

    public String masked() {
        return Text.mask(value, 0);
    }

    public boolean isValid() {
        return Check.notNull(value);
    }
}
