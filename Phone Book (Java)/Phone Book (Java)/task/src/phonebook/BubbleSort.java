package phonebook;

import java.util.ArrayList;
import java.util.List;

public class BubbleSort {
    static List<DirectoryEntry> sort(List<DirectoryEntry> entries, long timeout) {
        List<DirectoryEntry> sortedEntries = new ArrayList<DirectoryEntry>(entries);
        int n = sortedEntries.size();
        boolean swapped;
        DirectoryEntry next;
        DirectoryEntry cur;

        long startTime = System.currentTimeMillis();

        for(int i=0; i < n-1; i++){
            swapped = false;
            if ((System.currentTimeMillis() - startTime) > timeout) {
                return new ArrayList<>();
            }
            for(int j=0; j < n-i-1; j++){
                cur = sortedEntries.get(j);
                next = sortedEntries.get(j+1);
                if(cur.name.compareTo(next.name) > 0) {
                    swapped = true;
                    sortedEntries.set(j, next);
                    sortedEntries.set(j + 1, cur);
                }
            }
            if (!swapped) break;
        }
        return sortedEntries;
    }
}
