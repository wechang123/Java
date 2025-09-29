import java.text.Normalizer;

// Lab2 신규 클래스: 악센트/다이어크리틱 제거 + 공백제거 + 소문자화를 수행하는 Normalizer
// AsciiFolding: 비ASCII 문자를 ASCII로 변환 (é→e, ö→o, ñ→n 등)
public class AsciiFoldingNormalizer implements ITextNormalizer {
    @Override
    public String normalize(String raw) {
        // null 체크
        if (raw == null) return "";

        // 1단계: NFD (Normalization Form D) 정규화
        // 문자를 기본 문자와 결합 문자(다이어크리틱)로 분리
        // 예: "é" → "e" + "´" (기본문자 + 악센트)
        String normalized = Normalizer.normalize(raw, Normalizer.Form.NFD);

        // 2단계: 정규식을 사용해 모든 결합 다이어크리틱 마크 제거
        // \\p{InCombiningDiacriticalMarks}: 유니코드 결합 다이어크리틱 블록의 모든 문자
        // 예: "café" → "cafe", "naïve" → "naive"
        String withoutAccents = normalized.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        // 3단계: 앞뒤 공백 제거(trim) 및 소문자 변환(toLowerCase)
        return withoutAccents.trim().toLowerCase();
    }
}