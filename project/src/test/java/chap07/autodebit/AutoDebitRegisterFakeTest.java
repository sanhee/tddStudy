package chap07.autodebit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoDebitRegisterFakeTest {
    private AutoDebitRegister register;
    private StubCardNumberValidator cardNumberValidator;
    private MemoryAutoDebitInfoRepository repository;

    @BeforeEach
    void setUp(){
        cardNumberValidator = new StubCardNumberValidator();
        repository = new MemoryAutoDebitInfoRepository();
        register = new AutoDebitRegister(cardNumberValidator, repository);
    }

    @Test
    void 이미_등록된_유저일_경우_카드번호_변경(){
        repository.save(new AutoDebitInfo("노을", "111222333444", LocalDateTime.now()));

        AutoDebitReq req = new AutoDebitReq("노을", "5588");

        // result를 사용하는 곳은 없지만, register() 내에서 save()를 이용하고 있음
        RegisterResult result = this.register.register(req);

        AutoDebitInfo saved = repository.findOne(req.getUserId());
        assertEquals("5588", saved.getCardNumber());
    }

    @Test
    void 아직_등록되지_않은_새로운_정보(){
        AutoDebitReq req = new AutoDebitReq("박산희","6893");
        RegisterResult result = this.register.register(req);

        AutoDebitInfo saved = repository.findOne(req.getUserId());
        assertEquals("6893", saved.getCardNumber());
    }
}
