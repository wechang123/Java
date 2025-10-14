// 추상 데코레이터 클래스 - 다른 IPOI 객체를 감싸는 역할
public abstract class POIDecorator implements IPOI {
    protected IPOI decoratedPOI; // 감싸고 있는 IPOI 객체

    public POIDecorator(IPOI decoratedPOI) { // 생성자
        // yourcode
        this.decoratedPOI = decoratedPOI; // 감쌀 IPOI 객체 저장
    }

    @Override
    public String getInformation() { // 감싸고 있는 객체의 정보 반환
        // yourcode
        return decoratedPOI.getInformation(); // 감싸고 있는 객체의 getInformation() 호출
    }

    // 데코레이터 체인에서 원본 POI 객체를 재귀적으로 찾는 유틸리티 메서드
    public static POI unwrapPOI(IPOI node) {
        // yourcode
        if (node instanceof POIDecorator) { // 현재 노드가 데코레이터인 경우
            return unwrapPOI(((POIDecorator) node).decoratedPOI); // 재귀적으로 다음 단계 unwrap
        } else if (node instanceof POI) { // 현재 노드가 원본 POI인 경우
            return (POI) node; // POI 객체 반환
        } else { 
            return null; 
        }
    }
}