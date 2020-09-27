import java.util.*;

public class WordLadder2 {
    public static void main(String[] args) {
//        String beginWord = "hit";
//        String endWord = "cog";
//
//        List<String> wordList = new ArrayList<>();
//        wordList.add("hot");
//        wordList.add("dot");
//        wordList.add("dog");
//        wordList.add("lot");
//        wordList.add("log");
//        wordList.add("cog");

//        String beginWord = "a";
//        String endWord = "c";
//
//        List<String> wordList = new ArrayList<>();
//        wordList.add("a");
//        wordList.add("b");
//        wordList.add("c");

        String beginWord = "red";
        String endWord = "tax";

        List<String> wordList = new ArrayList<>();
        wordList.add("ted");
        wordList.add("tex");
        wordList.add("red");
        wordList.add("tax");
        wordList.add("tad");
        wordList.add("den");
        wordList.add("rex");
        wordList.add("pee");

        List<List<String>> results = findLadders(beginWord, endWord, wordList);
        for (List<String> result: results) {
            for (String word: result) {
                System.out.print(word + ", ");
            }

            System.out.println();
        }
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> output = new ArrayList<>();
        if (!wordList.contains(endWord)) {
            return output;
        }

        Node root = createWordGraph(beginWord, endWord, new HashSet<>(wordList));

        printGraph(root);
        return findPaths(root, endWord);
    }

    private static Node createWordGraph(String beginWord, String endWord, Set<String> wordList) {
        Set<Character> distinctChars = getDistinctChars(wordList);

        Queue<Node> frontier = new LinkedList<>();
        Node root = new Node(beginWord);
        frontier.add(root);

        Set<String> processedStates = new HashSet<>();
        while (!frontier.isEmpty()) {
            Node node = frontier.peek();
            String state = node.val;

            if (processedStates.contains(state)) {
                frontier.remove();
                continue;
            }

            if (state.equals(endWord)) {
                processedStates.add(endWord);
                frontier.remove();

                continue; // No need to transform further if endword reached
            }

            // Generate next state transitions
            generateNextStates(state, wordList, distinctChars, frontier, processedStates, node);

            processedStates.add(state);
            frontier.remove();
        }

        return root;
    }

    private static Set<Character> getDistinctChars(Set<String> wordList) {
        Set<Character> distinctChars = new HashSet<>();
        for (String word: wordList) {
            for (char token: word.toCharArray()) {
                distinctChars.add(token);
            }
        }

        return distinctChars;
    }

    private static void generateNextStates(String beginWord, Set<String> wordList,
                                           Set<Character> distinctChars, Queue<Node> frontier,
                                           Set<String> processedStates, Node node) {
        for (int i = 0; i < node.val.length(); i++) {
            for (Character token: distinctChars) {
                String newWord = replaceWord(beginWord, i, token);
                if (!newWord.equals(beginWord) && wordList.contains(newWord)
                        && !processedStates.contains(newWord)) {
                    Node newNode = new Node(newWord);
                    node.addNeighbor(newNode);

                    frontier.add(newNode);
                }
            }
        }
    }

    private static String replaceWord(String word, int i, Character token) {
        return word.substring(0, i) + token + word.substring(i+1);
    }

    private static class Node {
        public String val;
        public List<Node> neighbors;

        public Node(String val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }

        public void addNeighbor(Node node) {
            neighbors.add(node);
        }
    }

    private static List<List<String>> findPaths(Node root, String endWord) {
        List<List<String>> output = new ArrayList<>();
        List<String> currentChain = new ArrayList<>();
        currentChain.add(root.val);

        findPathsRecursive(root, endWord, output, currentChain, 0, new DepthTracker(Integer.MAX_VALUE));

        return output;
    }

    private static class DepthTracker {
        public int maxDepth;

        public DepthTracker(int maxDepth) {
            this.maxDepth = maxDepth;
        }
    }

    private static void findPathsRecursive(Node node, String endWord, List<List<String>> output,
                                           List<String> currentChain, int depth, DepthTracker depthTracker) {
        if (node == null || depth > depthTracker.maxDepth) {
            return;
        }

        if (node.val.equals(endWord)) {
            output.add(new ArrayList<>(currentChain));

            if (depth < depthTracker.maxDepth) {
                depthTracker.maxDepth = depth;

                filterLongerPaths(output, depth);
            }

            return;
        }

        for (Node neighbor: node.neighbors) {
            currentChain.add(neighbor.val);
            findPathsRecursive(neighbor, endWord, output, currentChain, depth + 1, depthTracker);

            // Backtrack
            currentChain.remove(currentChain.size()-1);
        }
    }

    private static void filterLongerPaths(List<List<String>> output, int depth) {
        output.removeIf(result -> (result.size() - 1) > depth); // -1 because depth is zero index'd
    }

    private static void printGraph(Node root) {
        Queue<Node> frontier = new LinkedList<>();
        frontier.add(root);

        while (!frontier.isEmpty()) {
            Node node = frontier.remove();

            System.out.println("Node id:" + node.toString());
            System.out.println("Node val: " + node.val);
            System.out.println("Node neighbors");
            for (Node neighbor: node.neighbors) {
                System.out.printf("%s (%s), ", neighbor.val, neighbor.toString());
                frontier.add(neighbor);
            }

            System.out.println("");
            System.out.println();
        }
    }
}

