package interfaces;

// ISP: 정규화에 필요한 최소한의 메서드만 정의
// OCP: 새로운 정규화 방식을 추가할 때 기존 코드 수정 없이 확장 가능
public interface IWordNormalizer {
    String normalize(String word);
}


