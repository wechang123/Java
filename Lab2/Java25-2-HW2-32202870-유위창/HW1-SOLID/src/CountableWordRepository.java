import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// SRP CountableWordRepository는 저장/집계 책임
public class CountableWordRepository {
    // 요구사항: 외부에서 사용하는 MAP 형태
    private final Map<Character, List<CountableWord>> map = new HashMap<>();

    public void addOne(String word) {
        char key = word.charAt(0);
        List<CountableWord> list = map.computeIfAbsent(key, _ -> new ArrayList<>());

        for (CountableWord cw : list) {
            if (cw.getWord().getWord().equals(word)) {
                cw.increaseCount();
                return;
            }
        }
        list.add(new CountableWord(new Word(word)));
    }

    public void removeOne(String word) {
        char key = word.charAt(0);
        List<CountableWord> list = map.get(key);
        if (list == null) return;

        for (int i = 0; i < list.size(); i++) {
            CountableWord cw = list.get(i);
            if (cw.getWord().getWord().equals(word)) {
                cw.decreaseCount();
                if (cw.isEmpty()) list.remove(i);
                if (list.isEmpty()) map.remove(key);
                return;
            }
        }
    }

    public Map<Character, List<CountableWord>> viewByInitial() {
        // 읽기 전용 스냅샷 반환 (캡슐화)
        Map<Character, List<CountableWord>> snapshot = new TreeMap<>();
        for (var e : map.entrySet()) {
            // 리스트 사본 + 불변 리스트로 래핑
            snapshot.put(e.getKey(), List.copyOf(e.getValue()));
        }
        return Collections.unmodifiableMap(snapshot);
    }
}
