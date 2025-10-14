public interface LocationSubject { // Subject 인터페이스
    void removeObserver(LocationObserver observer); // 등록된 locationObserver 제거
    void addObserver(LocationObserver observer); // 새로운 locationObserver 등록
    void updateLocation(Location current); // 위치 업데이트
    void notifyLocationObservers(Location location); 
    // yourcode-등록된 모든 locationObserver들에게 위치 변화를 알림
}