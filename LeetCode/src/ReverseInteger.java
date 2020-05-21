public class ReverseInteger {

  public static void main(String[] args) {
    int num = -123;
    System.out.println(reverse(num));
  }

  public static int reverse(int x) {
    int reverseInt = 0;
    String reverseStr = new StringBuilder(String.valueOf(x))
        .reverse()
        .toString();

    if (reverseStr.charAt(reverseStr.length() - 1) == '-') {
      // Negative number
      reverseStr = '-' + reverseStr.substring(0, reverseStr.length() - 1);
    } else {
      // Positive. Skip leading zeros.
      int i = 0;
      while (i < reverseStr.length()) {
        if (reverseStr.charAt(i) != 0) {
          break;
        }

        i++;
      }

      if (i < reverseStr.length()) {
        reverseStr = reverseStr.substring(i, reverseStr.length());
      }
    }

    try {
      reverseInt = Integer.parseInt(reverseStr);
    } catch (NumberFormatException e) {
      // Catching possible integer overflow.
      // Nothing to do, since reverseInt is already set to 0 by default.
    }

    return reverseInt;
  }
}
