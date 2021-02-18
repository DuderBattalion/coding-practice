import java.util.HashMap;
import java.util.Map;

public class LoggerRateLimiter {
    Map<String, Integer> expireCache;

    /** Initialize your data structure here. */
    public LoggerRateLimiter() {
        expireCache = new HashMap<>();
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        boolean shouldPrint = false;
        if (expireCache.containsKey(message)) {
            int expireTime = expireCache.get(message);
            if (expireTime <= timestamp) {
                updateExpireCache(message, timestamp + 10);
                shouldPrint = true;
            }
        } else {
            updateExpireCache(message, timestamp + 10);
            shouldPrint = true;
        }

        return shouldPrint;
    }

    private void updateExpireCache(String message, int expireTime) {
        expireCache.put(message, expireTime);
    }
}
