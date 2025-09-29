import java.util.Objects;

// SRP Word 데이터 담당
public class Word {
    private final String word;

    public Word(String word) {
        if (word == null || word.trim().isEmpty())
            throw new IllegalArgumentException("word must not be empty");
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    @Override 
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        return Objects.equals(this.word, ((Word)o).word);
    }

    @Override public int hashCode() { 
        return Objects.hash(word); 
    }

    @Override
    public String toString() {
        return word;
    }
}
