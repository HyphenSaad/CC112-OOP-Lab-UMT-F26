// Problem 10 — Password
// Fill in the missing methods below. See README.md for requirements.

import chirp.text.Check;
import chirp.text.Text;

public class Password {

    private String value = "";

    public String getValue() { return value; }

    public void setValue(String value) {
        // TODO: use Check.notNull(value)
    }

    public int length() {
        // TODO: return value.length()
        return 0;
    }

    public boolean hasDigit() {
        // TODO: use Check.containsDigit(value)
        return false;
    }

    public boolean isStrong() {
        // TODO: Check.minLength(value, 8) && hasDigit()
        return false;
    }

    public String masked() {
        // TODO: use Text.mask(value, 0)
        return "";
    }

    public boolean isValid() {
        // TODO: Check.notNull(value)
        return false;
    }
}
