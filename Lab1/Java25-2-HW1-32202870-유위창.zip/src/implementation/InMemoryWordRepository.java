package implementation;

import interfaces.IWordRepository;
import model.CountableWord;
import java.util.*;

// SRP: 단어 저장소 관리만 담당
// OCP: IWordRepository를 구현하여 확장에 열려있음
public class InMemoryWordRepository implements IWordRepository {
    private final Map<Character, List<CountableWord>> wordMap;
    
    public InMemoryWordRepository() {
        this.wordMap = new HashMap<>();
    }
    
    @Override
    public void add(String word) {
        if (word == null || word.isEmpty()) return;
        
        char key = word.charAt(0);
        List<CountableWord> list = wordMap.computeIfAbsent(key, k -> new ArrayList<>());
        
        // 기존 단어 찾기
        for (CountableWord cw : list) {
            if (cw.getWordString().equals(word)) {
                cw.increaseCount();
                return;
            }
        }
        
        // 새 단어 추가
        list.add(new CountableWord(word));
    }
    
    @Override
    public void remove(String word) {
        if (word == null || word.isEmpty()) return;
        
        char key = word.charAt(0);
        List<CountableWord> list = wordMap.get(key);
        if (list == null) return;
        
        for (int i = 0; i < list.size(); i++) {
            CountableWord cw = list.get(i);
            if (cw.getWordString().equals(word)) {
                cw.decreaseCount();
                if (cw.getCount() == 0) {
                    list.remove(i);
                }
                if (list.isEmpty()) {
                    wordMap.remove(key);
                }
                return;
            }
        }
    }
    
    @Override
    public Map<Character, List<CountableWord>> getAll() {
        // TreeMap을 사용하여 키를 알파벳 순으로 정렬
        return new TreeMap<>(wordMap);
    }
    
    @Override
    public void clear() {
        wordMap.clear();
    }
}