// OCP 정규화 규칙 교체는 INormalizer 구현만 변경.
public class LowercaseTrimNormalizer implements ITextNormalizer {
    @Override 
    public String normalize(String raw) {
        if (raw == null) return "";
        return raw.trim().toLowerCase();
    }
}
