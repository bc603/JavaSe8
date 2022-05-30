package ch01.lambdaex;

public class Worker implements Runnable {
    public void run() {
        //코드 블럭을 어딘가에 전달한다, 그러면 해당코드가 얼마 후에 호출된다.
        //Anonymous 인스턴스를 사용하는 이벤트 처리도 같음
        for (int i = 0; i < 100; i++) {
            doWork();
        }
    }

    private void doWork() {
        System.out.println("dowork");
    }
}
