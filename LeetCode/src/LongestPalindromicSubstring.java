import java.util.HashSet;
import java.util.Set;

public class LongestPalindromicSubstring {

  public static void main(String[] args) {
    // String text = "cbbd";
    // String text = "vaomxdtiuwqlwhgutkhxxhccsgvyoaccuicgybnqnslogtqhblegfudagpxfvjdacsxgevvepuwthdtybgflsxjdmmfumyqgpxatvdypjmlapccaxwkuxkilqqgpihyepkilhlfkdrbsefinitdcaghqmhylnixidrygdnzmgubeybczjceiybowglkywrpkfcwpsjbkcpnvfbxnpuqzhotzspgebptnhwevbkcueyzecdrjpbpxemagnwmtwikmkpqluwmvyswvxghajknjxfazshsvjkstkezdlbnkwxawlwkqnxghjzyigkvqpapvsntojnxlmtywdrommoltpbvxwqyijpkirvndwpgufgjelqvwffpuycqfwenhzrbzbdtupyutgccdjyvhptnuhxdwbmdcbpfvxvtfryszhaakwshrjseonfvjrrdefyxefqfvadlwmedpvnozobftnnsutegrtxhwitrwdpfienhdbvvykoynrsbpmzjtotjxbvemgoxreiveakmmbbvbmfbbnyfxwrueswdlxvuelbkrdxlutyukppkzjnmfmclqpkwzyylwlzsvriwomchzzqwqglpflaepoxcnnewzstvegyaowwhgvcwjhbbstvzhhvghigoazbjiikglbqlxlccrwqvyqxpbtpoqjliziwmdkzfsrqtqdkeniulsavsfqsjwnvpprvczcujihoqeanobhlsvbzmgflhykndfydbxatskf";
    String text = "miycvxmqggnmmcwlmizfojwrurwhwygwfykyefxbgveixykdebenzitqnciigfjgrzzbtgeazyrbiirmejhdwcgjzwqolrturjlqpsgunuqerqjevbheblmbvgxyedxshswsokbhzapfuojgyfhctlaifrisgzqlczageirnukgnmnbwogknyyuynwsuwbumdmoqwxprykmazghcpmkdcjduepjmjdxrhvixxbfvhybjdpvwjbarmbqypsylgtzyuiqkexgvirzylydrhrmuwpmfkvqllqvekyojoacvyrzjevaupypfrdguhukzuqojolvycgpjaendfetkgtojepelhcltorueawwjpltehbbjrvznxhahtuaeuairvuklctuhcyzomwrrznrcqmovanxmiyilefybkbveesrxkmqrqkowyrimuejqtikcjfhizsmumajbqglxrvevexnleflocxoqgoyrzgqflwiknntdcykuvdcpzlakljidclhkllftxpinpvbngtexngdtntunzgahuvfnqjedcafzouopiixw";
    // System.out.println(String.format("Result: %s", longestPalindrome(text)));
  }

  // public static String longestPalindrome(String s) {
  //   String longestSubStr = "";
  //
  //   int subStrLen = s.length();
  //   String subStr = "";
  //   int start;
  //   int end;
  //
  //   Set<String> palindromeCache = new HashSet<>();
  //   while (subStrLen > 0) {
  //     start = 0;
  //     end = start + subStrLen;
  //     while (end <= s.length()) {
  //       subStr = s.substring(start, end);
  //       if (isPalindrome(subStr)) {
  //         longestSubStr = subStr;
  //         break;
  //       }
  //
  //       start++;
  //       end++;
  //     }
  //
  //     if (longestSubStr.length() > 0) {
  //       break;
  //     }
  //
  //     subStrLen--;
  //   }
  //
  //   return longestSubStr;
  // }

  // private static boolean isPalindrome(String s) {
  //   String sReverse = new StringBuilder(s).reverse().toString();
  //   return s.equals(sReverse);
  // }

  /**
   * Dynamic programming solution:
   * P(X, Y) = Is Palindrome from position X to Y (true/false)
   * S(i) = String at index i
   * P(i, j) = P(i + 1, j - 1) && S(i) == S(j)
   *
   * Build out solution from base case.
   */
  // public static String longestPalindrome(String s) {
  //   int[][] cache = new int[s.length()][s.length()];
  //
  //
  //
  // }


}
