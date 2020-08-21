import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddresses {
    public static void main(String[] args) {
        List<String> output = restoreIpAddresses("25525511135");

        for (String row: output) {
            System.out.println(row);
        }

//        // Debug
//        List<String> output = new ArrayList<>();
//        String s = "25525511135";
//
//        List<Integer> cutIndices = new ArrayList<>();
//        cutIndices.add(3);
//        cutIndices.add(6);
//        cutIndices.add(9);
//
//        generateIpAddresses(s, output, cutIndices);
//        for (String row: output) {
//            System.out.println(row);
//        }
    }

    public static List<String> restoreIpAddresses(String s) {
        List<String> output = new ArrayList<>();
        List<Integer> cutIndices = new ArrayList<>();

        generateIpAddresses(s, output, cutIndices);

        return output;
    }

    private static void generateIpAddresses(String s, List<String> output, List<Integer> cutIndices) {
        if (s.isEmpty() || s.length() > 12) {
            return;
        }

        if (cutIndices.size() == 3) {
            List<String> subIps = getSubIps(s, cutIndices);
            boolean validIp = isValidIp(subIps);
            if (validIp) {
                output.add(generateIp(subIps));
            }

            return;
        }

        int lastCutIndex;
        if (cutIndices.isEmpty()) {
            lastCutIndex = 1;
        } else {
            lastCutIndex = cutIndices.get(cutIndices.size() - 1) + 1;
        }

//        int lastCutIndex = cutIndices.get(cutIndices.size() - 1);
        for (int i = lastCutIndex; i <= s.length(); i++) {
            cutIndices.add(i);
            generateIpAddresses(s, output, cutIndices);

            cutIndices.remove(cutIndices.size() - 1); // Backtrack
        }
    }

    private static List<String> getSubIps(String s, List<Integer> cutIndices) {
        List<String> subIps = new ArrayList<>();
        if (cutIndices.size() != 3) {
            throw new RuntimeException("Cut indices size is not 3. Error. Size found: " + cutIndices.size());
        }

        subIps.add(s.substring(0, cutIndices.get(0)));
        subIps.add(s.substring(cutIndices.get(0), cutIndices.get(1)));
        subIps.add(s.substring(cutIndices.get(1), cutIndices.get(2)));
        subIps.add(s.substring(cutIndices.get(2)));

        return subIps;
    }

    private static boolean isValidIp(List<String> subIps) {
        for (String subIp: subIps) {
            if (subIp.isEmpty() || subIp.length() > 3
                    || subIp.equals("00")|| subIp.equals("000")) {
                return false;
            }

            int subIpNum = Integer.parseInt(subIp);
            if (subIpNum > 255) {
                return false;
            }
        }

        return true;
    }

    private static String generateIp(List<String> subIps) {
        if (subIps.size() != 4) {
            throw new RuntimeException(
                    String.format(
                            "Error: Invalid lengths for sub ip'slength: %d",
                            subIps.size()));
        }

        StringBuilder ip = new StringBuilder();
        for (String subIp : subIps) {
            ip.append(subIp);
            ip.append(".");
        }

        // Remove trailing "."
        return ip.toString().substring(0, ip.length() - 1);
    }

//    private static boolean isValidIp(String s, List<Integer> cutIndices) {
//        if (cutIndices.size() != 3) {
//            return false;
//        }
//
//        String s1 = s.substring(0, cutIndices.get(0) + 1);
//        if (!isValidSubIp(s1)) {
//            return false;
//        }
//
//        String s2 = s.substring(cutIndices.get(0) + 1, cutIndices.get(1) + 1);
//        if (!isValidSubIp(s2)) {
//            return false;
//        }
//
//        String s3 = s.substring(cutIndices.get(1) + 1, cutIndices.get(2) + 1);
//        if (!isValidSubIp(s3)) {
//            return false;
//        }
//
//        String s4 = s.substring(cutIndices.get(2) + 1);
//        return isValidSubIp(s4);
//    }

//    private static boolean isValidSubIp(String s) {
//        return !s.isEmpty() && s.length() <= 3 && !s.equals("00") && !s.equals("000");
//    }
}
