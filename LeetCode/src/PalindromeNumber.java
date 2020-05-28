public class PalindromeNumber {

  public static void main(String[] args) {
    int x = -121;
    System.out.println(isPalindrome(x));
  }

  public static boolean isPalindrome(int x) {
    String xStr = String.valueOf(x);
    StringBuilder xStrRevBuf = new StringBuilder(xStr).reverse();

    return xStr.equals(xStrRevBuf.toString());
  }
}
