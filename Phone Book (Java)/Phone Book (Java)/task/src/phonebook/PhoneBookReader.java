package phonebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PhoneBookReader {
    public List<String> readLines(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException ex) {
            System.out.println("reading " + filePath + " failed - IOException");
        }
        return null;
    }
}
