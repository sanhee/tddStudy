package chap07.register;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class UserRegisterMockTest {
    private UserRegister userRegister;

    @Mock
    private WeakPasswordChecker mockWeakPasswordChecker;
    private UserRepository fakeRepository = new MemoryUserRepository();
    @Mock
    private EmailNotifier mockEmailNotifier;

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockWeakPasswordChecker, fakeRepository, mockEmailNotifier);
    }

    @Test
    void 약한_암호면_가입_실패() {
        given(mockWeakPasswordChecker.checkPasswordWeak("pw"))
                .willReturn(true);

        assertThrows(WeakPasswordException.class, () -> userRegister.register("id", "pw", "email"));
    }

    // 모의 객체가 기대한 대로 불렸는지 행위를 검증하는 코드
    @Test
    void 회원가입시_암호_검사_수행함() {
        userRegister.register("id", "pw", "email");

        then(mockWeakPasswordChecker)
                .should()
                .checkPasswordWeak(anyString());
    }

    // 모의 객체의 메서드를 호출할 때 전달한 인자를 구하는 코드
    @Test
    void 가입하면_메일을_전송함() {
        userRegister.register("id", "pw", "email");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        then(mockEmailNotifier)
                .should()
                .sendEmail(captor.capture());

        String realEmail = captor.getValue();

        assertEquals("email", realEmail);
    }
}
