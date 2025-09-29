// YOURCODE 
// Lab2 추가 기능: 최소 길이 필터를 적용하는 Normalizer
// 짧은 단어(불용어 등)를 제거하여 의미 있는 단어만 추출하는 용도
// 예: "a", "is", "to" 등의 짧은 단어 필터링
public class MinLengthNormalizer implements ITextNormalizer {
    private final int minLength;  // 최소 길이 기준값

    // 파라미터가 있는 생성자: 원하는 최소 길이 설정 가능
    public MinLengthNormalizer(int minLength) {
        this.minLength = minLength;
    }

    // 기본 생성자: 3글자를 기본 최소 길이로 설정
    // 대부분의 영어 불용어가 2글자 이하이므로 3글자 이상만 통과
    public MinLengthNormalizer() {
        this(3);  // 기본값 3글자
    }

    @Override
    public String normalize(String raw) {
        // null 체크
        if (raw == null) return "";

        // 핵심 로직: 길이 필터링
        // 단어 길이가 최소 길이보다 짧으면 빈 문자열 반환 (필터링)
        // 예: minLength=3일 때, "is"(2글자) → "", "the"(3글자) → "the"
        if (raw.length() < minLength) {
            return "";  // 짧은 단어는 제거
        }

        // 최소 길이를 충족하는 단어는 그대로 반환
        return raw;
    }
}