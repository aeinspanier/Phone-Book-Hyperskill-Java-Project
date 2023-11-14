package phonebook;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchVisitor implements Visitor{

    List<Search> searchList = new ArrayList<Search>();
    Directory directory;
    List<String> names;

    public SearchVisitor(Directory directory, List<String> names) {
        this.directory = directory;
        this.names = names;
        searchList.add(new LinearSearch(directory, names));
    }

    private void startSearchMsg() {
        System.out.println("Start searching...");
    }

    private void printSearchStatistics(int found, long timeDiff) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeDiff);
        long seconds = (TimeUnit.MILLISECONDS.toSeconds(timeDiff) % 60);
        long ms = timeDiff - (minutes * 1000 * 60) - (seconds * 1000);
        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.", found, this.names.size(), minutes, seconds, ms);
    }

    public void visitAllAlgorithms() {
        for (Search search : searchList) {
            startSearchMsg();
            long startTime = System.currentTimeMillis();
            int numFound = search.accept(this);
            long endTime = System.currentTimeMillis();
            long timeDiff = endTime - startTime;
            printSearchStatistics(numFound, timeDiff);
        }
    }

    @Override
    public int visitLinearSearch(LinearSearch linSearch) {
        return linSearch.search(this.directory.getDirectoryEntries());
    }
}
