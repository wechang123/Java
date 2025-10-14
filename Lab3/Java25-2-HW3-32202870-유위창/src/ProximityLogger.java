import java.util.List;

public class ProximityLogger implements ProximityObserver { 
    // ProximityObserver 인터페이스 구현

    @Override
    public void onNearestChanged(List<POI> nearestSorted) { //가장 가까운 POI 변화 알림
        System.out.println("[LOG] nearestChanged -> " + nearestSorted);
    }

    @Override
    public void onEnter(POI poi, double distanceMeters) { //POI 근접 반경 진입 알림
        System.out.printf("[LOG] enter %s @ %.2fm%n",
            poi.name, distanceMeters);
    }

    @Override
    public void onExit(POI poi, double distanceMeters) { //POI 근접 반경 이탈 알림
        System.out.printf("[LOG] exit %s @ %.2fm%n",
            poi.name, distanceMeters);
    }
}