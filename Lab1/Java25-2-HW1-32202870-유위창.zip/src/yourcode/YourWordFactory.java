package yourcode;

import interfaces.*;
import implementation.*;

// SRP: 객체 생성 책임만 담당
// OCP: 새로운 구현체를 추가할 때 팩토리 메서드만 추가하면 됨
// DIP: 구체 클래스 생성을 한 곳에서 관리하여 의존성 역전
public class YourWordFactory {
    
    // 기본 WordManager 생성
    public static YourWordManager createDefaultWordManager() {
        IWordLoader loader = new CsvWordLoader();
        IWordTokenizer tokenizer = new SimpleWordTokenizer();
        IWordNormalizer normalizer = new LowercaseWordNormalizer();
        IWordRepository repository = new InMemoryWordRepository();
        IWordViewer viewer = new ConsoleWordViewer();
        
        YourWordProcessor processor = new YourWordProcessor(
            loader, tokenizer, normalizer, repository
        );
        
        return new YourWordManager(processor, repository, viewer);
    }
    
    // 커스텀 설정으로 WordManager 생성
    public static YourWordManager createCustomWordManager(
            IWordLoader loader,
            IWordTokenizer tokenizer,
            IWordNormalizer normalizer,
            IWordRepository repository,
            IWordViewer viewer) {
        
        YourWordProcessor processor = new YourWordProcessor(
            loader, tokenizer, normalizer, repository
        );
        
        return new YourWordManager(processor, repository, viewer);
    }
}