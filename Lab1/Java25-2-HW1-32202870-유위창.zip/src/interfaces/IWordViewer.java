package interfaces;

import model.CountableWord;
import java.util.List;
import java.util.Map;

// ISP: 화면 출력에 필요한 최소한의 메서드만 정의
// OCP: 새로운 출력 방식(파일, GUI 등)을 추가할 때 기존 코드 수정 없이 확장 가능
public interface IWordViewer {
    void display(Map<Character, List<CountableWord>> wordMap);
}