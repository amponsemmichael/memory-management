import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MemoryLeak {

    private static final Map<String, String> cache = new HashMap<>();

    public static void main(String[] args) {
        MemoryLeak program = new MemoryLeak();
        program.simulateMemoryLeak();
    }

    private void simulateMemoryLeak() {
        while (true) {
            // Create a new large object (UUID generates random strings)
            String key = UUID.randomUUID().toString();
            String value = generateLargeString();

            // Add the large object to the cache (without any eviction mechanism)
            cache.put(key, value);

            // Print the size of the cache to track its growth
            System.out.println("Cache size: " + cache.size());

            // Simulate a delay to slow down the memory leak
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateLargeString() {
        // Generate a large string (simulating a large object)
        return "X".repeat(10_000);
    }
}
