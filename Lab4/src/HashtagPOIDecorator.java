import java.util.List;
import java.util.stream.Collectors;

// 해시태그 정보를 추가하는 구상 데코레이터
public class HashtagPOIDecorator extends POIDecorator {
    private List<String> hashtags; // POI의 해시태그 리스트

    public HashtagPOIDecorator(IPOI decoratedPOI, List<String> hashtags) { // 생성자
        // yourcode
        super(decoratedPOI); // 부모 클래스 생성자 호출
        this.hashtags = hashtags; // 해시태그 리스트 저장
    }

    @Override
    public String getInformation() { // 기존 정보에 해시태그 추가
        // yourcode
        if (hashtags != null && !hashtags.isEmpty()) { // 해시태그가 존재하는 경우
            String tags = hashtags.stream().collect(Collectors.joining(", ")); // Stream API로 ", "로 구분된 문자열 생성
            return super.getInformation() + " | Hashtags: [" + tags + "]"; // 해시태그 정보 덧붙임
        } else { // 해시태그가 없는 경우
            return super.getInformation(); // 원래 정보만 반환
        }
    }
}