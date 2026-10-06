public class StarTriangleN {
   /**
     * Prints a right-aligned triangle of stars ('*') with N lines.
     * The first row contains 1 star, the second 2 stars, and so on.
     */
   public static void starTriangle(int N) {
      // TODO: Fill in this function
      for (int i = 1; i <= N; i++) {
         int j = N - i;
         while(j > 0) {
            System.out.print(" ");
            j--;
         }
         int k = i;
         while(k > 0) {
            System.out.print("*");
            k--;
         }
         System.out.println();
      }
   }

   public static void main(String[] args) {
      starTriangle(7);
   }
}
