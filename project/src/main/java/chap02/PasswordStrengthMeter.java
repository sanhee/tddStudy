package chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if (s == null || s.isBlank()) {
            return PasswordStrength.INVALID;
        }

        boolean lengthEnough = s.length() >= 8;
        boolean containNum = meetsContainingNumberCriteria(s);
        boolean containUppercase = meetsContainingUppercaseCriteria(s);

        if (lengthEnough && !containNum && !containUppercase) {
            return PasswordStrength.WEAK;
        }
        if (!lengthEnough) {
            return PasswordStrength.NORMAL;
        }
        if (!containNum) {
            return PasswordStrength.NORMAL;
        }
        if (!containUppercase) {
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
