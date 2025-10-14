//POI 데이터를 로드하고, 데코레이터 패턴을 적용하여 다양한 정보를 출력
import java.util.List;

public class App { // 메인 클래스
    public static void main(String[] args) throws Exception { // 메인 메서드
        System.out.println("== Loading POIs from NewPOI.json =="); // POI 데이터를 JSON 파일에서 로드
        List<POI> pois = POIGsonFileLoader.load("NewPOI.json"); // POI 객체 리스트 로드
        pois.forEach(System.out::println); // 각 POI 객체 출력
        System.out.println(); // 빈 줄 출력

        List<IPOI> decoratedBase = POIGsonFileLoader.loadAsDecorated("NewPOI.json"); // 데코레이터가 적용된 POI 객체 리스트 로드
        System.out.println("== Base decorated (from JSON) =="); // 기본 데코레이터가 적용된 POI 출력
        decoratedBase.forEach(d -> System.out.println(" - " + d.getInformation())); // 각 데코레이터 적용된 POI 정보 출력
        System.out.println(); // 빈 줄 출력

        Location[] path = new Location[] { // 경로를 나타내는 위치 배열
            new Location(37.5779, 126.9760),
            new Location(37.5784, 126.9766),
            new Location(37.5789, 126.9772),
            new Location(37.5795, 126.9787),
            new Location(37.5797, 126.9794),
            new Location(37.5800, 126.9800)
        };

        System.out.println("\n== Along path =="); // 경로를 따라 이동하면서 POI 정보 출력
        for (Location cur : path) { // 경로의 각 위치에 대해 반복
            System.out.printf("\n-- step @ (%.5f, %.5f)%n", cur.latitude, cur.longitude); // 현재 위치 출력
            for (IPOI baseChain : decoratedBase) { // 각 기본 데코레이터가 적용된 POI에 대해 반복
                // yourcode
                IPOI decoratedWithDist = new DistancePOIDecorator(baseChain, cur); // 4. Distance 데코레이터를 적용
                IPOI decoratedWithProx = new ProximityPOIDecorator(decoratedWithDist, cur, 200); // 5. Proximity 데코레이터를 적용
                System.out.println("   -> " + decoratedWithProx.getInformation()); // 최종 데코레이터가 적용된 POI 정보 출력
            }
        }
    }
}
