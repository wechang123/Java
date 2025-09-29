import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// SRP CountableWordProcessor는 서비스처리만 담당
// DIP 상위 수준(서비스)이 하위 구현에 의존하지 않고 추상에 의존 CountableWordProcessor는 ITokenizer, INormalizer, CountableWordRepository(추상 저장소로 바꿔도 동일)만 본다.
// 구체 구현을 생성/주입(DI)하므로 테스트에서 스텁/목을 주입하기 쉽다.
public class CountableWordProcessor {
    private final ITokenizer tokenizer;
    private final List<ITextNormalizer> normalizers; // Lab2 수정: 단일 normalizer → List<ITextNormalizer>로 변경
    private final CountableWordRepository store;

    // Lab2 수정: 생성자 파라미터 순서 변경 및 여러 normalizer 받도록 수정
    // List<ITextNormalizer>를 첫 번째 파라미터로 받아 여러 정규화 전략을 순차적으로 적용 가능
    CountableWordProcessor(List<ITextNormalizer> normalizers, ITokenizer tokenizer, CountableWordRepository store) {
        this.normalizers = Objects.requireNonNull(normalizers); // 여러 정규화 전략을 리스트로 저장
        this.tokenizer = Objects.requireNonNull(tokenizer); // 토큰화 전략
        this.store = Objects.requireNonNull(store); // 단어 저장소
    }

    public void process(String path) throws IOException {
        String text = Files.readString(Path.of(path));
        processText(text);
    }

    // Lab2 핵심 수정: 여러 normalizer를 순차적으로 적용하는 로직
    public void processText(String text) {
        // 1단계: tokenizer로 텍스트를 토큰 단위로 분리
        for (String tok : tokenizer.tokenize(text)) {
            String norm = tok;
            // 2단계: 각 토큰에 대해 모든 normalizer를 순차적으로 적용
            for (ITextNormalizer normalizer : normalizers) {
                norm = normalizer.normalize(norm); // 이전 결과에 다음 normalizer 적용 (파이프라인 패턴)
            }
            // 3단계: 최종 정규화된 문자열이 비어있지 않으면 저장소에 추가
            if (!norm.isEmpty()) store.addOne(norm);
        }
    }

    public void addOne(String raw) {
        String norm = raw;
        for (ITextNormalizer normalizer : normalizers) {
            norm = normalizer.normalize(norm);
        }
        if (!norm.isEmpty()) store.addOne(norm);
    }

    public void removeOne(String raw) {
        String norm = raw;
        for (ITextNormalizer normalizer : normalizers) {
            norm = normalizer.normalize(norm);
        }
        if (!norm.isEmpty()) store.removeOne(norm);
    }

    public Map<Character, List<CountableWord>> viewByInitial() {
        return store.viewByInitial();
    }
}
