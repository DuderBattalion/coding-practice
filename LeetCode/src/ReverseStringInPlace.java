public class ReverseStringInPlace {

  public static void main(String[] args) {
    char[] s = {'h', 'e', 'l', 'l', 'o'};
    reverseString(s);
  }

  public static void reverseString(char[] s) {
    if (s.length < 1) {
      return;
    }

    int start = 0;
    int end = s.length - 1;
    char temp;
    while (start < end) {
      // Swap start and end
      temp = s[start];
      s[start] = s[end];
      s[end] = temp;

      start++;
      end--;
    }

    System.out.println(String.valueOf(s));
  }
}
