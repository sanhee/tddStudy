package chap02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {
    @Test
    void 모든_규칙을_충족하는_경우() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("abc12!@AB");
        assertEquals(PasswordStrength.STRONG, result);

        PasswordStrength result2 = meter.meter("abc1!@Add");
        assertEquals(PasswordStrength.STRONG, result2);
    }

    @Test
    void 길이만_8글자_미만이고_나머지_조건은_충족하는_경우() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("ab12!@A");
        assertEquals(PasswordStrength.NORMAL, result);
        PasswordStrength result2 = meter.meter("Ab12!c");
        assertEquals(PasswordStrength.NORMAL, result2);
    }
}
