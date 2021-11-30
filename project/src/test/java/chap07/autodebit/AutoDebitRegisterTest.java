package chap07.autodebit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AutoDebitRegisterTest {
    private AutoDebitRegister register;

    @BeforeEach
    void setUp(){
        CardNumberValidator validator = new CardNumberValidator();
        AutoDebitInfoRepository repository = new JpaAutoDebitInfoRepository();
        register = new AutoDebitRegister(validator, repository);
    }

    @Test
    void 유효한_카드번호(){
        // 업체에서 받은 테스트용 유효한 카드번호라고 가정
        AutoDebitReq req = new AutoDebitReq("noeul", "12345678");
        RegisterResult result = this.register.register(req);
        assertEquals(CardValidity.VALID, result.getValidity());
    }

    @Test
    void 도난_카드번호(){
        AutoDebitReq req = new AutoDebitReq("user1", "0000000001");
        RegisterResult result = this.register.register(req);
        assertEquals(CardValidity.THEFT, result.getValidity());
    }
}
