import java.util.List;

/**
 * App 클래스
 * 이중 Observer 패턴을 사용한 POI 근접 알림 시스템 메인 클래스
 * LocationManager → ProximityManager → (POIDetailView, ProximityLogger, LocationSpeedAnalyzer)
 */
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("=== POI 근접 알림 시스템 시작 ===");

        // POI 데이터 로딩 (절대 경로 사용)
        String poiFilePath = System.getProperty("user.dir").endsWith("HW3-POI") ?
            "POI.json" : "/Users/yuwichang/java/Lab3/HW3-POI/POI.json";
        List<POI> pois = POIGsonFileLoader.load(poiFilePath);
        System.out.println("로딩된 POI 개수: " + pois.size());
        System.out.println();

        // Subject 생성 (LocationManager)
        LocationManager locationManager = new LocationManager(5.0, 500);

        // Subject&Observer 생성 (ProximityManager)
        ProximityManager proximityManager = new ProximityManager(pois, 2, 100.0);

        // Observer들 생성
        POIDetailView uiView = new POIDetailView();
        ProximityLogger logger = new ProximityLogger();


        // 위치 업데이트 횟수와 이동 거리 통계를 제공하는 Observer
        LocationCounter counter = new LocationCounter(); //yourcode
        // Observer 패턴 연결
        locationManager.addObserver(proximityManager);  // LocationManager → ProximityManager
        proximityManager.addObserver(uiView);           // ProximityManager → POIDetailView
        proximityManager.addObserver(logger);           // ProximityManager → ProximityLogger

        locationManager.addObserver(counter);// yourcode

        System.out.println("Observer 패턴 연결 완료");
        System.out.println("- LocationManager → ProximityManager");
        System.out.println("- ProximityManager → POIDetailView, ProximityLogger");
        System.out.println("- LocationManager → LocationCounter (추가 기능)");
        System.out.println();

        // 사용자 이동 시뮬레이션 경로 (PDF 예시와 유사하게 조정)
        Location[] path = new Location[] {
            new Location(37.5779, 126.9760),   // 시작점 - 경복궁 근정문 근처
            new Location(37.5784, 126.9766),   // 경복궁 근정문 더 가까이
            new Location(37.5790, 126.9771),   // 경복궁 사정전 쪽으로
            new Location(37.5799, 126.9770),   // 경복궁 향원정 방향
            new Location(37.5805, 126.9769),   // 경복궁 향원정 근처
            new Location(37.5815, 126.9775)    // 멀어지는 지점
        };

        System.out.println("=== 이동 시뮬레이션 시작 ===");

        // 이동 경로 실행
        for (int i = 0; i < path.length; i++) {
            Location currentLocation = path[i];
            System.out.printf("--- 위치 %d: %s ---%n", i+1, currentLocation);

            locationManager.updateLocation(currentLocation);

            // 시간 간격 (minUpdateIntervalMs보다 길게)
            Thread.sleep(600);
            System.out.println();
        }

        System.out.println("=== Observer 추가/삭제 기능 시연 ===");

        // Observer 제거 시연
        System.out.println("ProximityLogger 제거...");
        proximityManager.removeObserver(logger);

        // 새로운 위치에서 테스트
        System.out.println("새 위치에서 테스트 (로거 제거 상태):");
        locationManager.updateLocation(new Location(37.5810, 126.9810));
        Thread.sleep(600);

        // Observer 다시 추가
        System.out.println("\nProximityLogger 다시 추가...");
        proximityManager.addObserver(logger);

        System.out.println("새 위치에서 테스트 (로거 추가 상태):");
        locationManager.updateLocation(new Location(37.5820, 126.9820));


        // 위치 카운터 통계 출력 - 전체 이동 경로에 대한 통계 정보
        System.out.println("\n=== 위치 추적 분석 결과 (YOURCODE) ==="); //yourcode
        counter.printFinalStatistics();

        System.out.println("\n=== 시스템 종료 ===");
    }
}
