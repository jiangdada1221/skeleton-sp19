public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> result = new LinkedListDeque<>();
        int length = word.length();
        for (int i = 0; i <= length - 1; i++) {
            result.addLast(word.charAt(i));
        }
        return result;
    }
    private boolean helper(Deque<Character> de){
        if (de.size() <= 1){
            return true;
        }else {
            char first = de.removeFirst();
            char back = de.removeLast();
            if (first != back) {
                return false;
            }
            return helper(de);
        }
    }
    private boolean helper(Deque<Character> de, CharacterComparator cc){
        if (de.size() <= 1){
            return true;
        }else {
            char first = de.removeFirst();
            char back = de.removeLast();
            if (!cc.equalChars(first,back)){
                return false;
            }
            return helper(de,cc);
        }
    }
    public boolean isPalindrome(String word) {
        Deque<Character> de = wordToDeque(word);
        return helper(de);
    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> de = wordToDeque(word);
        return helper(de,cc);
    }
//    public static void main(String[] args) {
//        int i = 'a' - 'b';
//        System.out.println(i);
//    }
}
