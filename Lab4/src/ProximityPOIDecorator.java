// 특정 반경 내 근접성 정보를 추가하는 구상 데코레이터
public class ProximityPOIDecorator extends POIDecorator {
    private Location currentLocation; // 현재 위치
    private double radiusMeter; // 판단할 반경 (미터 단위)

    public ProximityPOIDecorator(IPOI decoratedPOI, Location currentLocation, double radiusMeter) { // 생성자
        // yourcode
        super(decoratedPOI); // 부모 클래스 생성자 호출
        this.currentLocation = currentLocation; // 현재 위치 저장
        this.radiusMeter = radiusMeter; // 반경 저장
    }

    @Override
    public String getInformation() { // 기존 정보에 근접성 추가
        // yourcode
        POI poi = POIDecorator.unwrapPOI(decoratedPOI); // 데코레이터 체인에서 원본 POI 추출
        if (poi != null && poi.getLocation() != null) { // 원본 POI와 위치가 존재하는 경우
            double distance = GeoUtil.distanceMeters(currentLocation, poi.getLocation()); // Haversine 공식으로 거리 계산
            String inRange = (distance <= radiusMeter) ? "Yes" : "No"; // 반경 내에 있는지 판단
            return super.getInformation() + String.format(" | Within %.0fm: %s", radiusMeter, inRange); // 근접성 정보 덧붙임
        } else { // 원본 POI를 찾을 수 없는 경우
            return super.getInformation(); // 원래 정보만 반환
        }
    }
}