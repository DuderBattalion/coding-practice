import com.leetcode.util.UnionFind;
import com.leetcode.util.UnionFindOptimized;

public class Driver {
    public static void main(String[] args) {
        UnionFindOptimized unionFind = new UnionFindOptimized(5);
        unionFind.union(1, 2);
        unionFind.union(2, 3);

        System.out.println(unionFind.find(1, 3));
        System.out.println(unionFind.find(1, 4));
    }
}
