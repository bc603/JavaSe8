package ch01.methodreference;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 생성자 레퍼런스는 메서드의 이름이 new라는 점을 제외하면 메서드 레퍼런스와 유사하다
 * Button::new는 Button생서자를 가리키는 레퍼런스다
 * 가르키는 생성자는 문맥에 따라 다르다.
 */
public class ConstructorReference {

    public static void main(String[] args) {
        List<String> labels = Arrays.asList("Apple", "Android", "Blackberry", "pm");
        // Button 생성자는 여러 개지만, 컴파일러는 생서자가 호출되는 문맥으로 부터 추정해서
        // String 파라미터 한 개를 받는 생성자를 선택한다.
        Stream<Button> stream = labels.stream().map(Button::new);
        List<Button> buttons = stream.collect(Collectors.toList());

        //배열 타입으로도 생서자 레퍼런스를 만들수 있다.
        // int[]::new 파라미터가 한개인 생성자 레퍼런스, x -> new int[x] 에 해당

        // ????
        // 배열 생성자 레퍼런스는 자바의 한계를 극복하는데 유용하다.
        // 자바에서는 제너릭 타입T의 배열을 생성할 수 없다.
        // 표현식 new T[n]은 new Object[n]으로 소거되기 때문에 오류다.
        //
        // 버튼의 배열을 원한다고 할때
        // Stream 인터페이스는 Object 배열을 리턴하는 toArray 메서드를 포함한다.
        Object[] buttons1 = stream.toArray();

        // 사용자는 Object의 배열이 아니라 버튼의 배열을 원한다
        // 스트림 라이브러리는 이 문제는 생성자 레퍼런스를 이용해 해결한다.
        // toArray메서드에 Button[]::new 를 전달
        Button[] buttons3 = stream.toArray(Button[]::new);
        // toArray 메서드는 이 생성자를 호출해 올바른 타입의 배열을 얻는다.
        // 그리고 나서 해당 배열을 채워넣어 리턴한다.
    }
}
