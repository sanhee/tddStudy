package chap07.register;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterTest {
    private UserRegister userRegister;
    private StubWeakPasswordChecker stubPasswordChecker = new StubWeakPasswordChecker();
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

    @BeforeEach
    void setUp(){
        userRegister = new UserRegister(stubPasswordChecker, fakeRepository, spyEmailNotifier);
    }

    @Test
    void 약한_암호면_가입_실패(){
        // 암호가 약한 상황 흉내
        stubPasswordChecker.setWeak(true);

        assertThrows(WeakPasswordException.class, ()->{
            userRegister.register("id","pw","email");
        });
    }

    @Test
    void 이미_같은_ID가_존재하면_가입_실패(){
        fakeRepository.save(new User("id", "pw1", "email@email.com"));

        assertThrows(DupIdException.class, () -> {
           userRegister.register("id","pw2","email");
        });
    }

    @Test
    void 같은_ID가_없으면_가입_성공(){
        userRegister.register("noeul","password","68936@naver.com");
        User savedUser = fakeRepository.findById("noeul");

        assertAll(
                ()-> assertEquals("noeul", savedUser.getId()),
                ()-> assertEquals("68936@naver.com", savedUser.getEmail())
        );
    }

    @Test
    void 회원가입에_성공하면_이메일을_보냄(){

        userRegister.register("산희","1234","68936@naver.com");

        assertAll(
                () -> assertTrue(spyEmailNotifier.isCalled()),
                () -> assertEquals("68936@naver.com",spyEmailNotifier.getEmail())
        );
    }

}
