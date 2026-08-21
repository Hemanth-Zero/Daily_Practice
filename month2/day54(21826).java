class Solution {
    int[] coins;

    public long findKthSmallest(int[] coins, int k) {
        this.coins = coins;

        long low = 1;
        long high = (long) Arrays.stream(coins).min().getAsInt() * k;

        while (low < high) {
            long mid = low + (high - low) / 2;

            if (count(mid) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private long count(long x) {
        long count = 0;
        int n = coins.length;

        for (int mask = 1; mask < (1 << n); mask++) {
            long lcm = 1;
            int bits = 0;

            for (int j = 0; j < n; j++) {
                if ((mask & (1 << j)) != 0) {
                    bits++;

                    long g = gcd(lcm, coins[j]);
                    lcm = lcm / g * coins[j];

                    if (lcm > x) {
                        break;
                    }
                }
            }

            if (lcm > x) continue;

            if (bits % 2 == 1) {
                count += x / lcm;
            } else {
                count -= x / lcm;
            }
        }

        return count;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}