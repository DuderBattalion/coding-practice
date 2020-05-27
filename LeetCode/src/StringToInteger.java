public class StringToInteger {

  public static void main(String[] args) {
    String str = "2000000000000000";
    System.out.println(myAtoi(str));
  }

  public static int myAtoi(String str) {
    str = getNumOnly(str);

    if (str.isEmpty()) {
      return 0;
    }

    char firstChar = str.charAt(0);
    if (str.length() == 1) {
      if (isNumeric(firstChar)) {
        return Integer.parseInt(str.substring(0, 1));
      } else {
        return 0;
      }
    }

    int num;
    try {
      num = Integer.parseInt(str);
    } catch (NumberFormatException e) {
      // Integer too large to parse
      if (firstChar == '+' || isNumeric(firstChar)) {
        num = Integer.MAX_VALUE;
      } else if (firstChar == '-') {
        num = Integer.MIN_VALUE;
      } else {
        num = 0;
      }
    }

    return num;
  }

  private static boolean isNumeric(char c) {
    return (c >= 48 && c <= 57);
  }

  private static String getNumOnly(String s) {
    s = s.trim();

    if (s.isEmpty()) {
      return s;
    }

    StringBuilder buf = new StringBuilder();
    char firstChar = s.charAt(0);
    if (isNumeric(firstChar) || firstChar == '+' || firstChar == '-') {
      buf.append(String.valueOf(firstChar));
    } else {
      return "0";
    }

    char c;
    for (int i = 1; i < s.length(); i++) {
      c = s.charAt(i);
      if (isNumeric(c)) {
        buf.append(String.valueOf(c));
      } else {
        break;
      }
    }

    return buf.toString();
  }


}
