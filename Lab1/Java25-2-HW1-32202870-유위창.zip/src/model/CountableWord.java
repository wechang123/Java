package model;

// SRP: CountableWord는 카운트 가능한 단어 관리만 담당
// LSP: Word를 상속하지 않고 합성을 사용하여 리스코프 치환 원칙 준수
public class CountableWord {
    private final Word word;
    private int count;
    
    public CountableWord(String word) {
        this.word = new Word(word);
        this.count = 1;
    }
    
    public CountableWord(Word word) {
        this.word = word;
        this.count = 1;
    }
    
    public Word getWord() {
        return word;
    }
    
    public String getWordString() {
        return word.getWord();
    }
    
    public int getCount() {
        return count;
    }
    
    public void increaseCount() {
        count++;
    }
    
    public void decreaseCount() {
        if (count > 0) {
            count--;
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CountableWord other = (CountableWord) obj;
        return word.equals(other.word);
    }
    
    @Override
    public int hashCode() {
        return word.hashCode();
    }
    
    @Override
    public String toString() {
        return word + "(" + count + ")";
    }
}