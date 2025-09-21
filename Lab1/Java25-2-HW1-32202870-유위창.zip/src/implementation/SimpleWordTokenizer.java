package implementation;

import interfaces.IWordTokenizer;
import java.util.ArrayList;
import java.util.List;

// SRP: 단어 토큰화만 담당
// OCP: IWordTokenizer를 구현하여 확장에 열려있음
public class SimpleWordTokenizer implements IWordTokenizer {
    
    @Override
    public List<String> tokenize(String text) {
        // 구두점과 공백으로 분리
        String[] tokens = text.split("[ \t\n'\",.?!]");
        List<String> result = new ArrayList<>();
        
        for (String token : tokens) {
            if (!token.isEmpty()) {
                result.add(token.trim());
            }
        }
        
        return result;
    }
}

