package ch01.methodreference;

public class Greeter {
    public void greet() {
        System.out.println("Hello, World");
    }

    public static void main(String[] args) {
        ConcurrentGreeter greeter = new ConcurrentGreeter();
        greeter.greet();
    }
}

class ConcurrentGreeter extends Greeter {
    @Override
    public void greet() {
        // 스레드가 시작할때 해당 스레드의 Runnable이 호출되고 super::greete가 실행된다.
        // 이때 슈퍼클래스의 greet 메서드가 호출된다.
        Thread t = new Thread(super::greet);
        t.start();
    }

    /*
    이너 클래스에서는 바깥쪽 클래스의 this 레퍼런스를 EnclosingClass.this::method또는
    EnclosingClass.super::method로
    캡처할수 있다.
     */
}
