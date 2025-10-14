import java.util.List;

// POI(Point of Interest) 클래스 - 관심 지점 정보를 저장하는 Concrete Component
public class POI implements IPOI {
    private String name; // POI 이름
    private Location location; // POI 위치 (위도, 경도)
    private String category; // POI 카테고리 (JSON에서 로드)
    private List<String> hashtags; // POI 해시태그 리스트 (JSON에서 로드)

    public POI(String name, Location location) { // 생성자
        // yourcode
        this.name = name; // 이름 초기화
        this.location = location; // 위치 초기화
    }

    @Override
    public String getInformation() { // POI 정보를 문자열로 반환
        // yourcode
        return "POI: " + this.name + " @ " + this.location; // "POI: 이름 @ 위치" 형식
    }

    @Override
    public String toString() { // toString 메서드 오버라이드
        // yourcode
        return getInformation(); // getInformation() 메서드 호출
    }

    // Getter 메서드들 (JSON 파싱 및 데코레이터에서 사용)
    public String getName() { return name; } // 이름 반환
    public Location getLocation() { return location; } // 위치 반환
    public String getCategory() { return category; } // 카테고리 반환
    public List<String> getHashtags() { return hashtags; } // 해시태그 리스트 반환
}