import java.util.List;

// Lab2 메인 클래스: Strategy 패턴을 활용한 텍스트 처리 파이프라인 데모
public class App {
    public static void main(String[] args) throws Exception {
        // 테스트용 텍스트: 다양한 문자 형태 포함
        // - 영문 대소문자, 구두점, 숫자
        // - 유니코드 문자: full-width (ＡＢＣ, ３가지), 악센트 문자 (café, naïve)
        // - 한글, 특수문자, URL 등
        String text = """
            Quick, brown   FOX!! jumps over 13 lazy dogs.
            QUICK—brown—fox;   (NFKC: "quotes", full-width ＡＢＣ, café, naïve)
            You're reading O'Reilly's book... Visit https://example.com/docs.
            Email: test@example.org, #hashtag @handle
            미나 프로그래밍—좋아요! ３가지 항목:\tone, two, three.
            """;

        // ========== 파이프라인 1 ==========
        // 전략 조합: NFKC 정규화 → 소문자/공백제거 → 정규식 토큰화
        // 목적: 유니코드 정규화와 구두점 기반 분리
        CountableWordProcessor pipeline1 = new CountableWordProcessor(
                List.of(
                    new NFKCTextNormalizer(),      // 1단계: 유니코드 정규화 (ＡＢＣ→ABC, ３→3)
                    new LowercaseTrimNormalizer()  // 2단계: 소문자 변환 및 공백 제거
                ),
                new RegexTokenizer("[\\s\\p{Punct}]+"),  // 3단계: 공백과 구두점으로 토큰 분리
                new CountableWordRepository()
        );

        // ========== 파이프라인 2 ==========
        // 전략 조합: 악센트 제거 → 어간 추출 → 공백 토큰화
        // 목적: 악센트 제거와 어간 추출을 통한 정규화
        CountableWordProcessor pipeline2 = new CountableWordProcessor(
                List.of(
                    new AsciiFoldingNormalizer(),  // 1단계: 악센트 제거 (café→cafe, naïve→naive)
                    new SimpleStemNormalizer()      // 2단계: 어간 추출 (dogs→dog, reading→read)
                ),
                new WhitespaceTokenizer(),         // 3단계: 공백으로만 토큰 분리 (구두점 포함)
                new CountableWordRepository()
        );

        // 각 파이프라인으로 텍스트 처리
        pipeline1.processText(text);
        pipeline2.processText(text);

        // 파이프라인 1 결과 출력
        System.out.println("\n[Pipeline #1] 초성별 빈도 (CountableWord 적용):");
        new ConsolePrinter().print(pipeline1.viewByInitial());

        // 파이프라인 2 결과 출력
        System.out.println("\n[Pipeline #2] 초성별 빈도 (CountableWord 적용):");
        new ConsolePrinter().print(pipeline2.viewByInitial());

        // ========== YOURCODE: 파이프라인 3 ==========
        // 추가 전략: 최소 길이 필터 적용 (3글자 이상만 통과)
        // 목적: 불용어 제거 효과 (a, is, to 등 짧은 단어 필터링)
        System.out.println("\n=== YOURCODE: 길이 필터 적용 (3글자 이상만) ===");
        CountableWordProcessor pipeline3 = new CountableWordProcessor(
                List.of(
                    new LowercaseTrimNormalizer(),   // 1단계: 소문자 변환
                    new MinLengthNormalizer(3)       // 2단계: 3글자 미만 단어 필터링 (YOURCODE)
                ),
                new RegexTokenizer("[\\s\\p{Punct}]+"),  // 3단계: 구두점 기반 토큰화
                new CountableWordRepository()
        );

        // 파이프라인 3 처리 및 결과 출력
        pipeline3.processText(text);
        System.out.println("\n[Pipeline #3] 초성별 빈도 (3글자 이상 단어만):");
        new ConsolePrinter().print(pipeline3.viewByInitial());
        // YOURCODE 효과: 's', 'o', 're' 등의 짧은 토큰이 제거됨
    }
}
