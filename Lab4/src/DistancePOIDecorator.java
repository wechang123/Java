// 현재 위치로부터의 거리 정보를 추가하는 구상 데코레이터
public class DistancePOIDecorator extends POIDecorator {
    private Location currentLocation; // 현재 위치

    public DistancePOIDecorator(IPOI decoratedPOI, Location currentLocation) { // 생성자
        // yourcode
        super(decoratedPOI); // 부모 클래스 생성자 호출
        this.currentLocation = currentLocation; // 현재 위치 저장
    }

    @Override
    public String getInformation() { // 기존 정보에 거리 추가
        // yourcode
        POI poi = POIDecorator.unwrapPOI(decoratedPOI); // 데코레이터 체인에서 원본 POI 추출
        if (poi != null && poi.getLocation() != null) { // 원본 POI와 위치가 존재하는 경우
            double distance = GeoUtil.distanceMeters(currentLocation, poi.getLocation()); // Haversine 공식으로 거리 계산
            return super.getInformation() + String.format(" | Distance: %.2fm", distance); // 거리 정보 덧붙임 (소수점 2자리)
        } else { // 원본 POI를 찾을 수 없는 경우
            return super.getInformation(); // 원래 정보만 반환
        }
    }
}