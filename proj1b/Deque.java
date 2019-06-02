public interface Deque <jyp>{
    void addFirst(jyp item);
    void addLast(jyp item);
    default boolean isEmpty(){
        if (size() == 0) {
            return true;
        }
        return false;
    }
    int size();
    void printDeque();
    jyp removeFirst();
    jyp removeLast();
    jyp get(int inded);
}
