package implementation;

import interfaces.IWordNormalizer;

// SRP: 단어 정규화(소문자 변환)만 담당
// OCP: IWordNormalizer를 구현하여 확장에 열려있음
public class LowercaseWordNormalizer implements IWordNormalizer {
    
    @Override
    public String normalize(String word) {
        if (word == null || word.isEmpty()) {
            return "";
        }
        return word.trim().toLowerCase();
    }
}