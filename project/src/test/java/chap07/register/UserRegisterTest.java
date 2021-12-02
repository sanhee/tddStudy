package chap07.register;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserRegisterTest {
    private UserRegister userRegister;
    private StubWeakPasswordChecker stubPasswordChecker = new StubWeakPasswordChecker();

    @BeforeEach
    void setUp(){
        userRegister = new UserRegister(stubPasswordChecker);
    }

    @Test
    void 약한_암호면_가입_실패(){
        // 암호가 약한 상황 흉내
        stubPasswordChecker.setWeak(true);

        assertThrows(WeakPasswordException.class, ()->{
            userRegister.register("id","pw","email");
        });
    }
}
