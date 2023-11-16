package phonebook;

public interface Visitor {
    void visitLinearSearch(LinearSearch linSearch);
    void visitJumpSearch(JumpSearch jumpSearch);
}
