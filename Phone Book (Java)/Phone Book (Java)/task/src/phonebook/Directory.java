package phonebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Directory {
    List<DirectoryEntry> directoryEntries = new ArrayList<DirectoryEntry>();
    public Directory(List<String> entries) {
        for (String entry : entries) {
            String[] splitEntry = entry.split(" ", 2);
            directoryEntries.add(new DirectoryEntry( splitEntry[1], Integer.parseInt(splitEntry[0])));
        }
    }

    public List<DirectoryEntry> getDirectoryEntries() {
        return this.directoryEntries;
    }

}
