import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// SRP ConsolePrinter는 출력만 담당
public class ConsolePrinter {
    public void print(Map<Character, List<CountableWord>> grouped) {
        // 키 정렬 보장(TreeMap) 상태지만, 혹시 모르니 재정렬 가능
        var keys = grouped.keySet().stream().sorted().collect(Collectors.toList());
        for (char ch : keys) {
            List<CountableWord> list = grouped.get(ch);
            System.out.print(ch + ": ");
            for (int i = 0; i < list.size(); i++) {
                if (i > 0) System.out.print(" ");
                System.out.print(list.get(i));
            }
            System.out.println();
        }
    }
}
