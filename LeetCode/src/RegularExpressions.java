import java.util.regex.Pattern;

public class RegularExpressions {
  public static void main(String[] args) {
    String s = "aabbbccc";
    String p = "a*b*c*";

    System.out.println(isMatch(s, p));
  }

  /**
   * '.' - match any single char
   * '*' - use preceding char any number of times
   * '.*' - use any char any number of times (skip modifier)
   *
   * Solution:
   * Convert input string S to linked list.
   * "aabbbccc" -> (a, 2) -> (b, 3) -> (c->3)
   *
   * Parse pattern P to linked list
   * "a*b*c*" -> (a, *) -> (b, *) -> (c, *)
   * "a*.*c*" -> (a, *) -> (., *) -> (c, *)
   * "a*.b*c*" -> (a, *) -> (., 1) -> (b, *) -> (c, *)
   *
   * If ".*" encountered, peek ahead to see how much we can skip ahead
   * In above example, we would see that next node is (c, *),
   * so skip both lists to that node and proceed.
   */
  public static boolean isMatch(String s, String p) {
    List<TextNode> textNodeList = createTextNodeList(s);
    List<PatternNode> patternNodeList = createPatternNodeList(p);

    TextNode textNode = textNodeList.get(0);
    PatternNode pNode = patternNodeList.get(0);
    while (pNode != null) {
      // Add any char any number of times
      Modified modifier = getModifier(pNode);

      // Case (., *)
      if (modifier.equals("SKIP")) {
        SkipResult skipResult = skipAhead(textNode, pNode);
        textNode = skipResult.textNode;
        pNode = skipResult.patternNode;
      }
      // Case (a, *)
      else if (modifier.equals("MULTIPLY")) {

      }
      // Case (a, .)
      else if (modifier.equals("SUB")) {

      }
      // Case (a)
      else if (modifier.equals("NONE")){

      }
      // This shouldn't happen
      else {
        throw new RuntimeException("[Error]: Invalid modifier detected.");
      }
    }
  }
}
