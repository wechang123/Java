import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        String text = """
            Quick, brown   FOX!! jumps over 13 lazy dogs.
            QUICK—brown—fox;   (NFKC: “quotes”, full-width ＡＢＣ, café, naïve)
            You're reading O'Reilly's book... Visit https://example.com/docs.
            Email: test@example.org, #hashtag @handle
            미나 프로그래밍—좋아요! ３가지 항목:\tone, two, three.
            """;

        // 파이프라인 1: (NFKC → lower+trim) → RegexTokenizer 
        CountableWordProcessor pipeline1 = new CountableWordProcessor(
                List.of(new NFKCTextNormalizer(), new LowercaseTrimNormalizer()),
                new RegexTokenizer("[\\s\\p{Punct}]+"),
                new CountableWordRepository()
        );

        // 파이프라인 2: (AsciiFolding + SimpleStem) → WhitespaceTokenizer 
        CountableWordProcessor pipeline2 = new CountableWordProcessor(
                List.of(new AsciiFoldingNormalizer(), new SimpleStemNormalizer()),
                new WhitespaceTokenizer(),
                new CountableWordRepository()
        );

        pipeline1.processText(text);
        pipeline2.processText(text);

        // === CountableWord 적용: 파이프라인1 결과를 집계 ===
        System.out.println("\n[Pipeline #1] 초성별 빈도 (CountableWord 적용):");
        new ConsolePrinter().print(pipeline1.viewByInitial());

        // === CountableWord 적용: 파이프라인2 결과를 집계 ===
        System.out.println("\n[Pipeline #2] 초성별 빈도 (CountableWord 적용):");
        new ConsolePrinter().print(pipeline2.viewByInitial());
    }
}
