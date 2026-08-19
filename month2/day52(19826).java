class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int p=0;
        int count=0;
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        for (int[] edge : reservedSeats) {
            int u = edge[0];
            int v = edge[1];
            if (!adjList.containsKey(u)) adjList.put(u, new ArrayList<>());
            adjList.get(u).add(v);
        }
        count = (n - adjList.size()) * 2;

        for (int row : adjList.keySet()) {
            for(int j=1;j<6;){
                boolean flag = true;
                for(int k=0;k<4;k++){
                    if(adjList.get(row).contains(j+k+1)){
                        flag = false;
                        break;
                    }
                }
                if(flag == true){
                    count++;
                    j+=2;
                }
                j+=2;
            }
        }
        return count;
    }
}