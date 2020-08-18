import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

public class GrayCode {
    public static void main(String[] args) {
        for (int i = 0; i <= 4; i++) {
            System.out.println("GrayCodes for " + i + ": ");

            List<Integer> grayCodes = grayCode(i);
            grayCodes.forEach(grayCode -> System.out.print(grayCode + ", "));
            System.out.println();
        }
//
//        List<Integer> grayCodes = grayCode(2);
//        grayCodes.forEach(grayCode -> System.out.print(grayCode + ", "));
    }

    /**
     * Algorithm:
     * Reverse the (n - 1) list
     * Prefix original list with '0'
     * Prefix reflected list with '1'
     * Concatenate both lists to form nth list
     * Rinse and repeat
     */
    public static List<Integer> grayCode(int n) {
        List<Integer> output = new ArrayList<>();
        if (n == 0) {
            output.add(0);
            return output;
        }

        List<String> grayCodes = new ArrayList<>();
        grayCodes.add("0");
        grayCodes.add("1");

        for (int i = 2; i <= n; i++) {
            List<String> reversedGrayCodes = new ArrayList<>(grayCodes);
            Collections.reverse(reversedGrayCodes);

            List<String> newGrayCodes = new ArrayList<>();
            for (String grayCode: grayCodes) {
                newGrayCodes.add("0" + grayCode);
            }

            for (String reversedGrayCode: reversedGrayCodes) {
                newGrayCodes.add("1" + reversedGrayCode);
            }

            grayCodes = newGrayCodes;
        }

        for (String grayCode: grayCodes) {
            output.add(Integer.parseInt(grayCode, 2));
        }

        return output;
    }



}
