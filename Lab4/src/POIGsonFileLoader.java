// yourcode - JSON 파일에서 POI 데이터를 로드하는 클래스
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class POIGsonFileLoader {
    // JSON 파일에서 기본 POI 리스트를 로드하는 메서드
    public static List<POI> load(String filePath) {
        // yourcode
        try (Reader reader = new FileReader(filePath)) { // 파일 읽기
            Gson gson = new Gson(); // Gson 객체 생성
            Type type = new TypeToken<Map<String, List<POI>>>(){}.getType(); // {"pois": [...]} 구조를 위한 타입 정의
            Map<String, List<POI>> map = gson.fromJson(reader, type); // JSON 파싱하여 Map으로 변환
            return map.get("pois"); // "pois" 키의 POI 리스트 반환
        } catch (Exception e) { // 예외 처리
            e.printStackTrace(); // 스택 트레이스 출력
            return new ArrayList<>(); // 빈 리스트 반환
        }
    }

    // JSON 파일에서 POI를 로드하고 Category와 Hashtag 데코레이터를 자동으로 적용하는 메서드
    public static List<IPOI> loadAsDecorated(String filePath) {
        // yourcode
        try (Reader reader = new FileReader(filePath)) { // 파일 읽기
            Gson gson = new Gson(); // Gson 객체 생성
            Type type = new TypeToken<Map<String, List<POI>>>(){}.getType(); // {"pois": [...]} 구조를 위한 타입 정의
            Map<String, List<POI>> map = gson.fromJson(reader, type); // JSON 파싱하여 Map으로 변환
            List<POI> pois = map.get("pois"); // "pois" 키의 POI 리스트 가져오기

            List<IPOI> decoratedPois = new ArrayList<>(); // 데코레이트된 IPOI 리스트 생성
            for (POI poi : pois) { // 각 POI에 대해 반복
                IPOI decoratedPoi = new POI(poi.getName(), poi.getLocation()); // 1. 기본 POI 생성
                if (poi.getCategory() != null && !poi.getCategory().isEmpty()) { // 2. Category가 있으면
                    decoratedPoi = new CategoryPOIDecorator(decoratedPoi, poi.getCategory()); // Category 데코레이터로 감싸기
                }
                if (poi.getHashtags() != null && !poi.getHashtags().isEmpty()) { // 3. Hashtag가 있으면
                    decoratedPoi = new HashtagPOIDecorator(decoratedPoi, poi.getHashtags()); // Hashtag 데코레이터로 감싸기
                }
                decoratedPois.add(decoratedPoi); // 데코레이트된 객체를 리스트에 추가
            }
            return decoratedPois; // 최종 데코레이트된 리스트 반환
        } catch (Exception e) { // 예외 처리
            e.printStackTrace(); // 스택 트레이스 출력
            return new ArrayList<>(); // 빈 리스트 반환
        }
    }
}
