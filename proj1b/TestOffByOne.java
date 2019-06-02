
import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    @Test
    public void testoffbyone() {
        assertTrue(offByOne.equalChars('a','b'));
        assertTrue(offByOne.equalChars('r','q'));
        assertFalse(offByOne.equalChars('a','u'));
        assertTrue(offByOne.equalChars('%','&'));
    }
}