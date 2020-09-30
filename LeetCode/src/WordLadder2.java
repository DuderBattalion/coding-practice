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

//        String beginWord = "red";
//        String endWord = "tax";
//
//        List<String> wordList = new ArrayList<>();
//        wordList.add("ted");
//        wordList.add("tex");
//        wordList.add("red");
//        wordList.add("tax");
//        wordList.add("tad");
//        wordList.add("den");
//        wordList.add("rex");
//        wordList.add("pee");

        String beginWord = "cet";
        String endWord = "ism";
        String[] wordListArr = {"kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob"};
        List<String> wordList = Arrays.asList(wordListArr);

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

//        printGraph(root);
        int solutionDepth = getSolutionDepth(root, endWord);
        System.out.println("solution depth = " + solutionDepth);

        return findPaths(root, endWord, solutionDepth);
    }

    private static Node createWordGraph(String beginWord, String endWord, Set<String> wordList) {
        Set<Character> distinctChars = getDistinctChars(wordList);

        Queue<Node> frontier = new LinkedList<>();
        Node root = new Node(beginWord);
        frontier.add(root);

        Set<String> processedStates = new HashSet<>();
        Map<String, Node> nodes = new HashMap<>();
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

//                continue; // No need to transform further if endword reached
                break;
            }

            // Generate next state transitions
            generateNextStates(state, wordList, distinctChars, frontier,
                    nodes, processedStates, node);

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
                                           Map<String, Node> nodes, Set<String> processedStates, Node node) {
        for (int i = 0; i < node.val.length(); i++) {
            for (Character token: distinctChars) {
                String newWord = replaceWord(beginWord, i, token);
                if (!newWord.equals(beginWord) && wordList.contains(newWord)
                        && !processedStates.contains(newWord)) {
                    Node neighborNode;
                    if (nodes.containsKey(newWord)) {
                        neighborNode = nodes.get(newWord);
                    } else {
                        neighborNode = new Node(newWord);
                        nodes.put(newWord, neighborNode);
                    }

//                    Node newNode = new Node(newWord);
//                    node.addNeighbor(newNode);

//                    frontier.add(newNode);

                    node.addNeighbor(neighborNode);
                    frontier.add(neighborNode);
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

    private static int getSolutionDepth(Node root, String endWord) {
        if (root == null) {
            return 0;
        }

        int depth = 0;

        Queue<Node> frontier = new LinkedList<>();
        frontier.add(root);

        Set<String> processedNodes = new HashSet<>();
        while (!frontier.isEmpty()) {
            depth++;

            Queue<Node> nextLevel = new LinkedList<>();
            while (!frontier.isEmpty()) {
                Node node = frontier.remove();
                if (processedNodes.contains(node.val)) {
                    continue;
                }

                processedNodes.add(node.val);

                if (node.val.equals(endWord)) {
                    return depth;
                }

                nextLevel.addAll(node.neighbors);
            }

            frontier = nextLevel;
        }

        return depth;
    }

    private static List<List<String>> findPaths(Node root, String endWord, int maxDepth) {
        List<List<String>> output = new ArrayList<>();
        findPathsRecursive(root, endWord, maxDepth, 1, output, new ArrayList<>());

        return output;
    }

    private static void findPathsRecursive(Node root, String endWord, int maxDepth, int depth,
                                           List<List<String>> output, List<String> chain) {
        if (root == null || depth > maxDepth) {
            return;
        }

        if (root.val.equals(endWord)) {
            output.add(new ArrayList<>(chain));
            return;
        }

        chain.add(root.val);
        for (Node neighbor: root.neighbors) {
            findPathsRecursive(neighbor, endWord, maxDepth, depth + 1, output, chain);
        }

        // Backtrack
        chain.remove(chain.size() - 1);
    }

//    private static List<List<String>> findPaths(Node root, String endWord) {
//        List<List<String>> output = new ArrayList<>();
//        List<String> currentChain = new ArrayList<>();
//        currentChain.add(root.val);
//
//        findPathsRecursive(root, endWord, output, currentChain, 0, new DepthTracker(Integer.MAX_VALUE));
//
//        return output;
//    }
//
//    private static class DepthTracker {
//        public int maxDepth;
//
//        public DepthTracker(int maxDepth) {
//            this.maxDepth = maxDepth;
//        }
//    }
//
//    private static void findPathsRecursive(Node node, String endWord, List<List<String>> output,
//                                           List<String> currentChain, int depth, DepthTracker depthTracker) {
//        if (node == null || depth > depthTracker.maxDepth) {
//            return;
//        }
//
//        if (node.val.equals(endWord)) {
//            output.add(new ArrayList<>(currentChain));
//
//            if (depth < depthTracker.maxDepth) {
//                depthTracker.maxDepth = depth;
//
//                filterLongerPaths(output, depth);
//            }
//
//            return;
//        }
//
//        for (Node neighbor: node.neighbors) {
//            currentChain.add(neighbor.val);
//            findPathsRecursive(neighbor, endWord, output, currentChain, depth + 1, depthTracker);
//
//            // Backtrack
//            currentChain.remove(currentChain.size()-1);
//        }
//    }
//
//    private static void filterLongerPaths(List<List<String>> output, int depth) {
//        output.removeIf(result -> (result.size() - 1) > depth); // -1 because depth is zero index'd
//    }
//
//    private static void printGraph(Node root) {
//        Queue<Node> frontier = new LinkedList<>();
//        frontier.add(root);
//
//        while (!frontier.isEmpty()) {
//            Node node = frontier.remove();
//
//            System.out.println("Node id:" + node.toString());
//            System.out.println("Node val: " + node.val);
//            System.out.println("Node neighbors");
//            for (Node neighbor: node.neighbors) {
//                System.out.printf("%s (%s), ", neighbor.val, neighbor.toString());
//                frontier.add(neighbor);
//            }
//
//            System.out.println("");
//            System.out.println();
//        }
//    }
}

