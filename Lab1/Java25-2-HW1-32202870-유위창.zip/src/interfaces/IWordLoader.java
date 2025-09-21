package interfaces;

import java.io.IOException;
import java.util.List;

// ISP: 파일 로딩에 필요한 최소한의 메서드만 정의
// DIP: 고수준 모듈이 저수준 구현체가 아닌 추상화에 의존
public interface IWordLoader {
    List<String> loadWords(String path) throws IOException;
}


