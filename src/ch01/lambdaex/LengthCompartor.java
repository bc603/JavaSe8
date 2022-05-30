package ch01.lambdaex;

import java.util.Comparator;

public class LengthCompartor implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        //코드 블럭을 어딘가에 전달한다, 그러면 해당코드가 얼마 후에 호출된다.
        //Anonymous 인스턴스를 사용하는 이벤트 처리도 같음
        int result = Integer.compare(o1.length(), o2.length());
        return result;
    }

}
