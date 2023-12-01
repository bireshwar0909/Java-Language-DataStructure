package DataStructure;
        // Arrays one of the most used data structures.
        // the reason arrays are used so much because it creates the fundamental building
        //      block of other data structure.
        // with arrays and pointers alone we can almost create any data structure.
        
        // so what is an static array?
        // a static array is a fixed length container containing n elements indexable from the
        //      the range [0, n-1]
        // Static arrays are given contagious chunks of memory.

        /** 
         * When and where is a static array used?
         * They are used almost everywhere. 
         *      A. Storting and accessing sequential data
         *      B. Temporarily storting objects
         *      C. Used by IO routines as buffers
         *      D. Lookup tables and inverse lookup tables
         *      E. Can be used to return multiple values from a function
         *      F. Used in dynamic programming to cache answers to subproblems
         */

        /**
         * time complexity:
         *            Static Array   Dynamic Array
         *  Access    --   O(1)   --      O(1)
         *  Search    --   O(n)   --      O(n)
         *  Insertion --   N/A    --      O(n)
         *  Appending --   N/A    --      O(1)
         *  Deletion  --   N/A    --      O(n)
         */

        /**
         * What are dynamic arrays?
         * These are the arrays which can grow and shrink in size with time and with our needs.
         * So how can we create an dyanmic array?
         *      one way is to use a static array.
         *      and thats by creating a static array with a initial capacity and later 
         *      adding elements to the underlying static array, keeping track of the number of elements.
         *      If adding another element will exceed the capacity, then create a new static array with 
         *      twice the capacity and copy the original elements into it.
         */

        public class Java01Arrays<T> implements Iterable<T> {

            private T[] arr;
            private int len = 0; // length user thinks array is
            private int capacity = 0; // Actual array size
          
            public Java01Arrays() {
              this(16);
            }
          
            public Java01Arrays(int capacity) {
              if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
              this.capacity = capacity;
              arr = (T[]) new Object[capacity];
            }
          
            public int size() {
              return len;
            }
          
            public boolean isEmpty() {
              return size() == 0;
            }
          
            public T get(int index) {
              return arr[index];
            }
          
            public void set(int index, T elem) {
              arr[index] = elem;
            }
          
            public void clear() {
              for (int i = 0; i < len; i++) arr[i] = null;
              len = 0;
            }
          
            public void add(T elem) {
          
              // Time to resize!
              if (len + 1 >= capacity) {
                if (capacity == 0) capacity = 1;
                else capacity *= 2; // double the size
                T[] new_arr = (T[]) new Object[capacity];
                for (int i = 0; i < len; i++) new_arr[i] = arr[i];
                arr = new_arr; // arr has extra nulls padded
              }
          
              arr[len++] = elem;
            }
          
            // Removes an element at the specified index in this array.
            public T removeAt(int rm_index) {
              if (rm_index >= len || rm_index < 0) throw new IndexOutOfBoundsException();
              T data = arr[rm_index];
              T[] new_arr = (T[]) new Object[len - 1];
              for (int i = 0, j = 0; i < len; i++, j++)
                if (i == rm_index) j--; // Skip over rm_index by fixing j temporarily
                else new_arr[j] = arr[i];
              arr = new_arr;
              capacity = --len;
              return data;
            }
          
            public boolean remove(Object obj) {
              int index = indexOf(obj);
              if (index == -1) return false;
              removeAt(index);
              return true;
            }
          
            public int indexOf(Object obj) {
              for (int i = 0; i < len; i++) {
                if (obj == null) {
                  if (arr[i] == null) return i;
                } else {
                  if (obj.equals(arr[i])) return i;
                }
              }
              return -1;
            }
          
            public boolean contains(Object obj) {
              return indexOf(obj) != -1;
            }
          
            // Iterator is still fast but not as fast as iterative for loop
            @Override
            public java.util.Iterator<T> iterator() {
              return new java.util.Iterator<T>() {
                int index = 0;
          
                @Override
                public boolean hasNext() {
                  return index < len;
                }
          
                @Override
                public T next() {
                  return arr[index++];
                }
          
                @Override
                public void remove() {
                  throw new UnsupportedOperationException();
                }
              };
            }
          
            @Override
            public String toString() {
              if (len == 0) return "[]";
              else {
                StringBuilder sb = new StringBuilder(len).append("[");
                for (int i = 0; i < len - 1; i++) sb.append(arr[i] + ", ");
                return sb.append(arr[len - 1] + "]").toString();
              }
            }
          }
        