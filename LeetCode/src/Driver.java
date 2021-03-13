import com.leetcode.util.FenwickTreeSum;
import com.leetcode.util.RabinKarp;
import com.leetcode.util.UnionFind;

import java.util.regex.Pattern;

public class Driver {
    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind(5);
        unionFind.union(1, 2);
        unionFind.union(2, 3);

        System.out.println(unionFind.find(1, 3));
        System.out.println(unionFind.find(1, 4));
    }
}
