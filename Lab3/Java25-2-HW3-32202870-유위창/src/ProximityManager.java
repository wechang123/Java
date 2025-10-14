import java.util.*;
import java.util.stream.Collectors;

public class ProximityManager implements LocationObserver, ProximitySubject { 
    // ProximitySubject 인터페이스 구현 및 LocationObserver 구현
    private List<POI> all;                          // 모든 POI 목록
    private int topK;                               // 상위 K개 근접 POI 개수
    private double proximityMeters;                 // 근접 반경 (미터)
    private List<ProximityObserver> observers;      // ProximityObserver 목록

    // 근접 상태 관리
    private List<String> nearestOrder;              // 현재 가장 가까운 Top-K POI 순서
    private Set<String> inside;                     // 현재 근접 반경 내에 있는 POI 이름들

public ProximityManager(List<POI> all, int topK, double proximityMeters) {
        // 생성자
        this.all = Objects.requireNonNull(all);
        this.topK = Math.max(1, topK);
        this.proximityMeters = Math.max(0.0, proximityMeters);
        this.observers = new ArrayList<>();
        this.nearestOrder = new ArrayList<>();
        this.inside = new HashSet<>();
    }

    @Override
    public void addObserver(ProximityObserver observer) { // Observer 추가
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(ProximityObserver observer) { // Observer 제거
        observers.remove(observer);
    }

    @Override
    public void notifyNearestChanged(List<POI> nearestSorted) { 
        // 가장 가까운 POI 변화 통지
        for (ProximityObserver observer : observers) { // 모든 Observer에게 알림
            observer.onNearestChanged(nearestSorted);
        }
    }

    @Override
    public void notifyEnter(POI poi, double distanceMeters) { // 근접 반경 진입 통지
        for (ProximityObserver observer : observers) { // 모든 Observer에게 진입 알림
            observer.onEnter(poi, distanceMeters);
        }
    }

    @Override
    public void notifyExit(POI poi, double distanceMeters) { // 근접 반경 이탈 통지
        for (ProximityObserver observer : observers) { // 모든 Observer에게 이탈 알림
            observer.onExit(poi, distanceMeters);
        }
    }

   
    @Override // LocationObserver 인터페이스 메서드 구현
    public void onLocationChanged(Location here) { // 위치 변경 시 호출
        // 1. 모든 POI와의 거리 계산 및 정렬
        List<POIDistance> distances = all.stream() 
                .map(poi -> new POIDistance(poi, GeoUtil.distanceMeters(here, poi.location)))
                .sorted(Comparator.comparingDouble(pd -> pd.distance))
                .collect(Collectors.toList());

        // 2. 가장 근접한 Top-K 목록 작성
        List<POI> currentNearest = distances.stream()
                .limit(topK)
                .map(pd -> pd.poi)
                .collect(Collectors.toList());
        // 3. Top-K POI 이름 목록
        List<String> currentNearestNames = currentNearest.stream()
                .map(poi -> poi.name)
                .collect(Collectors.toList());

        // 4. Top-K 목록 변화 확인 및 통지
        if (!currentNearestNames.equals(nearestOrder)) {
            nearestOrder = new ArrayList<>(currentNearestNames);
            notifyNearestChanged(currentNearest);
        }

        // 5 . 근접 반경 진입/이탈 이벤트 처리
        Set<String> currentInside = new HashSet<>();

        for (POIDistance pd : distances) {
            if (pd.distance <= proximityMeters) {
                currentInside.add(pd.poi.name);

                // 진입 이벤트: 이전에 없었는데 현재 근접 반경 안에 있음
                if (!inside.contains(pd.poi.name)) {
                    notifyEnter(pd.poi, pd.distance);
                }
            }
        }

        // 이탈 이벤트: 이전에 있었는데 현재 근접 반경 밖에 있음
        for (String prevInsideName : inside) {
            if (!currentInside.contains(prevInsideName)) {
                // 해당 POI 찾기
                POI exitPoi = all.stream()
                        .filter(poi -> poi.name.equals(prevInsideName))
                        .findFirst()
                        .orElse(null);

                if (exitPoi != null) {
                    double distance = GeoUtil.distanceMeters(here, exitPoi.location);
                    notifyExit(exitPoi, distance);
                }
            }
        }

        // 근접 상태 업데이트
        inside = currentInside;
    }

    /**
     * POI와 거리를 저장하는 내부 클래스
     */
    private static class POIDistance {
        final POI poi;
        final double distance;

        POIDistance(POI poi, double distance) {
            this.poi = poi;
            this.distance = distance;
        }
    }
}