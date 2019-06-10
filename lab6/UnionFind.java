public class UnionFind {

    // TODO - Add instance variables?
    private int[] array; // store the elements

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        this.array = new int[n];
        for (int i = 0; i <= array.length-1; i++) {
            array[i] = -1;
        }

    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        if (vertex < 0 || vertex >= array.length) {
            throw new IllegalArgumentException();
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        validate(v1);
        int result = find(v1);
        return -array[result];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        return array[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        int parV1 = find(v1);
        int parV2 = find(v2);
        if (parV1 == parV2) {
            return;
        }else {
//            int smaller = array[parV1];
//            int bigger = array[parV2];
        if (-array[parV1] <= -array[parV2]) {
           int size = array[parV1];
           array[parV1] = parV2;
           array[parV2] += size;
        }else {
            int size = array[parV2];
            array[parV2] = parV1;
            array[parV1] += size;
        }

    }}

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        int refer;
        int result = vertex;
        while (parent(result) > 0) {
            result = parent(result);
        }
        while (array[vertex] > 0) {
            refer = vertex;
            vertex = array[vertex];
            array[refer] = result;
        }
        return result;
    }

}
