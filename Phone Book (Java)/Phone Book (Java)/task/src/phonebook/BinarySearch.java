package phonebook;

import java.util.List;

public class BinarySearch implements Search{
    List<String> names;
    Directory directory;
    long sortTime;
    long searchTime;
    int found = 0;

    public BinarySearch(Directory directory, List<String> names) {
        this.names = names;
        this.directory = directory;
    }

    public void accept(Visitor visitor) {
        visitor.visitBinarySearch(this);
    }

    public void printStats() {
        TimeSpan totalTimeSpan = new TimeSpan(this.sortTime + this.searchTime);
        TimeSpan sortedTimeSpan = new TimeSpan(this.sortTime);
        TimeSpan searchTimeSpan = new TimeSpan(this.searchTime);
        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n", this.found, this.names.size(), totalTimeSpan.minutes, totalTimeSpan.seconds, totalTimeSpan.ms);
        String sortMsg = String.format("Sorting Time: %d min. %d sec. %d ms.", sortedTimeSpan.minutes, sortedTimeSpan.seconds, sortedTimeSpan.ms);
        System.out.println(sortMsg);
        System.out.printf("Searching Time: %d min. %d sec. %d ms.\n", searchTimeSpan.minutes, searchTimeSpan.seconds, searchTimeSpan.ms);
    }

    private int binarySearchForName(List<DirectoryEntry> entries, String name) {
        int l = 0;
        int r = entries.size() - 1;

        while (l <= r) {
            int mid = (l+r)/2;
            DirectoryEntry midEntry = entries.get(mid);
            if(midEntry.name.compareTo(name) == 0) {
                return 1;
            } else if (midEntry.name.compareTo(name) < 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return 0;
    }

    public void search(){
        long startSort = System.currentTimeMillis();
        List<DirectoryEntry> entries = this.directory.getDirectoryEntries();
        List<DirectoryEntry> sortedEntries = QuickSort.sort(entries, 0, entries.size()-1);
        this.sortTime = System.currentTimeMillis() - startSort;

        long startSearch = System.currentTimeMillis();
        for (String name : this.names) {
            this.found += binarySearchForName(sortedEntries, name);
        }
        this.searchTime = System.currentTimeMillis() - startSearch;
    }
}
