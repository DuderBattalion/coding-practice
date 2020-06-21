import java.util.ArrayList;
import java.util.List;

public class GenerateParanthesis {
    public static void main(String[] args) {
        List<String> output = generateParenthesis(3);
        output.forEach(str -> System.out.println(str));
    }

    public static List<String> generateParenthesis(int n) {
        return recurseGenParenthesis("", n, 0, 0, new ArrayList<String>());
    }

    public static List<String> recurseGenParenthesis(String text, int n,
                                                     int open, int close,
                                                     List<String> output) {
        if (close > open || close > n || open > n) {
            return output;
        }

        if (open == n && close == n) {
            output.add(text);
            return output;
        }

        // Two possibilities
        // Case a: Open bracket
        output = recurseGenParenthesis(text + "(", n, open + 1, close, output);

        // Case b: Close bracket
        output = recurseGenParenthesis(text + ")", n, open, close + 1, output);

        return output;
    }
}
