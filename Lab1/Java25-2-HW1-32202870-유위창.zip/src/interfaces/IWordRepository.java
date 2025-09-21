package interfaces;

import model.CountableWord;
import java.util.List;
import java.util.Map;

// ISP: 저장소에 필요한 최소한의 메서드만 정의
// DIP: 구체적인 저장 방식에 의존하지 않고 추상화에 의존
public interface IWordRepository {
    void add(String word);
    void remove(String word);
    Map<Character, List<CountableWord>> getAll();
    void clear();
}