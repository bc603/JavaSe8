package ch01.lambdaex;


import java.util.Arrays;
import java.util.Comparator;

/**
 * 람다 표혀식 문법 예시
 */
public class UserWorker {

    //코드 블럭을 어딘가에 전달한다, 그러면 해당코드가 얼마 후에 호출된다.
    //Anonymous 인스턴스를 사용하는 이벤트 처리도 같음
    public static void main(String[] args) {
        Worker worker = new Worker();
        new Thread(worker).start();

        String strings[] = {"apple", "android", "blackberry", "pm"};
        String stringsl[] = {"apple", "android", "blackberry", "pm"};
        Arrays.sort(strings, new LengthCompartor());
        for (String a : strings) {
            System.out.println(a);
        }

        //람다식의 한 형태 파라미터, 화살표 ->, 표현식
        Arrays.sort(stringsl, (String first, String second) -> {
            if (first.length() < second.length()) return -1;
            else if (first.length() > second.length()) return 1;
            else return 0;
        });
        for (String a : stringsl) {
            System.out.println(a);
        }

        //파라미터가 없는 경우라면
        //파라미터 없는 메서드와 마찬가지로 빈 괄호를 사용한다.
        new Thread(() -> {
            for (int i = 0; i < 30; i++)
                System.out.println("lambda:" + i);
        }).start();

        //람다표현식이 파라미터 타입을 추정할 수 있는 경우에는 타입을 생략할 수 있다.
        //컴파일러가 first, second를 String 타입이라고 추정가능하다
        Comparator<String> comp = (first, second) -> Integer.compare(first.length(), second.length());
        String stringstype[] = {"apple", "android", "blackberry", "pm"};
        Arrays.sort(stringstype, comp);
        for (String a : stringstype) {
            System.out.println("lambda type:" + a);
        }

        //메서드에서 추정되는 타입 한개를 파라미터로 받으면 괄호를 생략할수 있다
//        EventHandler<ActionEvent> listener = event ->
//                System.out.println("Thanks for Click");

        //람다 표현식의 결과 타입은 지정하지 않는다.
        //결과 타입은 항상 문맥으로부터 추정된다.
        //예) int 타입의 결과를 기대하는 문맥에서 사용할수 있다.
        /*(String first, String second) ->
                Integer.compare(first.length(), second.length());*/

    }
}