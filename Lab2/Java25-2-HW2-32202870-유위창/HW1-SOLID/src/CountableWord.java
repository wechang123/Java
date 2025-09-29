// SRP CountableWord는 Word를 합성(composition)하여 카운트를 보유하는 엔티티. 카운트 변경 책임만 가짐
public class CountableWord {
    private final Word word;
    private int count;

    public CountableWord(Word word) {
        this.word = word;
        count = 1;
    }

    public Word getWord() {
        return this.word;
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

    boolean isEmpty() { 
        return count == 0; 
    }

    @Override
    public String toString() {
        return word + "(" + count + ")";
    }
}
