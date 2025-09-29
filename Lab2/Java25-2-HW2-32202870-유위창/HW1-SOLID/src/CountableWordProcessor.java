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
    private final ITextNormalizer normalizer;
    private final CountableWordRepository store;

    CountableWordProcessor(ITokenizer tokenizer, ITextNormalizer normalizer, CountableWordRepository store) {
        this.tokenizer = Objects.requireNonNull(tokenizer);
        this.normalizer = Objects.requireNonNull(normalizer);
        this.store = Objects.requireNonNull(store);
    }

    public void process(String path) throws IOException {
        String text = Files.readString(Path.of(path));
        processText(text);
    }

    public void processText(String text) {
        for (String tok : tokenizer.tokenize(text)) {
            String norm = normalizer.normalize(tok);
            if (!norm.isEmpty()) store.addOne(norm);
        }
    }

    public void addOne(String raw) {
        String norm = normalizer.normalize(raw);
        if (!norm.isEmpty()) store.addOne(norm);
    }

    public void removeOne(String raw) {
        String norm = normalizer.normalize(raw);
        if (!norm.isEmpty()) store.removeOne(norm);
    }

    public Map<Character, List<CountableWord>> viewByInitial() {
        return store.viewByInitial();
    }
}
