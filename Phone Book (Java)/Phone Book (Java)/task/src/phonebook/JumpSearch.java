package phonebook;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class JumpSearch implements Search {
    List<String> names;
    Directory directory;
    long maxBubbleSortTime;
    boolean sortStopped = false;
    long sortTime;
    long searchTime;
    int found = 0;

    public JumpSearch(Directory directory, List<String> names) {
        this.names = names;
        this.directory = directory;
    }

    public void accept(Visitor visitor) {
        visitor.visitJumpSearch(this);
    }

    public void printStats() {
        TimeSpan totalTimeSpan = new TimeSpan(this.sortTime + this.searchTime);
        TimeSpan sortedTimeSpan = new TimeSpan(this.sortTime);
        TimeSpan searchTimeSpan = new TimeSpan(this.searchTime);
        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n", this.found, this.names.size(), totalTimeSpan.minutes, totalTimeSpan.seconds, totalTimeSpan.ms);
        String sortMsg = String.format("Sorting Time: %d min. %d sec. %d ms.", sortedTimeSpan.minutes, sortedTimeSpan.seconds, sortedTimeSpan.ms);
        if (this.sortStopped) {
            sortMsg += " - STOPPED, moved to linear search";
        }
        System.out.println(sortMsg);
        System.out.printf("Searching Time: %d min. %d sec. %d ms.\n", searchTimeSpan.minutes, searchTimeSpan.seconds, searchTimeSpan.ms);
    }

    int jumpSearchForName(List<DirectoryEntry> directoryEntryList, String name) {
        int prev = 0;
        int n = directoryEntryList.size();
        int step = (int) Math.floor(Math.sqrt(n));
        int ptr = step;

        //loop until current element is less than the given search element
        while (directoryEntryList.get(Math.min(ptr, n) - 1).name.compareTo(name) < 0) {
            prev = ptr;
            ptr += step;
            if (prev >= n) return 0;
        }

        //perform linear search prev index element to given element
        while (directoryEntryList.get(prev).name.compareTo(name) < 0) {
            prev++;
            if (prev == Math.min(ptr, n)) return 0;
        }

        // Return index if element is found
        if (directoryEntryList.get(prev).name.compareTo(name) == 0) return 1;

        return 0;

    }

    public void search(){
        long startSort = System.currentTimeMillis();
        List<DirectoryEntry> sortedEntries = BubbleSort.sort(this.directory.getDirectoryEntries(), this.maxBubbleSortTime);
        long endSort = System.currentTimeMillis();

        if (sortedEntries.isEmpty()){
            this.sortStopped = true;
        }

        this.sortTime = endSort - startSort;

        if (this.sortStopped) {
            LinearSearch linSearch = new LinearSearch(this.directory, this.names);
            linSearch.search();
            this.searchTime = linSearch.endSearch - linSearch.startSearch;
            this.found = linSearch.found;
        }else {
            long startSearch = System.currentTimeMillis();
            for (String name : this.names) {
                this.found += jumpSearchForName(sortedEntries, name);
            }
            this.searchTime = System.currentTimeMillis() - startSearch;
        }
    }

    public void setMaxBubbleSortTime(long linearSearchTime) {
        this.maxBubbleSortTime = linearSearchTime * 10;
    }
}
