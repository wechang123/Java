import java.util.List;

public interface ProximitySubject { // Subject 인터페이스
    void addObserver(ProximityObserver observer); // 새로운 ProximityObserver 등록
    void removeObserver(ProximityObserver observer); // 등록된 ProximityObserver 제거
    void notifyNearestChanged(List<POI> nearestSorted); // yourcode-가장 가까운 POI 변화 통지
    void notifyEnter(POI poi, double distanceMeters); // yourcode-근접 반경 진입 통지
    void notifyExit(POI poi, double distanceMeters); // yourcode-근접 반경 이탈 통지
}