import java.util.ArrayList;
import java.util.List;

// Lab2 신규 클래스: 공백을 기준으로 문자열을 토큰화하는 Tokenizer
// RegexTokenizer와 달리 단순히 공백만을 구분자로 사용 (구두점 등은 단어에 포함)
public class WhitespaceTokenizer implements ITokenizer {
    @Override
    public List<String> tokenize(String text) {
        // 결과를 저장할 리스트 초기화
        List<String> tokens = new ArrayList<>();

        // null 또는 빈 문자열 체크
        if (text == null || text.isEmpty()) {
            return tokens;  // 빈 리스트 반환
        }

        // 정규식 \\s+를 사용하여 하나 이상의 공백문자로 분리
        // \\s: 모든 공백 문자 (스페이스, 탭, 줄바꿈 등)
        // +: 하나 이상의 연속된 공백
        // 예: "Hello   World\tJava" → ["Hello", "World", "Java"]
        String[] parts = text.split("\\s+");

        // 분리된 각 부분을 토큰 리스트에 추가
        for (String part : parts) {
            // split 결과로 빈 문자열이 생길 수 있으므로 체크
            if (!part.isEmpty()) {
                tokens.add(part);  // 구두점이 포함된 상태로 추가 (예: "fox!", "docs.")
            }
        }
        return tokens;
    }
}