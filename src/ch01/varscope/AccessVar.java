package ch01.varscope;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * 변수 유효 범위
 */
public class AccessVar {

    public static void main(String[] args) {
        repeatMessage("Hello, world", 100);
    }

    // text와 count는 람다 표현식안에 정의되어 있지 않다
    // repeatMessage의 파라미터 변수이다.
    public static void repeatMessage(String text, int count) {
        Runnable r = () -> {
          for(int i = 0; i < count; i ++) {
              System.out.println("i:" + i);
              Thread.yield();
          }
        };
        new Thread(r).start();
    }

    // 람다 표현식은 세가지로 구성된다.
    // 1. 코드 블럭
    // 2. 파라미터
    // 3. 자유 변수(파라미터도 아니고 코드 내부에도 정의되지 않은 변수)의 값 -> text, count 같은
    // 람다 표현식을 나타내는 자료 주고는 이들 변수의 값(hello, 100)을 저장해야 한다.
    // 이 경우 람다 표현식이 이들 값을 '캡쳐'했다고 말한다.
    // 예시) 람다 표현식을 단일 메서드를 갖춘 객체로 변환하고 자유 변수들의 값을 해당 객체의 인스턴스 변수에 복사한다.

    // * 자유 변수들의 값을 포함하는 코드 블록의 기술용어는 Closure(클로저)다.
    // 자바에서는 람다 표현식이 클로저다.

    // 자바에는 캡처한 값이 잘 정의되어 있음을 보장하기 위한 중요한 제약이 있다
    // 람다 표현식에서는 값이 변하지 안흔 변수만 참조할 수 있다.
    // 잘못된 참조의 예
    public static void repeatMessageWrong(String text, int count) {
        Runnable r = () -> {
            while (count > 0) {
                //count--; //캡처한 변수는 변경할 수 없다.
                // 람다 표현식에서 변수를 변경하는 작업은 스레드에 안전하지 않다.
                System.out.println(text);
                Thread.yield();
            }
        };
        new Thread(r).start();
    }

    // 람다 표현식에서 변수를 변경하는 작업은 스레드에 안전하지 않다.
    // 일련의 병행 작업이 있고, 각 작업에서 공유 카운터를 업데이트하는 경우를 생각해보자.
    public static void wrongExample() {
        int matches = 0;
        Path[] files = new Path[2];
        //for (Path p : files) {
        //    new Thread( () -> { if (p.isAbsolute()) matches++;
                //Error : Variable used in lambda expression should be final or effectively final
        //    }).start();
        //}
        // 여러쓰레드에서 이 증가 연산을 동시에 실행하면 무슨 일이 일어날지 알 수 있는 방법이 없다.
    }

    // 변경 금지는 오직 지역변수에만 해당한다.
    // 만일 matches가 람다를 감싸고 있는 클래스의 인스턴스 변수 또는 정적 변수라면 오류가 보고되지 않는다.
    public static void wrongExample2() {
        List<Path> matches = new ArrayList<>();
        Path[] files = new Path[2];
        for(Path p : files)
            new Thread(() -> {
                if (p.isAbsolute()) matches.add(p);
                // Error가 아닌 이유는
                // matches는 사실상 final이다. 초기화가 된 후 새로운 값을 전혀 대입받지 않은 변수를 말한다.
                // 여기서는 matches가 항상 같은 ArrayList를 참조한다.
                // 하지만 ArrayList 객체 자체는 변경되었기 때문에 스레드에 안전하지 않다
                // 따라서 여러 스레드에서 add를 호출하면 결과는 예측할 수 없다.
                // -> 나중에 stream을 배워서 사용하도록

            }).start();
    }
}
