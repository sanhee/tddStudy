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

    @Test
    void 값이_없는_경우() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    void 빈_문자열인_경우() {
        assertStrength("", PasswordStrength.INVALID);
    }

    @Test
    void 대문자를_포함하지_않고_나머지_조건을_충족하는_경우() {
        assertStrength("abcd12!@", PasswordStrength.NORMAL);
    }

    @Test
    void 길이가_8글자_이상인_조건만_충족하는_경우() {
        assertStrength("abcdefgh", PasswordStrength.WEAK);
    }
}
