import org.junit.Test;

import static org.junit.Assert.*;

public class TestUnionFInd {
    @Test
    public void test() {
        UnionFind uf = new UnionFind(10);
        assertEquals(1,uf.sizeOf(7));
        uf.union(1,2);
        uf.union(4,5);
        uf.union(3,4);
        uf.union(1,4);
        assertTrue(uf.connected(1,5));
        assertTrue(uf.connected(3,5));
        assertEquals(5,uf.sizeOf(3));
        uf.union(1,3);
        assertEquals(5,uf.sizeOf(3));
    }
}
