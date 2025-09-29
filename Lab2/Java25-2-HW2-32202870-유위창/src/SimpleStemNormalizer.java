// Lab2 신규 클래스: 간단한 어간 추출(stemming)을 수행하는 Normalizer
// 영어 단어의 소유격, 복수형, 시제, 동명사 형태를 제거하여 기본형으로 변환
public class SimpleStemNormalizer implements ITextNormalizer {
    @Override
    public String normalize(String raw) {
        // null 체크
        if (raw == null) return "";

        // 기본 전처리: 앞뒤 공백 제거 및 소문자 변환
        String word = raw.trim().toLowerCase();

        // 빈 문자열 체크
        if (word.isEmpty()) return "";

        // 1. 소유격 제거 처리
        // 영어 소유격 형태: 's, 's (유니코드 apostrophe), s'
        if (word.endsWith("'s")) {  // 일반 apostrophe (O'Reilly's → O'Reilly)
            word = word.substring(0, word.length() - 2);
        } else if (word.endsWith("'s")) {  // 유니코드 apostrophe
            word = word.substring(0, word.length() - 2);
        } else if (word.endsWith("s'")) {  // 복수 소유격 (dogs' → dog)
            word = word.substring(0, word.length() - 2);
        }

        // 2. 시제/동명사 제거 (길이가 4보다 큰 단어에 대해서만 적용)
        // 너무 짧은 단어는 어근이 손상될 수 있으므로 제외
        if (word.length() > 4) {
            if (word.endsWith("ing")) {  // 현재진행형/동명사 (reading → read)
                word = word.substring(0, word.length() - 3);
            } else if (word.endsWith("ed")) {  // 과거형/과거분사 (jumped → jump)
                word = word.substring(0, word.length() - 2);
            }
        }

        // 3. 복수형 제거 (길이가 3보다 큰 단어에 대해서만 적용)
        if (word.length() > 3) {
            if (word.endsWith("ies")) {  // -ies → -y (libraries → library)
                word = word.substring(0, word.length() - 3) + "y";
            } else if (word.endsWith("es")) {  // -es 제거 (boxes → box)
                word = word.substring(0, word.length() - 2);
            } else if (word.endsWith("s") && !word.endsWith("ss")) {  // -s 제거 (단, ss로 끝나는 단어 제외)
                word = word.substring(0, word.length() - 1);  // dogs → dog
            }
        }

        return word;
    }
}