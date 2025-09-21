package implementation;

import interfaces.IWordViewer;
import model.CountableWord;
import java.util.List;
import java.util.Map;

// SRP: 콘솔 출력만 담당
// OCP: IWordViewer를 구현하여 확장에 열려있음
public class ConsoleWordViewer implements IWordViewer {
    
    @Override
    public void display(Map<Character, List<CountableWord>> wordMap) {
        for (Map.Entry<Character, List<CountableWord>> entry : wordMap.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            for (CountableWord cw : entry.getValue()) {
                System.out.print(cw + " ");
            }
            System.out.println();
        }
    }
}