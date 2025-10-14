import java.util.ArrayList;
import java.util.List;

public class LocationManager implements LocationSubject {
    // LocationSubject 인터페이스 구현

    private List<LocationObserver> observers; // 등록된 LocationObserver 목록
    private Location last;                    // 마지막 위치
    private long lastTsMs;                   // 마지막 업데이트 시간
    private double distanceThresholdMeters;   // 거리 임계값 (미터)
    private long minUpdateIntervalMs;        // 최소 업데이트 간격 (밀리초)

 
    public LocationManager(double distanceThresholdMeters, long minUpdateIntervalMs) { 
        this.observers = new ArrayList<>(); // Observer 목록 초기화
        this.distanceThresholdMeters = distanceThresholdMeters; // 거리 임계값 설정
        this.minUpdateIntervalMs = minUpdateIntervalMs; // 최소 업데이트 간격 설정
        this.last = null; // 마지막 위치 초기화
        this.lastTsMs = 0; // 마지막 업데이트 시간 초기화
    }

    public void addObserver(LocationObserver observer) { // Observer 추가
        if (observer != null && !observers.contains(observer)) { // 중복 등록 방지
            observers.add(observer); // Observer 목록에 추가
        }
    }

    @Override  
    public void removeObserver(LocationObserver observer) { // Observer 제거
        observers.remove(observer); // Observer 목록에서 제거 
    }

    @Override 
    public void notifyLocationObservers(Location location) { // 모든 Observer에게 위치 변화 통지
        for (LocationObserver observer : observers) { // 등록된 모든 Observer에게 알림
            observer.onLocationChanged(location); // 위치 변화 알림 메서드 호출
        }
    }
    
    @Override 
    public void updateLocation(Location current) { // 위치 업데이트 메서드
        long currentTimeMs = System.currentTimeMillis(); // 현재 시간 (밀리초)

        
        if (last == null) { // 첫 번째 위치 업데이트인 경우
            last = current; // 마지막 위치 설정
            lastTsMs = currentTimeMs; // 마지막 업데이트 시간 설정
            notifyLocationObservers(current); // 모든 Observer에게 첫 위치 통지
            return;
        }

        long timeDiff = currentTimeMs - lastTsMs; // 마지막 업데이트 이후 경과 시간 계산
        if (timeDiff < minUpdateIntervalMs) { // 최소 업데이트 간격 미만인 경우
            return;
        }

        double distance = GeoUtil.distanceMeters(last, current); // 마지막 위치와 현재 위치 간 거리 계산
        if (distance >= distanceThresholdMeters) { // 의미 있는 거리 이동인 경우
            last = current; // 마지막 위치 갱신
            lastTsMs = currentTimeMs; // 마지막 업데이트 시간 갱신
            notifyLocationObservers(current); // 모든 Observer에게 위치 변화 통지
        }
    }
    public Location getLastLocation() { // 마지막 위치 반환 메서드
        return last;
    }
}