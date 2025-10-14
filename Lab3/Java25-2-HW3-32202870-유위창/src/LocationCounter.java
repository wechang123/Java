//yourcode
public class LocationCounter implements LocationObserver { // LocationObserver 인터페이스 구현
    private int updateCount; // 위치 업데이트 횟수
    private Location firstLocation; // 첫 위치
    private Location lastLocation;  // 마지막 위치
    public LocationCounter() { // 생성자
        this.updateCount = 0; // 초기화
        this.firstLocation = null; // 첫 위치 초기화
        this.lastLocation = null; // 마지막 위치 초기화
    }
    @Override 
    public void onLocationChanged(Location location) { // 위치 변경 시 호출
        updateCount++;
        if (firstLocation == null) {
            firstLocation = location;
            System.out.printf("[COUNT] 위치 추적 시작 - 첫 위치: %s%n", location);
        }
        lastLocation = location;
        // 3번째마다 현재 상태 출력
        if (updateCount % 3 == 0) {
            System.out.printf("[COUNT] 현재까지 %d번의 위치 업데이트 완료%n", updateCount);

            if (firstLocation != null && lastLocation != null) { // 첫 위치와 마지막 위치가 모두 존재할 때
                double totalDistance = GeoUtil.distanceMeters(firstLocation, lastLocation);
                System.out.printf("[COUNT] 시작점에서 현재까지 총 이동거리: %.1fm%n", totalDistance);
            }
        }
    }
    public void printFinalStatistics() { // 최종 통계 출력
        System.out.println("[COUNT] === 위치 추적 최종 통계 ===");
        System.out.printf("[COUNT] 총 위치 업데이트 횟수: %d회%n", updateCount);

        if (firstLocation != null && lastLocation != null) { // 첫 위치와 마지막 위치가 모두 존재할 때
            double totalDistance = GeoUtil.distanceMeters(firstLocation, lastLocation);
            System.out.printf("[COUNT] 총 이동 거리: %.1fm%n", totalDistance);
            System.out.printf("[COUNT] 시작 위치: %s%n", firstLocation);
            System.out.printf("[COUNT] 최종 위치: %s%n", lastLocation);
        }
    }
    public int getUpdateCount() {
        return updateCount;
    }
}
