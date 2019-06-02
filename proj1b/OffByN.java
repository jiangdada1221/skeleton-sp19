public class OffByN implements CharacterComparator {
    private int i;
    public OffByN(int i){
        this.i = i;
    }
    public boolean equalChars(char x, char y){
        int j = x - y;
        if(j == i || j == -i){
            return true;
        }
        return false;
    }
}
