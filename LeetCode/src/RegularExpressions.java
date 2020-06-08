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
//   public static boolean isMatch(String s, String p) {
//     int sIndex = 0;
//     int pIndex = 0;
//
//     boolean isMatching = true;
//     char prevChar;
//     while (sIndex < s.length()) {
//       if (s.charAt(sIndex) == p.charAt(pIndex)) {
//         prevChar = s.charAt(sIndex);
//         sIndex++;
//         pIndex++;
//
//         continue;
//       }
//
//       // Check if char sub possible
//       if (p.charAt(pIndex) == '.') {
//         prevChar = '.';
//
//         pIndex++;
//         sIndex++;
//
//         continue;
//       }
//
//       // If doesn't match, isMatching fails if next char is
//       // 1) End of sIndex
//       // 2) Not wildcard
//       if (sIndex + 1 >= s.length() || s.charAt(sIndex + 1) != '*') {
//         isMatching = false;
//         break;
//       }
//
//       // Wildcard
//       if (prevChar == '.') {
//         // Some special stuff here
//         continue;
//       } else {
//         if (s.charAt(sIndex) == prevChar) {
//
//         }
//       }
//
//
//
//
//     }
//   }
//
//
// }
