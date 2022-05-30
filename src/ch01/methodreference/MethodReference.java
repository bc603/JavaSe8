package ch01.methodreference;

import java.util.Arrays;

public class MethodReference {
    public static void main(String[] args) {
        // 다른 코드에 전달할려고 하는 액션을 수행하는 메서드가 이미 존재할수도 있음
        // button.setOnAction(event -> System.out.println(event));

        // setOnAction 메서드에 println 메서드만 전달할 수 있다면 더 좋을 것이다.
        // button.setOnAction(System.out::println);

        // System.out::println 표현식은 람다 표현식 x -> System.out.println(x)에
        // 해당하는 메서드 레퍼런스(Method reference)다.

        String strings[] = { "Apple", "android", "Blackberry", "pm"};
        Arrays.sort(strings, String::compareToIgnoreCase); // compareTo와 compareToIngnoreCase 결과가 다름
        for (String a : strings) {
            System.out.println("A:" + a);
        }
        
        // ::연산자 -> 객체 또는 클래스와 메서드 이름을 구분하며 세가지 경우가 있음
        // object::instanceMathod
        // Class::staticMethod
        // Class::instanceMethod
        // 처음 두 경우에서 메서드 레퍼런스는 메서드의 파라미터를 제공하는 람다 표현식에 해당
        // System.out::println은 x -> System.out.println(x) 해당
        // 마찬가지로 Math::pow는 (x, y) -> Math(x, y)에 해당

        // 세번째 경우에서는 첫 번째 파라미터가 해당 메서드의 대상이 된다.
        // String::compareToIgnoreCase는 (x, y) -> x.compreToIgnoreCase(y)와 같이 된다.
    }
}
