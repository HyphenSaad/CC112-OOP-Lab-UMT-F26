import chirp.text.Check;

public class EmailValidator {

    private String email = "";

    public String getEmail() { return email; }

    public void setEmail(String value) {
        if (Check.notNull(value)) this.email = value.trim();
    }

    public boolean isValidEmail() {
        return Check.isEmail(email);
    }

    public String normalize() {
        int at = email.indexOf('@');
        if (at < 0) return email;
        return email.substring(0, at) + "@" + email.substring(at + 1).toLowerCase();
    }

    public String domain() {
        if (!isValidEmail()) return "";
        int at = email.indexOf('@');
        return email.substring(at + 1);
    }

    public boolean isValid() {
        return Check.notNull(email);
    }
}
