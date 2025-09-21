package yourcode;

import interfaces.*;
import java.io.IOException;
import java.util.List;

// SRP: 단어 처리 파이프라인 조정만 담당
// DIP: 구체 클래스가 아닌 인터페이스에 의존
// OCP: 새로운 처리 방식을 추가할 때 이 클래스를 수정하지 않고 새로운 구현체만 추가
public class YourWordProcessor {
    private final IWordLoader loader;
    private final IWordTokenizer tokenizer;
    private final IWordNormalizer normalizer;
    private final IWordRepository repository;
    
    // DIP: 의존성 주입을 통해 구체 클래스가 아닌 인터페이스에 의존
    public YourWordProcessor(
            IWordLoader loader,
            IWordTokenizer tokenizer,
            IWordNormalizer normalizer,
            IWordRepository repository) {
        this.loader = loader;
        this.tokenizer = tokenizer;
        this.normalizer = normalizer;
        this.repository = repository;
    }
    
    // SRP: 파일 처리 파이프라인 조정만 담당
    public void processFile(String filePath) throws IOException {
        // 1. 파일 로드
        List<String> rawWords = loader.loadWords(filePath);
        
        // 2. 각 라인을 토큰화하고 정규화
        for (String line : rawWords) {
            List<String> tokens = tokenizer.tokenize(line);
            for (String token : tokens) {
                String normalized = normalizer.normalize(token);
                if (!normalized.isEmpty()) {
                    repository.add(normalized);
                }
            }
        }
    }
    
    // 단일 단어 추가
    public void addWord(String word) {
        String normalized = normalizer.normalize(word);
        if (!normalized.isEmpty()) {
            repository.add(normalized);
        }
    }
    
    // 단일 단어 제거
    public void removeWord(String word) {
        String normalized = normalizer.normalize(word);
        if (!normalized.isEmpty()) {
            repository.remove(normalized);
        }
    }
}