import java.util.List;

public class POIDetailView implements ProximityObserver {
    // ProximityObserver 인터페이스 구현
    @Override
    public void onNearestChanged(List<POI> nearestSorted) { // 가장 가까운 POI 변화 알림
        System.out.println("[UI] Top-K nearest updated:");
        for (int i = 0; i < nearestSorted.size(); i++) {
            POI poi = nearestSorted.get(i);
            System.out.printf("#%d %s @ (%s)%n",
                i + 1, poi.name, poi.location);
        }
    }

    @Override
    public void onEnter(POI poi, double distanceMeters) { // POI 근접 반경 진입 알림
        System.out.printf("[UI] ENTER range: %s (%.1fm)%n",
            poi.name, distanceMeters);
    }

    @Override
    public void onExit(POI poi, double distanceMeters) { // POI 근접 반경 이탈 알림
        System.out.printf("[UI] EXIT range: %s (%.1fm)%n",
            poi.name, distanceMeters);
    }
}