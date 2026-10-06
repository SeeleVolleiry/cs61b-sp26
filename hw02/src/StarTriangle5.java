public class StarTriangle5 {
   /**
     * Prints a right-aligned triangle of stars ('*') with 5 lines.
     * The first row contains 1 star, the second 2 stars, and so on.
     * 最简单的不是使用循环，而是直接打印：没有参数要求、没有泛用要求。
     * 但是要求使用while或者for循环。
     */
   public static void starTriangle5() {
      // TODO: Fill in this function
      /*System.out.println("    *");
      System.out.println("   **");
      System.out.println("  ***");
      System.out.println(" ****");
      System.out.println("*****");*/
      for (int i = 1; i < 6; i++) {
         int j = 5-i;
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
      starTriangle5();
   }
}
