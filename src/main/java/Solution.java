public class Solution {
    public int minScore(int n, int[][] roads) {
        UnionFind uf = new UnionFind(n);
        for(int i = 0; i < roads.length; i++){
            uf.unify(roads[i][0] - 1, roads[i][1] - 1);
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < roads.length; i++){
            if(hasPath(uf, roads[i][0] - 1, roads[i][1] - 1, n)){
                min = Math.min(min, roads[i][2]);
            }
        }
        return min;
    }
    boolean hasPath(UnionFind uf, int node1, int node2, int n){
        if((uf.isConnected(0, node1) && uf.isConnected(node2, n - 1)) || (uf.isConnected(0, node2) && uf.isConnected(node1, n - 1))){
            return true;
        }
        return false;
    }
}
class UnionFind {
    int edges;
    int[] parent;
    int[] sizes;
    int numberOfElements;

    public UnionFind(int edges) {
        this.edges = edges;
        parent = new int[edges];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        sizes = new int[edges];
        Arrays.fill(sizes, 1);
        numberOfElements = edges;

    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public boolean isConnected(int node1, int node2) {
        return find(node1) == find(node2);
    }

    public int find(int p) {

        int root = p;
        while (root != parent[root]) root = parent[root];

        while (p != root) {
            int next = parent[p];
            parent[p] = root;
            p = next;
        }

        return root;
    }

    public boolean unify(int node1, int node2) {
        if (isConnected(node1, node2)) {
            return false;
        }

        int parent1 = find(node1);
        int parent2 = find(node2);

        if (sizes[parent1] < sizes[parent2]) {
            sizes[parent2] += sizes[parent2];
            parent[parent1] = parent2;
            sizes[parent1] = 0;
        } else {
            sizes[parent1] += sizes[parent2];
            parent[parent2] = parent1;
            sizes[parent2] = 0;
        }
        numberOfElements--;
        return true;
    }

}
