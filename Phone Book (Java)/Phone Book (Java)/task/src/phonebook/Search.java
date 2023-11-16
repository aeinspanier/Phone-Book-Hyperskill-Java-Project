package phonebook;

import java.util.List;

public interface Search {
    void accept(Visitor visitor);
    void search();
    void printStats();
}
