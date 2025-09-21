package model;

// SRP: Word 클래스는 단어 데이터만 관리하는 단일 책임
// 불변 객체로 만들어 thread-safe하고 예측 가능한 동작 보장
public final class Word {
    private final String word;
    
    public Word(String word) {
        if (word == null || word.trim().isEmpty()) {
            throw new IllegalArgumentException("Word cannot be null or empty");
        }
        this.word = word.trim().toLowerCase(); // 정규화된 형태로 저장
    }
    
    public String getWord() {
        return word;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Word other = (Word) obj;
        return word.equals(other.word);
    }
    
    @Override
    public int hashCode() {
        return word.hashCode();
    }
    
    @Override
    public String toString() {
        return word;
    }
}