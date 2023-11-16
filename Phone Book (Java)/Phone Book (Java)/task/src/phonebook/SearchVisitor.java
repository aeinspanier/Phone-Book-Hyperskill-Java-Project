package phonebook;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchVisitor implements Visitor{

    HashMap<String, Search> searches = new HashMap<>();
    String[] searchOrder = {"LinearSearch", "JumpSearch"};

    public SearchVisitor(Directory directory, List<String> names) {
        searches.put("LinearSearch", new LinearSearch(directory, names));
        searches.put("JumpSearch", new JumpSearch(directory, names));
    }

    public void visitAllAlgorithms() {
        for (String searchName : this.searchOrder) {
            Search search = this.searches.get(searchName);
            search.accept(this);
            System.out.println();
        }
    }

    @Override
    public void visitLinearSearch(LinearSearch linSearch) {
        System.out.println("Start searching (linear search)...");
        linSearch.search();
        linSearch.printStats();
        JumpSearch jumpSearch = (JumpSearch) this.searches.get("JumpSearch");
        jumpSearch.setMaxBubbleSortTime(linSearch.searchTime);
    }

    @Override
    public void visitJumpSearch(JumpSearch jumpSearch) {
        System.out.println("Start searching (bubble sort + jump search)...");
        jumpSearch.search();
        jumpSearch.printStats();
    }
}
