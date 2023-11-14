package phonebook;

import java.util.List;

public interface Search {
    int accept(Visitor visitor);
    int search(List<DirectoryEntry> directoryEntryList);
}
