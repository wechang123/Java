import java.text.Normalizer;

// Lab2 신규 클래스: NFKC 유니코드 정규화를 수행하는 Normalizer
// NFKC (Normalization Form KC): 호환성 문자를 표준 문자로 변환 + 정준 분해 후 재조합
// 예: full-width 문자(ＡＢＣ) → 일반 문자(ABC), "quotes" → "quotes" 등
public class NFKCTextNormalizer implements ITextNormalizer {
    @Override
    public String normalize(String raw) {
        // null 체크: null인 경우 빈 문자열 반환
        if (raw == null) return "";

        // Java 표준 라이브러리의 Normalizer 사용
        // NFKC 형식으로 유니코드 정규화 수행
        // 예시: "３가지" → "3가지", "ＡＢＣ" → "ABC", "café" → "café"(정규화된 형태)
        return Normalizer.normalize(raw, Normalizer.Form.NFKC);
    }
}