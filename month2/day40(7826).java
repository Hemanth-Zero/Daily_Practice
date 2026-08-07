class Solution {
    public String smallestNumber(String num, long t) {
        long[] req = factorize(t);
        if (req == null) return "-1";

        int[] baseCounts = minDigitCounts(req); // counts[2..9]
        int minLen = 0;
        for (int c : baseCounts) minLen += c;

        int L = num.length();

        // Case 1: even the minimal digit-count needed already exceeds num's length.
        if (minLen > L) {
            return buildAscending(baseCounts, 0);
        }

        char[] digits = num.toCharArray();
        int firstZero = -1;
        for (int i = 0; i < L; i++) {
            if (digits[i] == '0') { firstZero = i; break; }
        }

        // prefixExp[i] = prime exponents of digits[0..i-1]
        long[][] prefixExp = new long[L + 1][4];
        for (int i = 0; i < L; i++) {
            prefixExp[i + 1] = prefixExp[i].clone();
            addExp(prefixExp[i + 1], digits[i] - '0');
        }

        // Case 2: num itself already works.
        if (firstZero == -1 && covers(prefixExp[L], req)) {
            return num;
        }

        // Case 3: try to find a same-length answer > num.
        // We may only keep a prefix that stops at or before the first zero digit.
        int limit = (firstZero == -1) ? L - 1 : firstZero;

        for (int i = limit; i >= 0; i--) {
            int d = digits[i] - '0';
            int spaceAfter = L - 1 - i;
            for (int nd = d + 1; nd <= 9; nd++) {
                long[] cur = prefixExp[i].clone();
                addExp(cur, nd);

                long[] remaining = new long[4];
                for (int k = 0; k < 4; k++) remaining[k] = Math.max(0, req[k] - cur[k]);

                int[] fillCounts = minDigitCounts(remaining);
                int need = 0;
                for (int c : fillCounts) need += c;

                if (need <= spaceAfter) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i);
                    sb.append((char) ('0' + nd));
                    int padOnes = spaceAfter - need;
                    sb.append(buildAscending(fillCounts, padOnes));
                    return sb.toString();
                }
            }
        }

        // Case 4: no same-length answer exists; the result needs one more digit.
        return buildAscending(baseCounts, (L + 1) - minLen);
    }

    // Factor t using only primes 2,3,5,7 -> exponents [e2,e3,e5,e7]; null if impossible.
    private long[] factorize(long t) {
        long[] exp = new long[4];
        int[] primes = {2, 3, 5, 7};
        for (int i = 0; i < 4; i++) {
            while (t % primes[i] == 0) {
                t /= primes[i];
                exp[i]++;
            }
        }
        return (t == 1) ? exp : null;
    }

    // Add the prime-factor contribution of a single digit (1-9) to exp[e2,e3,e5,e7].
    private void addExp(long[] exp, int digit) {
        switch (digit) {
            case 2: exp[0] += 1; break;
            case 3: exp[1] += 1; break;
            case 4: exp[0] += 2; break;
            case 5: exp[2] += 1; break;
            case 6: exp[0] += 1; exp[1] += 1; break;
            case 7: exp[3] += 1; break;
            case 8: exp[0] += 3; break;
            case 9: exp[1] += 2; break;
            default: break; // 0 and 1 contribute nothing
        }
    }

    private boolean covers(long[] have, long[] req) {
        for (int k = 0; k < 4; k++) if (have[k] < req[k]) return false;
        return true;
    }

    // Given required exponents [e2,e3,e5,e7], greedily build the smallest-count
    // digit multiset (digits 2-9) that satisfies them.
    private int[] minDigitCounts(long[] req) {
        long c2 = req[0], c3 = req[1], e5 = req[2], e7 = req[3];
        int[] count = new int[10];

        count[5] = (int) e5;
        count[7] = (int) e7;

        count[9] = (int) (c3 / 2); c3 %= 2;
        count[8] = (int) (c2 / 3); c2 %= 3;

        long combo = Math.min(c2, c3);
        count[6] = (int) combo;
        c2 -= combo; c3 -= combo;

        count[4] = (int) (c2 / 2); c2 %= 2;
        count[2] = (int) c2;
        count[3] = (int) c3;

        return count;
    }

    // Build smallest zero-free string: extraOnes copies of '1', then digits 2..9
    // in ascending order per counts.
    private String buildAscending(int[] counts, int extraOnes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < extraOnes; i++) sb.append('1');
        for (int d = 2; d <= 9; d++) {
            for (int i = 0; i < counts[d]; i++) sb.append((char) ('0' + d));
        }
        return sb.toString();
    }
}