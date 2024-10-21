import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class MemoryLeakOptimized {

    // Cache with a fixed size of 1000, automatically removes the eldest entry
    private static final int MAX_CACHE_SIZE = 200;
    private static final Map<String, String> cache = new LinkedHashMap<String, String>(MAX_CACHE_SIZE, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
            return size() > MAX_CACHE_SIZE; // Evict the eldest entry when cache exceeds max size
        }
    };

    public static void main(String[] args) {
        MemoryLeakOptimized program = new MemoryLeakOptimized();
        program.simulateOptimizedCache();
    }

    private void simulateOptimizedCache() {
        while (true) {
            // Create new entries with minimal object creation
            String key = UUID.randomUUID().toString();
            String value = generateReusableLargeString();

            // Add entries to the cache with an eviction policy
            cache.put(key, value);

            // Print the cache size (to observe its fixed growth)
            System.out.println("Cache size: " + cache.size());

            // Simulate a delay (as in the original example)
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Reuse a large string to minimize object creation overhead
    private String generateReusableLargeString() {
        return "X".repeat(10_000);
    }
}
