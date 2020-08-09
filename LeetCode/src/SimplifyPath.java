import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPath {
    public static void main(String[] args) {
//        String path = "/home/";
//        String path = "/../";
//        String path = "/home//foo/";
//        String path = "/a/./b/../../c/";
//        String path = "/a/../../b/../c//.//";
        String path = "/a//b////c/d//././/..";

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
                if (pathStack.isEmpty()) {
                    break;
                }

                pathStack.pop();
            } else if (!token.isEmpty() && !token.equals(".")){
                pathStack.push(token);
            }
        }

        StringBuilder outputBuf = new StringBuilder();
        while (!pathStack.isEmpty()) {
            outputBuf.append("/");
            outputBuf.append(pathStack.pop());
        }

        String output = outputBuf.reverse().toString();
        if (output.isEmpty()) {
            output = "/";
        }

        return output;
    }
}
