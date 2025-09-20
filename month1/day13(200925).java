import java.util.*;

class Router {
    int memoryLimit;
    
    class DataPack {
        int source, destination, timestamp;
        DataPack(int s, int d, int t) { source = s; destination = d; timestamp = t; }
    }
    
    Queue<DataPack> queue;
    Set<String> seen;
    Map<Integer, List<Integer>> destTimestamps;
    Map<Integer, Integer> processedIndex;
    
    public Router(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        queue = new LinkedList<>();
        seen = new HashSet<>();
        destTimestamps = new HashMap<>();
        processedIndex = new HashMap<>();
    }
    
    public boolean addPacket(int source, int destination, int timestamp) {
        String key = source + "," + destination + "," + timestamp;
        if (seen.contains(key)) return false;
        
        if (queue.size() == memoryLimit) {
            DataPack old = queue.poll();
            String oldKey = old.source + "," + old.destination + "," + old.timestamp;
            seen.remove(oldKey);
            processedIndex.put(old.destination, processedIndex.getOrDefault(old.destination, 0) + 1);
        }
        
        queue.add(new DataPack(source, destination, timestamp));
        seen.add(key);
        destTimestamps.computeIfAbsent(destination, k -> new ArrayList<>()).add(timestamp);
        processedIndex.putIfAbsent(destination, 0);
        return true;
    }
    
    public int[] forwardPacket() {
        if (queue.isEmpty()) return new int[0];
        DataPack p = queue.poll();
        String key = p.source + "," + p.destination + "," + p.timestamp;
        seen.remove(key);
        processedIndex.put(p.destination, processedIndex.get(p.destination) + 1);
        return new int[]{p.source, p.destination, p.timestamp};
    }
    
    public int getCount(int destination, int startTime, int endTime) {
        if (!destTimestamps.containsKey(destination)) return 0;
        List<Integer> list = destTimestamps.get(destination);
        int start = lowerBound(list, startTime);
        int end = upperBound(list, endTime);
        int idx = processedIndex.getOrDefault(destination, 0);
        return Math.max(0, end - Math.max(start, idx));
    }
    
    private int lowerBound(List<Integer> list, int val) {
        int l = 0, r = list.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (list.get(m) >= val) r = m;
            else l = m + 1;
        }
        return l;
    }
    
    private int upperBound(List<Integer> list, int val) {
        int l = 0, r = list.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (list.get(m) > val) r = m;
            else l = m + 1;
        }
        return l;
    }
}
