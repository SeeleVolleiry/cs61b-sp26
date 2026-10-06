public class PrintIndexed {
   /**
     * Prints each character of a given string followed by the reverse of its index.
     * Example: printIndexed("hello") -> h4e3l2l1o0
     */
   public static void printIndexed(String s) {
      // TODO: Fill in this function
      int length = s.length();
      int currIndex = length - 1;
      int i = 0;
      while (currIndex >= 0) {
         System.out.print(s.charAt(i));
         System.out.print(currIndex);
         currIndex--; i++;
      }
      System.out.println();
   }

   public static void main(String[] args) {
      printIndexed("hello");
      printIndexed("cat"); // should print c2a1t0
   }
}
