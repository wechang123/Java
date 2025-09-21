
// SOLID 원칙을 적용한 리팩토링 버전
// SRP: 각 클래스가 단일 책임을 가짐
// OCP: 확장에는 열려있고 수정에는 닫혀있음
// LSP: 상속 대신 합성 사용으로 리스코프 치환 원칙 준수
// ISP: 인터페이스를 작고 구체적으로 분리
// DIP: 구체 클래스가 아닌 추상화(인터페이스)에 의존

import yourcode.YourWordFactory;
import yourcode.YourWordManager;

public class App2 {
    public static final String FRUITS_CSV = "/Users/yuwichang/java/HW1-Monolithic/fruits.csv";
    
    public static void main(String[] args) {
        try {
            // DIP: 팩토리를 통해 의존성 주입
            YourWordManager wordManager = YourWordFactory.createDefaultWordManager();
            
            // 파일 처리
            wordManager.loadFromFile(FRUITS_CSV);
            System.out.println("== 초기 ==");
            wordManager.display();
            
            // banana 1회 제거
            wordManager.removeWord("banana");
            System.out.println("== banana 1회 제거 후 ==");
            wordManager.display();
            
            // banana 2회 제거
            wordManager.removeWord("banana");
            System.out.println("== banana 2회 제거 후 ==");
            wordManager.display();
            
            // date 추가
            wordManager.addWord("date");
            System.out.println("== date 추가 후 ==");
            wordManager.display();
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}