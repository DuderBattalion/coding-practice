import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    public static void main(String[] args) {
        String[] strs = { "eat", "tea", "tan", "ate", "nat", "bat" };

        List<List<String>> outputs = groupAnagrams(strs);
        for (List<String> output: outputs) {
            output.forEach(str -> {
                System.out.print(str + ",");
            });

            System.out.println();
        }
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagramCache = new HashMap<>();
        for (String str: strs) {
            String key = getCacheKey(str);
            if (anagramCache.containsKey(key)) {
                List<String> keyVals = anagramCache.get(key);
                keyVals.add(str);
            } else {
                List<String> keyVals = new ArrayList<>();
                keyVals.add(str);

                anagramCache.put(key, keyVals);
            }
        }

        List<List<String>> output = new ArrayList<>();
        anagramCache.forEach((k,v) -> {
            output.add(v);
        });

        return output;
    }

    private static String getCacheKey(String str) {
        return str.chars()
                .sorted()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
