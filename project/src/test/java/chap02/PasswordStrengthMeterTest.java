package chap02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {
    private final PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expectResult) {
        PasswordStrength result = meter.meter(password);
        assertEquals(result, expectResult);
    }

    @Test
    void 모든_규칙을_충족하는_경우() {
        assertStrength("abc12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!@Add", PasswordStrength.STRONG);
    }

    @Test
    void 길이만_8글자_미만이고_나머지_조건은_충족하는_경우() {
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
        assertStrength("Ab12!c", PasswordStrength.NORMAL);
    }

    @Test
    void 숫자를_포함하지_않고_나머지_조건은_충족하는_경우() {
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
    }
}
