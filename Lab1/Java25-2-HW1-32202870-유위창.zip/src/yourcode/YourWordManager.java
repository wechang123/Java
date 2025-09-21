package yourcode;

import interfaces.*;
import model.CountableWord;
import java.io.IOException;
import java.util.List;
import java.util.Map;

// SRP: 전체 단어 관리 시스템의 조정자 역할만 담당
// DIP: 구체 클래스가 아닌 인터페이스에 의존
public class YourWordManager {
    private final YourWordProcessor processor;
    private final IWordRepository repository;
    private final IWordViewer viewer;
    
    public YourWordManager(
            YourWordProcessor processor,
            IWordRepository repository,
            IWordViewer viewer) {
        this.processor = processor;
        this.repository = repository;
        this.viewer = viewer;
    }
    
    // 파일 처리
    public void loadFromFile(String filePath) throws IOException {
        processor.processFile(filePath);
    }
    
    // 단어 추가
    public void addWord(String word) {
        processor.addWord(word);
    }
    
    // 단어 제거
    public void removeWord(String word) {
        processor.removeWord(word);
    }
    
    // 현재 상태 출력
    public void display() {
        Map<Character, List<CountableWord>> wordMap = repository.getAll();
        viewer.display(wordMap);
    }
    
    // 저장소 초기화
    public void clear() {
        repository.clear();
    }
}