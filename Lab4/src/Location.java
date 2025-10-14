public class Location { // 위도와 경도를 저장하는 위치 클래스
    public final double latitude; // 위도
    public final double longitude; // 경도

    public Location(double latitude, double longitude) { // 생성자
        this.latitude = latitude; this.longitude = longitude; // 위도와 경도 초기화
    }

    @Override public String toString() { // 문자열로 변환
        return latitude + ", " + longitude; // "위도, 경도" 형식으로 반환
    }
}
