package chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if (s.length() < 8) {
            return PasswordStrength.NORMAL;
        }

        boolean containNum = false;

        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                containNum = true;
                break;
            }
        }
        if (!containNum) {
            return PasswordStrength.NORMAL;
        }
        return PasswordStrength.STRONG;
    }
}
