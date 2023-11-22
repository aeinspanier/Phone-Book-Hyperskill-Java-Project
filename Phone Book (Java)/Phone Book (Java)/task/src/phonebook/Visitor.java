package phonebook;

public interface Visitor {
    void visitLinearSearch(LinearSearch linSearch);
    void visitJumpSearch(JumpSearch jumpSearch);
    void visitBinarySearch(BinarySearch binSearch);
    void visitHashTableSearch(HashTableSearch hashSearch);
}
