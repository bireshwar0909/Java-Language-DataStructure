package DataStructure;
      /**
       * What is Union Find?
       * Union Find is a date structure that keeps track of elements which are split int one or more disjoint sets.
       *    It has two primary operations: find and union.
       * 
       * When and where is a Union Find used?
       *    A. Kruskal's Minimum spanning tree algorithm
       *    B. Grid Percolation
       *    C. Network Connectivity
       *    D. Least common ancestor in trees
       *    E. Image Processing
       * 
       * Time Complexity:
       * Construction         --  O(n)
       * Union                --  alpha(n)
       * Find                 --  alpha(n)
       * Get componet size    --  alpha(n)
       * Chech if connected   --  alpha(n)
       * Count Components     --  O(n)   
       */

       /**
        * Union Find Application: Kruskal's Minimum Spanning Tree
        *   Given a graph G = (V,E) we want to find a Minimum Spanning Tree in
        *     the graph (it may not be unique). A minimum spanning tree is a subset of the edges
        *     which connect all vertices in the graph with the minimal total edge cost.
        *     So how does it work?
        *     1. Sort the edges by ascending edge weight.
        *     2. Walk through the sorted edges and look at the two nodes the edges belongs to,
        *         if the nodes are already unified we dont include this edge, otherwise we
        *         include it and unify the nodes.
        *     3. The algorithm terminates when every edge has been proceessed or all the vertices
        *         have been unified.
        */

        /**
         * To begin using Union Find, first construct a bijection (a mapping) between your objects and the integers
         *    in range[0,n). Note: This step is not necessary in general, but it will allow us to
         *    construct an array-based union find.
         */

         /**
          * Summary
          * Find Operation: To find which component a particular element belongs to find the root of that component
          *                by following the parent nodes until a self loop is reached (a node who's parent is itself).
          * Union Operation: To unify two elements find which are the root nodes of each component and if the root nodes
          *                 are different make one of the root nodes be the parent of the other.
          * In this data structure we do not un-union elements. In general, this would be very inefficient to do since 
          * we would have to update all the children of a node.
          * The number of components is equal to the number of roots remaining. Also, remark that the number of root nodes 
          *   never increases.
          */

public class Java02UnionFind {

  // The number of elements in this union find
  private int size;

  // Used to track the size of each of the component
  private int[] sz;

  // id[i] points to the parent of i, if id[i] = i then i is a root node
  private int[] id;

  // Tracks the number of components in the union find
  private int numComponents;

  public Java02UnionFind(int size) {

    if (size <= 0) throw new IllegalArgumentException("Size <= 0 is not allowed");

    this.size = numComponents = size;
    sz = new int[size];
    id = new int[size];

    for (int i = 0; i < size; i++) {
      id[i] = i; // Link to itself (self root)
      sz[i] = 1; // Each component is originally of size one
    }
  }

  // Find which component/set 'p' belongs to, takes amortized constant time.
  public int find(int p) {

    // Find the root of the component/set
    int root = p;
    while (root != id[root]) root = id[root];

    // Compress the path leading back to the root.
    // Doing this operation is called "path compression"
    // and is what gives us amortized time complexity.
    while (p != root) {
      int next = id[p];
      id[p] = root;
      p = next;
    }

    return root;
  }

  // This is an alternative recursive formulation for the find method
  // public int find(int p) {
  //   if (p == id[p]) return p;
  //   return id[p] = find(id[p]);
  // }

  // Return whether or not the elements 'p' and
  // 'q' are in the same components/set.
  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  // Return the size of the components/set 'p' belongs to
  public int componentSize(int p) {
    return sz[find(p)];
  }

  // Return the number of elements in this UnionFind/Disjoint set
  public int size() {
    return size;
  }

  // Returns the number of remaining components/sets
  public int components() {
    return numComponents;
  }

  // Unify the components/sets containing elements 'p' and 'q'
  public void unify(int p, int q) {

    int root1 = find(p);
    int root2 = find(q);

    // These elements are already in the same group!
    if (root1 == root2) return;

    // Merge smaller component/set into the larger one.
    if (sz[root1] < sz[root2]) {
      sz[root2] += sz[root1];
      id[root1] = root2;
    } else {
      sz[root1] += sz[root2];
      id[root2] = root1;
    }

    // Since the roots found are different we know that the
    // number of components/sets has decreased by one
    numComponents--;
  }
}