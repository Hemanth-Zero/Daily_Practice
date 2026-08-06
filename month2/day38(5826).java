class Solution {

    HashSet<Integer> issus(int k, List<List<Integer>> graph, HashSet<Integer> sus) {
        if (sus.contains(k)) return sus;
        sus.add(k);
        for (int next : graph.get(k)) {
            issus(next, graph, sus);
        }
        return sus;
    }

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < invocations.length; i++) {
            graph.get(invocations[i][0]).add(invocations[i][1]);
        }
        HashSet<Integer> sus = new HashSet<>();
        issus(k, graph, sus);
        for (int i = 0; i < invocations.length; i++) {
            if (sus.contains(invocations[i][1])) {
                if (!sus.contains(invocations[i][0])) {
                    sus = new HashSet<>();
                    break;
                }
            }
        }
        HashSet<Integer> all = new HashSet<>();
        for (int i = 0; i < n; i++) {
            all.add(i);
        }
        all.removeAll(sus);
        return new ArrayList<>(all);
    }
}