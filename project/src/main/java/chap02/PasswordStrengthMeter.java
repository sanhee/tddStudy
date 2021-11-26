package chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if (s == null || s.isBlank()) {
            return PasswordStrength.INVALID;
        }

        int meterCount = 0; // 암호 조건 만족 개수
        if (s.length() >= 8) {
            meterCount++;
        }
        if (meetsContainingNumberCriteria(s)) {
            meterCount++;
        }
        if (meetsContainingUppercaseCriteria(s)) {
            meterCount++;
        }

        if (meterCount == 1) {
            return PasswordStrength.WEAK;
        }
        if (meterCount == 2) {
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
