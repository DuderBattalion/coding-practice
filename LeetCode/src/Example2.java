import java.util.*;

public class Example2 {
    public static void main(String[] args) {
        Random random = new Random();
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            int num = random.nextInt(1001);
            numMap.put(num, 1);
        }

        Set<Integer> numSet = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry: numMap.entrySet()) {
            int key = entry.getKey();
            numSet.add(key);
        }

        List<Integer> numList = new ArrayList<>(numSet);
        Collections.sort(numList);

        for (int num: numList) {
            System.out.println(num);
        }
    }
}
