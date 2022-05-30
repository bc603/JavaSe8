package ch01.functionalinterface;

import java.util.Arrays;
import java.util.function.BiFunction;

/*

 */
public class Sample {
    public static void main(String[] args) {
        String words[] = { "More", "than", "words"};
        // 두번째 파라미터는 단일 메서더를 갖춘 인터페이스인 Comparator의 인스턴스를 요구한다.
        // 단순히 람드를 전달하면
        Arrays.sort(words,
                (first, second) -> Integer.compare(first.length(), second.length()));
        // 내부적으로는 Arrays.sort 메서드는 Comparator<String>을 구현하는 어떤 클래스의 객체를 받는다
        // 전달받은 객체의 compare 메서드를 호출하면 람다 표현식의 몸체를 실행한다.
        // 이러한 객체와 클래스의 관리는 순전히 구현체의 몫이며,
        // 전통적으로 사용해온 이너 클래스(내부 클래스) 방식보다 훨씬 효율적일 수 있다. (??)
        // 람다 표현식을 객체가 아니라 함수로 생각하고 함수형 인터페이스에 전달할 수 있다고 인식하는 것이 가장 좋다.

        // 이렇게 인터페이스로 반환되는 점이 람다 표현식을 강력하게 만들어 주는 요인이다.
        // 문법은 짧고 단순하다.
        /*
        button.setOnAction(event ->
            System.out.println("Thanks for Click"));
         */
        // -> 이너 클래스를 사용한 방식보다 훨씬 읽기 쉽다.

        // 함수형 인터페이스로 변환이 자바에서 람다 표현식을 이용해 할 수 있는 유일한 일이다.

        // java.util.funcation 패키지에 다수의 아주 범용적인 함수형 인터페이스를 정의하고 있다.
        // BiFuction<T, U, R>은 파라미터 타입이 T와 U이고 리턴파입이 R인 함수를 나타낸다.
        // 문자열 비교 람다를 BiFunction  타입 변수에 저장할 수 있다.
        BiFunction<String, String, Integer> comp
                = (first, second) -> Integer.compare(first.length(), second.length());
        System.out.println(comp.apply("gdoo", "good").toString());


    }
}
