package etc.mockitoExample;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

public class GameGenMockTest {
    @Test
    void mock_메서드를_이용한_모의_객체_생성() {
        // 모의 객체 생성
        GameNumGen genMock = mock(GameNumGen.class);
        // 스텁을 정의할 모의 객체의 메서드 호출을 전달
        given(genMock.generate(GameLevel.EASY)).willReturn("123");

        String num = genMock.generate(GameLevel.EASY);
        assertEquals("123", num);
    }

    @Test
    void 특정_타입의_예외를_발생하도록_스텁_설정() {
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate(null)).willThrow(new IllegalArgumentException());

        assertThrows(
                IllegalArgumentException.class,
                () -> genMock.generate(null)
        );
    }

    @Test
    void 리턴_타입이_void인_메서드에_대해_예외_발생_예제() {
        List<String> mockList = mock(List.class);

        // BDDMockito.willThrow()
        willThrow(UnsupportedOperationException.class)
                .given(mockList) // 전달받은 모의 객체 자신을 리턴,  모의 객체 실행이 아님!!!
                .add(null); // 익셉션을 발생할 메서드 호출

        assertThrows(
                UnsupportedOperationException.class,
                () -> mockList.add(null)
        );
    }

    // ArgumentMatchers의 메서드는 내부적으로 인자의 일치 여부를 판단하기 위해
    // 내부적으로 ArgumentMatchers를 등록
    // Mockito는 한 인자라도 ArgumentMatchers를 사용해서 설정한 경우
    // 모든 인자를 ArgumentMatchers 를 이용해서 설정하도록 하고 있음
    // mockList.set(anyInt(), "코드스쿼드!!")   -- X
    @Test
    void 임의_값_일치와_정확한_값_일치가_필요한_경우_eq_메서드_사용(){
        List<String> mockList = mock(List.class);

        String expected = "짱짱";
        String value = "코드스쿼드!!";
        given(mockList.set(anyInt(), eq(value))).willReturn(expected);

        String actual = mockList.set(5, value);
        String actual2 = mockList.set(16, value);
        String actual3 = mockList.set(168, value);

        assertAll(
                ()-> assertEquals(expected, actual),
                ()-> assertEquals(expected, actual2),
                ()-> assertEquals(expected, actual3)
        );
    }

    @Test
    void 모의_객체의_메서드가_불렸는지_여부를_검증하는_코드(){
        GameNumGen genMock = mock(GameNumGen.class);
        Game game = new Game(genMock);
        game.init(GameLevel.EASY);

        then(genMock) // 메서드 호출 여부를 검증할 모의 객체 주입
                .should()  // 반드시 실행되야 하는 메소드
                //.generate(GameLevel.EASY);
                .generate(any());
    }

    @Test
    void 모의_객체가_한번만_호출된_경우(){
        GameNumGen genMock = mock(GameNumGen.class);
        genMock.generate(GameLevel.EASY);

        then(genMock)
                .should(only())
                .generate(any());
    }
}
