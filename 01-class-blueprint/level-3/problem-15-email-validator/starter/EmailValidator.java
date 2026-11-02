// Problem 15 — EmailValidator
// Fill in the missing methods below. See README.md for requirements.

import chirp.text.Check;

public class EmailValidator {

    private String email = "";

    public String getEmail() { return email; }

    public void setEmail(String value) {
        // TODO: if Check.notNull(value), store value.trim()
    }

    public boolean isValidEmail() {
        // TODO: return Check.isEmail(email)
        return false;
    }

    public String normalize() {
        // TODO: find @ index; if none, return email as-is
        // else: localPart + "@" + (everything after @ lowercased)
        return "";
    }

    public String domain() {
        // TODO: if !isValidEmail() return "";
        // else: return substring after @
        return "";
    }

    public boolean isValid() {
        // TODO: return Check.notNull(email)
        return false;
    }
}
