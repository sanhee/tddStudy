package chap07.autodebit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AutoDebitRegisterStubTest {
    private AutoDebitRegister register;
    private StubCardNumberValidator stubValidator;
    private JpaAutoDebitInfoRepository repository;

    @BeforeEach
    void setUp(){
        stubValidator = new StubCardNumberValidator();
        repository = new JpaAutoDebitInfoRepository();
        register = new AutoDebitRegister(stubValidator, repository);
    }

    @Test
    void 유효하지_않은_카드번호(){
        // 업체에서 받은 테스트용 유효하지 않은 카드번호라고 가정
        stubValidator.setInvalidNo("12345678");

        AutoDebitReq req = new AutoDebitReq("noeul", "12345678");
        RegisterResult result = this.register.register(req);

        assertEquals(CardValidity.INVALID, result.getValidity());
    }

    @Test
    void 도난_카드번호(){
        stubValidator.setTheftNo("0000000001");

        AutoDebitReq req = new AutoDebitReq("user1", "0000000001");
        RegisterResult result = this.register.register(req);

        assertEquals(CardValidity.THEFT, result.getValidity());
    }
}
