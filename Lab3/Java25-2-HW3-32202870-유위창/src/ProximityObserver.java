import java.util.List;

public interface ProximityObserver { // Observer 인터페이스
    void onNearestChanged(List<POI> nearestSorted); //  가장 가까운 POI 변화 알림
    void onEnter(POI poi, double distanceMeters); // 근접 반경 진입 알림
    void onExit(POI poi, double distanceMeters); // 근접 반경 이탈 알림
}