import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        Arrays.sort(a, (x, y) -> {
            if (x[0] != y[0])
                return Integer.compare(x[0], y[0]);
            return Integer.compare(x[1], y[1]);
        });

        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            int lo = i + 1, hi = n;

            while (lo < hi) {
                int mid = (lo + hi) >>> 1;

                if (a[mid][0] > a[i][1])
                    hi = mid;
                else
                    lo = mid + 1;
            }

            next[i] = lo;
        }

        long[][] dp = new long[n + 1][5];
        List<Integer>[][] path = new ArrayList[n + 1][5];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                path[i][k] = new ArrayList<>();
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k <= 4; k++) {
                dp[i][k] = dp[i + 1][k];
                path[i][k] = new ArrayList<>(path[i + 1][k]);

                if (dp[next[i]][k - 1] + a[i][2] > dp[i][k]) {
                    dp[i][k] = dp[next[i]][k - 1] + a[i][2];
                    path[i][k] = new ArrayList<>(path[next[i]][k - 1]);
                    path[i][k].add(a[i][3]);
                } else if (dp[next[i]][k - 1] + a[i][2] == dp[i][k]) {
                    List<Integer> temp = new ArrayList<>(path[next[i]][k - 1]);
                    temp.add(a[i][3]);

                    Collections.sort(temp);

                    List<Integer> cur = new ArrayList<>(path[i][k]);
                    Collections.sort(cur);

                    if (compare(temp, cur) < 0)
                        path[i][k] = temp;
                }
            }
        }

        Collections.sort(path[0][4]);

        int[] ans = new int[path[0][4].size()];

        for (int i = 0; i < ans.length; i++)
            ans[i] = path[0][4].get(i);

        return ans;
    }

    private int compare(List<Integer> a, List<Integer> b) {
        for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
            if (!a.get(i).equals(b.get(i)))
                return Integer.compare(a.get(i), b.get(i));
        }

        return Integer.compare(a.size(), b.size());
    }
}
