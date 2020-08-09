import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPath {
    public static void main(String[] args) {
//        String path = "/home/";
//        String path = "/../";
        String path = "/home//foo/";
//        String path = "/a/./b/../../c/";
//        String path = "/a/../../b/../c//.//";
//        String path = "/a//b////c/d//././/..";
//        String path = "";

        System.out.println(simplifyPath(path));
    }

    public static String simplifyPath(String path) {
        if (path.isEmpty()) {
            return "";
        }

        path = path.replaceAll("//", "/");

        char lastChar = path.charAt(path.length() - 1);
        if (lastChar == '/') {
            path = path.substring(0, path.length() - 1);
        }

        Deque<String> pathStack = new ArrayDeque<>();
        String[] pathTokens = path.split("/");
        for (String token: pathTokens) {
            if (token.equals("..")) {
                if (!pathStack.isEmpty()) {
                    pathStack.pop();
                }
            } else if (!token.isEmpty() && !token.equals(".")){
                pathStack.push(token);
            }
        }

        // Note - path structure stored in Stack - in reverse
        // Pop back into another stack to get the correct output format
        Deque<String> outputStack = new ArrayDeque<>();
        while (!pathStack.isEmpty()) {
            outputStack.push(pathStack.pop());
        }

        StringBuilder output = new StringBuilder();
        while (!outputStack.isEmpty()) {
            output.append("/");
            output.append(outputStack.pop());
        }

        if (output.length() == 0) {
            output.append("/");
        }

        return output.toString();
    }
}
