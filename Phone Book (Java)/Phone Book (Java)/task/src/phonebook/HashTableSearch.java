package phonebook;

import java.util.HashMap;
import java.util.List;

public class HashTableSearch implements Search{
    List<String> names;
    Directory directory;
    HashMap<String, DirectoryEntry> hashTable = new HashMap<String, DirectoryEntry>();
    long createTime;
    long searchTime;
    int found = 0;

    public HashTableSearch(Directory directory, List<String> names) {
        this.names = names;
        this.directory = directory;
    }

    public void accept(Visitor visitor) {
        visitor.visitHashTableSearch(this);
    }

    public void printStats() {
        TimeSpan totalTimeSpan = new TimeSpan(this.createTime + this.searchTime);
        TimeSpan createTimeSpan = new TimeSpan(this.createTime);
        TimeSpan searchTimeSpan = new TimeSpan(this.searchTime);
        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n", this.found, this.names.size(), totalTimeSpan.minutes, totalTimeSpan.seconds, totalTimeSpan.ms);
        String sortMsg = String.format("Creating time: %d min. %d sec. %d ms.", createTimeSpan.minutes, createTimeSpan.seconds, createTimeSpan.ms);
        System.out.println(sortMsg);
        System.out.printf("Searching Time: %d min. %d sec. %d ms.\n", searchTimeSpan.minutes, searchTimeSpan.seconds, searchTimeSpan.ms);
    }

    private void createHashMap() {
        List<DirectoryEntry> entries = this.directory.getDirectoryEntries();
        for (DirectoryEntry entry : entries) {
            this.hashTable.put(entry.name, entry);
        }
    }

    private int searchHashMap(String name) {
        if (this.hashTable.get(name) != null) {
            return 1;
        }
        return 0;
    }

    public void search(){
        long startCreate = System.currentTimeMillis();
        createHashMap();
        this.createTime = System.currentTimeMillis() - startCreate;

        long startSearch = System.currentTimeMillis();
        for (String name : this.names) {
            this.found += searchHashMap(name);
        }
        this.searchTime = System.currentTimeMillis() - startSearch;
    }
}
