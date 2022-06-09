class Main {
  /**
    * For an NxM array, find the path which has the largest sum of the elements along that path given the constraint:
    *   - From a element, you can only move to the upper-right, right or lower-right
    * @param N The row count of the array
    * @param M The column count of the array
    * @param A An NxM array of integers
    * @return the maximum path sum
    */
  public static int maxSum(int N, int M, int[][] A) {
    int max = 0;
    int[][] memoized = new int[N][M];

    for (int i = 0; i < N; i++) {
      memoized[i][0] = A[i][M-1];
    }

    for (int j = 1; j < M; j++) {
      for (int i = 0; i < N; i++) {
        memoized[i][j] = A[i][M-j-1];
        memoized[i][j] += max(
          (i > 0) ? memoized[i-1][j-1] : Integer.MIN_VALUE,
          memoized[i][j-1],
          (i < N-1) ? memoized[i+1][j-1] : Integer.MIN_VALUE
        );
      }
    }

    max = memoized[0][M-1];
    for (int i = 1; i < N; i++) {
      max = Math.max(max, memoized[i][M-1]);
    }
    return max;
  }
  
  /**
    * A three argument extension of the Math.max function
    * @param A, B, C The three integers to find the sum of
    * @returns The maximum of A,B and C
    */
  public static int max(int A, int B, int C) {
    int largest = Math.max(A, B);

    return Math.max(largest, C);
  }

    /*
    ================================
    N M |        A          | Output
    ================================
    3 3 | 1 3 3 2 1 4 0 6 4 | 12
    3 3 | 1 2 3 4 5 6 0 6 4 | 16
    3 3 | 1 2 3 4 5 6 7 8 9 | 24
    3 3 | 1 0 1 0 1 0 1 1 1 | 3
    3 3 | 1 1 1 1 1 1 1 1 1 | 3
    3 3 | 1 2 1 1 1 1 1 2 1 | 4
  */
  public static void main(String[] args) {
    int[][] A = new int[][] {
      {1, 2, 1},
      {1, 1, 1},
      {1, 2, 1}
    };

    System.out.println(maxSum(A.length,A[0].length, A));
  }
}
