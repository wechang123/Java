import java.util.ArrayList;
import java.util.List;

// OCP 다른 토큰화기가 필요하면 ITokenizer 구현만 추가(예: 형태소 분석기).
public class RegexTokenizer implements ITokenizer {
    private final String pattern;

    RegexTokenizer(String pattern) { this.pattern = pattern; }

    @Override public List<String> tokenize(String text) {
        if (text == null || text.isEmpty()) return List.of();
        String[] arr = text.split(pattern);
        List<String> out = new ArrayList<>(arr.length);
        for (String s : arr) {
            if (s != null && !s.isEmpty()) out.add(s);
        }
        return out;
    }
}
