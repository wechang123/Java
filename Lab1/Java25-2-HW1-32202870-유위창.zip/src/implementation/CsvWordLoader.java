package implementation;

import interfaces.IWordLoader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

// SRP: CSV 파일 로딩만 담당
// OCP: IWordLoader를 구현하여 확장에 열려있음
public class CsvWordLoader implements IWordLoader {
    
    @Override
    public List<String> loadWords(String path) throws IOException {
        String content = Files.readString(Path.of(path));
        // CSV 파일이므로 콤마로 구분하고 공백 제거
        return Arrays.asList(content.split(","));
    }
}


