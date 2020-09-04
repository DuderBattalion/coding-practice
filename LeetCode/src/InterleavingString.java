import java.util.HashSet;
import java.util.Set;

public class InterleavingString {
    public static void main(String[] args) {
//        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
//        System.out.println(isInterleave("aabcc", "dbbca", "aadbbbaccc"));
//        System.out.println(isInterleave("", "", ""));

        System.out.println(isInterleave("a", "b", "a"));
    }

    public static boolean isInterleave(String s1, String s2, String s3) {
        return isInterleave(s1, s2, s3, 0, 0, "", new HashSet<>());
    }

    private static boolean isInterleave(String s1, String s2, String s3, int i, int j,
                                        String output, Set<String> cache) {
        String key = i + "," + j;
        if (cache.contains(key)) {
            return false;
        }

        if (!s3.startsWith(output)) {
            return false;
        }

        // s1 empty
        if (i >= s1.length()) {
            output += s2.substring(j);
            return output.equals(s3);
        }

        // s2 empty
        if (j >= s2.length()) {
            output += s1.substring(i);
            return output.equals(s3);
        }

        if (output.length() == s3.length()) {
            return output.equals(s3);
        }

        // Cases:
        // 1. Add a, skip b
        // 2. Skip a, add b
        boolean isInterleaved =
                isInterleave(s1, s2, s3, i + 1, j, output + s1.charAt(i), cache) // Case 1: Add a, skip b
                || isInterleave(s1, s2, s3, i, j + 1, output + s2.charAt(j), cache); // Case 2: Skip a, add b

        cache.add(key);
        return isInterleaved;
    }
}

