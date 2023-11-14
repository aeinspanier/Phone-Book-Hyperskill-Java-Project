package phonebook;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PhoneBookReader fileReader = new PhoneBookReader();
        String directoryPath = "C:\\Users\\aeins\\Documents\\phonebook_data\\small_directory.txt";
        String searchPath = "C:\\Users\\aeins\\Documents\\phonebook_data\\small_find.txt";

        List<String> directoryLines = fileReader.readLines(directoryPath);
        List<String> namesToSearch = fileReader.readLines(searchPath);

        Directory directory = new Directory(directoryLines);
        SearchVisitor searchVisitor = new SearchVisitor(directory, namesToSearch);

        searchVisitor.visitAllAlgorithms();

    }
}
