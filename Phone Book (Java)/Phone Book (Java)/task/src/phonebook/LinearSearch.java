package phonebook;

import java.util.List;

public class LinearSearch implements Search {
    Directory directory;
    List<String> names;
    public LinearSearch(Directory directory, List<String> names) {
        this.directory = directory;
        this.names = names;
    }

    public int accept(Visitor visitor) {
        return visitor.visitLinearSearch(this);
    }

    public int search(List<DirectoryEntry> directoryEntries) {
        int found = 0;
        for (String name : this.names) {
            for (DirectoryEntry entry : directoryEntries) {
                if (name.equals(entry.name)) {
                    found++;
                }
            }
        }
        return found;
    }
}
