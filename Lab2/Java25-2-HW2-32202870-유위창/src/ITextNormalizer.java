// ISP ITokenizer와 INormalizer는 매우 작은 인터페이스
// LSP 상위 타입(인터페이스) 자리에 하위 타입(구현체)을 대입해도 정상 동작
public interface ITextNormalizer {
    String normalize(String raw);
}
