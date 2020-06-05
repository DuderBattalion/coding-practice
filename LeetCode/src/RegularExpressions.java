// import java.lang.reflect.Modifier;
// import java.util.regex.Pattern;
//
// public class RegularExpressions {
//   public static void main(String[] args) {
//     String s = "aabbbccc";
//     String p = "a*b*c*";
//
//     System.out.println(isMatch(s, p));
//   }
//
//   /**
//    * '.' - match any single char
//    * '*' - use preceding char any number of times
//    * '.*' - use any char any number of times (skip modifier)
//    *
//    * Solution:
//    * Convert input string S to linked list.
//    * "aabbbccc" -> (a, 2) -> (b, 3) -> (c->3)
//    *
//    * Parse pattern P to linked list
//    * "a*b*c*" -> (a, *) -> (b, *) -> (c, *)
//    * "a*.*c*" -> (a, *) -> (., *) -> (c, *)
//    * "a*.b*c*" -> (a, *) -> (., 1) -> (b, *) -> (c, *)
//    *
//    * If ".*" encountered, peek ahead to see how much we can skip ahead
//    * In above example, we would see that next node is (c, *),
//    * so skip both lists to that node and proceed.
//    */
//   public static boolean isMatch(String s, String p) {
//     List<TextNode> textNodeList = createTextNodeList(s);
//     List<PatternNode> patternNodeList = createPatternNodeList(p);
//
//     TextNode textNode = textNodeList.get(0);
//     PatternNode pNode = patternNodeList.get(0);
//
//     boolean isMatching = true;
//
//     Operation opResult;
//     while (pNode != null) {
//       // Add any char any number of times
//       String modifier = getModifier(pNode);
//       opResult = doOperation(modifier);
//
//       if (!opResult.isMatching) {
//         isMatching = false;
//         break;
//       }
//
//       textNode = opResult.textNode;
//       pNode = opResult.pNode;
//     }
//
//     return isMatching;
//   }
//
//   private static class Operation {
//     public boolean isMatching;
//     public TextNode textNode;
//     public PatternNode pNode;
//
//     public Operation() {
//       textNode = null;
//       pNode = null;
//       isMatching = true;
//     }
//   }
//
//   private static Operation doOperation(String modifier, TextNode textNode,
//                                        PatternNode pNode) {
//       Operation opResult = new Operation();
//
//       // Case (., *)
//       if (modifier.equals("SKIP")) {
//         opResult = doSkip(textNode, pNode);
//       }
//       // Case (a, *)
//       else if (modifier.equals("MULTIPLY")) {
//         opResult = doMultiply(textNode, pNode);
//       }
//       // Case (a, .)
//       else if (modifier.equals("SUB")) {
//         opResult = doSub(textNode, pNode);
//       }
//       // Case (a)
//       else if (modifier.equals("NONE")){
//         opResult = doCompare(textNode, pNode);
//       }
//       // This shouldn't happen
//       else {
//         throw new RuntimeException("[Error]: Invalid modifier detected.");
//       }
//
//     return opResult;
//   }
//
//   private static Operation doSkip(TextNode textNode, PatternNode pNode) {
//     if (textNode == null || pNode == null) {
//       return getInvalidOperation();
//     }
//
//     Operation opResult = new Operation();
//
//     TextNode nextTextNode = textNode.next();
//     while (pNode != null && pNode.token != nextTextNode.token) {
//       pNode = pNode.next();
//     }
//
//     opResult.pNode = pNode;
//     opResult.textNode = nextTextNode;
//     opResult.isMatching = true;
//
//     return opResult;
//   }
//
//   private static Operation doMultiply(TextNode textNode, PatternNode pNode) {
//     if (textNode == null || pNode == null) {
//       return getInvalidOperation();
//     }
//
//     Operation opResult = new Operation();
//
//     TextNode nextTextNode = textNode.next();
//     if (pNode.token == textNode.token) {
//       while (pNode != null && pNode.token != nextTextNode.token) {
//         pNode = pNode.next();
//       }
//
//       opResult.pNode = pNode;
//       opResult.textNode = nextTextNode;
//       opResult.isMatching = true;
//     } else {
//       opResult.isMatching = false;
//     }
//
//     return opResult;
//   }
//
//   private static Operation doSub(TextNode textNode, PatternNode pNode) {
//     if (textNode == null || pNode == null) {
//       return getInvalidOperation();
//     }
//
//     Operation opResult = new Operation();
//
//     opResult.pNode = pNode.next();
//     opResult.textNode = textNode.next();
//     opResult.isMatching = true;
//
//     return opResult;
//   }
//
//   private static Operation doCompare(TextNode textNode, PatternNode pNode) {
//     if (textNode == null || pNode == null) {
//       return getInvalidOperation();
//     }
//
//     Operation opResult = new Operation();
//
//     opResult.textNode = textNode.next();
//     opResult.pNode = pNode.next();
//     opResult.isMatching = (pNode.token == textNode.token);
//
//     return opResult;
//   }
//
//   private static Operation getInvalidOperation() {
//     Operation invalidOp = new Operation();
//     invalidOp.isMatching = false;
//
//     return invalidOp;
//   }
//
// }
