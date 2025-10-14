public final class GeoUtil { // 지리 계산 유틸리티 클래스
    public static double distanceMeters(Location a, Location b) { // 두 지점 간의 거리 계산 (미터 단위)
        // 지구 반지름 (미터)
        final double R = 6371000.0;
        // 위도와 경도의 차이를 라디안으로 변환
        double dLat = Math.toRadians(b.lat - a.lat);
        double dLon = Math.toRadians(b.lon - a.lon);
        double la1 = Math.toRadians(a.lat);
        double la2 = Math.toRadians(b.lat);
        // Haversine 공식 적용
        double h = Math.sin(dLat/2) * Math.sin(dLat/2) +
                   Math.cos(la1) * Math.cos(la2) * Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(h), Math.sqrt(1-h));
        return R * c;
    }
}