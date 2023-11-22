package phonebook;

import java.util.List;

public class QuickSort {
    private static int partition(List<DirectoryEntry> entries, int begin, int end) {
        DirectoryEntry pivot = entries.get(end);
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            DirectoryEntry curEntry = entries.get(j);
            if (curEntry.name.compareTo(pivot.name) <= 0) {
                i++;

                DirectoryEntry tmp = entries.get(i);
                entries.set(i, curEntry);
                entries.set(j, tmp);
            }
        }

        DirectoryEntry swap = entries.get(i+1);
        entries.set(i+1, pivot);
        entries.set(end, swap);

        return i+1;
    }

    static List<DirectoryEntry> sort(List<DirectoryEntry> entries, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(entries, begin, end);

            sort(entries, begin, partitionIndex-1);
            sort(entries, partitionIndex+1, end);
        }
        return entries;
    }
}
