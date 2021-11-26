# tddStudy



## 암호 검사기



- 문자열을 검사해서 규칙을 준수하는지에 따라 암호를 `약함`, `보통`, `강함`으로 구분
- 검사할 규칙은 다음 세 가지
  - 길이가 8글자 이상
  - 0부터 9 사이의 숫자를 포함
  - 대문자 포함
- 세 규칙을 모두 충족하면 암호는 `강함`
- 2개의 규칙을 충족하면 암호는 `보통`
- 1개 이하의 규칙을 충족하면 암호는 `약함`



### 첫 번째 테스트: 모든 규칙을 충족하는 경우

- 첫 번째 테스트를 선택할 때에는 가장 쉽거나 가장 예외적인 상황을 선택해야한다.



### 테스트 코드 정리

- 테스트 코드도 코드이기 때문에 유지보수 대상이다.

- 즉, 테스트 메서드에서 발생하는 중복을 알맞게 제거하거나 의미가 잘 드러나게 코드를 수정할 필요가 있다.
- 테스트 코드의 중복을 무턱대고 제거하면 안 된다. 중복을 제거한 뒤에도 **테스트 코드의 가독성이 떨어지지 않고 수정이 용이한 경우**에만 중복을 제거해야한다.
  - 중복을 제거한 뒤에 오히려 테스트 코드 관리가 어려워진다면 제거했던 중복을 되돌려야 한다.



### isBlank() vs isEmpty()

```java
public class Main 
{
    public static void main(String[] args) 
    {
        System.out.println( "ABC".isBlank() );      //false
        System.out.println( "  ".isBlank() );       //true
 
        System.out.println( "ABC".isEmpty() );      //false
        System.out.println( "  ".isEmpty() );       //false
    }
}
```

- https://howtodoinjava.com/java11/check-blank-string/





### (내가 실수한 부분 노트) 앞서서 생각하지 말자



다른 예외까지 처리하려고 하는데.. 지금 당장은 테스트케이스만 통과되도록 코드를 작성하자.

고민했던 건 아래 코드인데,

```java
if(!lengthEnough){
    return PasswordStrength.NORMAL;
}
```

**!길이 and (!숫자 또는 !대문자) 일 경우 WEAK에 대한 예외가 안돼있어서 처리해줘야 하나? 였음**



하지만, 당시 작성중이었던 테스트 코드는 `길이가 8글자 이상인 조건만 충족하는 경우` 였으므로, 

위 예외는 생각할 필요가 없음.



즉, 지금은 이 부분만 작성해주면 됨

```java
if(lengthEnough && !containNum && !containUppercase){
	return PasswordStrength.WEAK;
}
```

