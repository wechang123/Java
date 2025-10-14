// 카테고리 정보를 추가하는 구상 데코레이터
public class CategoryPOIDecorator extends POIDecorator {
    private String category; // POI의 카테고리

    public CategoryPOIDecorator(IPOI decoratedPOI, String category) { // 생성자
        // yourcode
        super(decoratedPOI); // 부모 클래스 생성자 호출
        this.category = category; // 카테고리 저장
    }

    @Override
    public String getInformation() { // 기존 정보에 카테고리 추가
        // yourcode
        return super.getInformation() + " | Category: " + category; // 카테고리 정보 덧붙임
    }
}