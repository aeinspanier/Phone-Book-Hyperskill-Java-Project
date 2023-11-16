package phonebook;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LinearSearch implements Search {
    List<String> names;
    Directory directory;
    long startSearch;
    long endSearch;
    long searchTime;
    int found = 0;

    public LinearSearch(Directory directory, List<String> names) {
        this.names = names;
        this.directory = directory;
    }

    public void accept(Visitor visitor) {
        visitor.visitLinearSearch(this);
    }

    public void printStats() {
        this.searchTime = this.endSearch - this.startSearch;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(this.searchTime);
        long seconds = (TimeUnit.MILLISECONDS.toSeconds(this.searchTime) % 60);
        long ms = this.searchTime - (minutes * 1000 * 60) - (seconds * 1000);
        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n", this.found, this.names.size(), minutes, seconds, ms);
    }

    public void search() {
        this.startSearch = System.currentTimeMillis();
        List<DirectoryEntry> directoryEntries = this.directory.getDirectoryEntries();
        for (String name : this.names) {
            for (DirectoryEntry entry : directoryEntries) {
                if (name.equals(entry.name)) {
                    this.found++;
                }
            }
        }
        this.endSearch = System.currentTimeMillis();
    }
}
