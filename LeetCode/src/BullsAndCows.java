import java.util.*;

public class BullsAndCows {
    // Count all the bulls
    // Create secret char map - <secretChar, indices>
    // Loop through guess
    // If guess char is a bull, bull++ and break
    // Else look ahead
    // - If look ahead char is a bull, skip to next
    // - Else look ahead char is a cow, cow++ and break.

    public String getHint(String secret, String guess) {
        Set<Integer> bulls = new HashSet<>();
        Set<Integer> cows = new HashSet<>();

        Map<Character, List<Integer>> secretChars = createSecretCharMap(secret);
        for (int i = 0; i < guess.length(); i++) {
            char guessChar = guess.charAt(i);

            // Case 0: Guess char is bull
            if (isBull(i, secret, guess)) {
                bulls.add(i);
                continue;
            }

            // Case 1: Potential cow
            if (secretChars.containsKey(guessChar)) {
                List<Integer> secretIndices = secretChars.get(guessChar);

                for (int j = 0; j < secretIndices.size(); j++) {
                    int secretIndex = secretIndices.get(j);

                    // Case 1a - skip if index already processed, or is a bull
                    if (isAlreadyProcessed(secretIndex, bulls, cows)
                            || isBull(secretIndex, secret, guess)) {
                        continue;
                    }

                    // Case 1b: Index is a cow
                    if (guessChar == secret.charAt(secretIndex)) {
                        cows.add(secretIndex);

                        break;
                    }
                }
            }
        }

        return String.format("%dA%dB", bulls.size(), cows.size());
    }

    /**
     * Create secret char map - <secretChar, indices>
     */
    private Map<Character, List<Integer>> createSecretCharMap(String secret) {
        Map<Character, List<Integer>> secretChars = new HashMap<>();
        for(int i = 0; i < secret.length(); i++) {
            char secretChar = secret.charAt(i);

            List<Integer> charIndices;
            if (secretChars.containsKey(secretChar)) {
                charIndices = secretChars.get(secretChar);
            } else {
                charIndices = new ArrayList<>();
                secretChars.put(secretChar, charIndices);
            }

            charIndices.add(i);
        }

        return secretChars;
    }

    private boolean isBull(int i, String secret, String guess) {
        return secret.charAt(i) == guess.charAt(i);
    }

    private boolean isAlreadyProcessed(int i, Set<Integer> bulls, Set<Integer> cows) {
        return bulls.contains(i) || cows.contains(i);
    }
}
