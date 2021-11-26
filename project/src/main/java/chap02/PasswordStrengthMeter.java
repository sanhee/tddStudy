package chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if (s == null || s.isBlank()) {
            return PasswordStrength.INVALID;
        }
        if (s.length() < 8) {
            return PasswordStrength.NORMAL;
        }

        boolean containNum = meetsContainingNumberCriteria(s);
        boolean containUppercase = meetsContainingUppercaseCriteria(s);

        if (!containNum || !containUppercase) {
            return PasswordStrength.NORMAL;
        }
        return PasswordStrength.STRONG;
    }

    private boolean meetsContainingNumberCriteria(String s) {
        boolean containNum = false;

        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                containNum = true;
                break;
            }
        }
        return containNum;
    }

    private boolean meetsContainingUppercaseCriteria(String s) {
        boolean containUppercase = false;

        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                containUppercase = true;
                break;
            }
        }
        return containUppercase;
    }
}
