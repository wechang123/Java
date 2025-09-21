package interfaces;

import java.util.List;

// ISP: 토큰화에 필요한 최소한의 메서드만 정의
// OCP: 새로운 토큰화 방식을 추가할 때 기존 코드 수정 없이 확장 가능
public interface IWordTokenizer {
    List<String> tokenize(String text);
}


