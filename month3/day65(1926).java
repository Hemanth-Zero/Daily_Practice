class Solution {
    public int minMoves(String[] classroom, int energy) {
        int n = classroom.length;
        int m = classroom[0].length();

        int[][] id = new int[n][m];
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(id[i], -1);
        }

        int sr = 0, sc = 0, totalL = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = classroom[i].charAt(j);

                if (c == 'S') {
                    sr = i;
                    sc = j;
                } else if (c == 'L') {
                    id[i][j] = totalL++;
                }
            }
        }

        int fullMask = (1 << totalL) - 1;

        if (fullMask == 0) return 0;

        boolean[][][][] visited = new boolean[n][m][energy + 1][1 << totalL];

        java.util.ArrayDeque<int[]> q = new java.util.ArrayDeque<>();

        q.offer(new int[]{sr, sc, energy, 0});
        visited[sr][sc][energy][0] = true;

        int[] di = {-1, 1, 0, 0};
        int[] dj = {0, 0, -1, 1};

        int moves = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] cur = q.poll();

                int i = cur[0];
                int j = cur[1];
                int eng = cur[2];
                int mask = cur[3];

                for (int k = 0; k < 4; k++) {
                    int ni = i + di[k];
                    int nj = j + dj[k];

                    if (ni < 0 || ni >= n || nj < 0 || nj >= m)
                        continue;

                    if (classroom[ni].charAt(nj) == 'X')
                        continue;

                    if (eng == 0)
                        continue;

                    int newEng = eng - 1;
                    int newMask = mask;

                    char cell = classroom[ni].charAt(nj);

                    if (cell == 'R')
                        newEng = energy;

                    if (cell == 'L')
                        newMask |= 1 << id[ni][nj];

                    if (newMask == fullMask)
                        return moves + 1;

                    if (!visited[ni][nj][newEng][newMask]) {
                        visited[ni][nj][newEng][newMask] = true;
                        q.offer(new int[]{ni, nj, newEng, newMask});
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}