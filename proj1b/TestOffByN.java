import static org.junit.Assert.*;

import org.junit.Test;

public class TestOffByN {
    @Test
    public void testOffByN() {
        OffByN on6 = new OffByN(6);
        OffByN on7 = new OffByN(7);
        assertTrue(on6.equalChars('a','g'));
        assertTrue(on7.equalChars('a','h'));
        assertFalse(on6.equalChars('a','b'));
        assertFalse(on7.equalChars('a','&'));
    }
}
