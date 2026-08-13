class Solution {

    class Node {
        char leftChar, rightChar;
        int leftLen, rightLen, maxLen, len;

        Node(char c) {
            leftChar = rightChar = c;
            leftLen = rightLen = maxLen = len = 1;
        }

        Node() {}
    }

    Node[] tree;

    Node merge(Node L, Node R) {
        if (L == null) return R;
        if (R == null) return L;

        Node res = new Node();

        res.len = L.len + R.len;
        res.leftChar = L.leftChar;
        res.rightChar = R.rightChar;

        res.leftLen = L.leftLen;
        res.rightLen = R.rightLen;

        res.maxLen = Math.max(L.maxLen, R.maxLen);

        // Join the middle two runs
        if (L.rightChar == R.leftChar) {
            res.maxLen = Math.max(
                res.maxLen,
                L.rightLen + R.leftLen
            );

            // Entire left side has the same character
            if (L.leftLen == L.len) {
                res.leftLen = L.len + R.leftLen;
            }

            // Entire right side has the same character
            if (R.rightLen == R.len) {
                res.rightLen = R.len + L.rightLen;
            }
        }

        return res;
    }

    void build(char[] a, int node, int l, int r) {
        if (l == r) {
            tree[node] = new Node(a[l]);
            return;
        }

        int mid = (l + r) / 2;

        build(a, node * 2, l, mid);
        build(a, node * 2 + 1, mid + 1, r);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    void update(char[] a, int node, int l, int r, int index) {
        if (l == r) {
            tree[node] = new Node(a[l]);
            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(a, node * 2, l, mid, index);
        } else {
            update(a, node * 2 + 1, mid + 1, r, index);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    public int[] longestRepeating(
        String s,
        String queryCharacters,
        int[] queryIndices
    ) {

        int k = queryIndices.length;
        int n = s.length();

        int[] ans = new int[k];

        char[] a = s.toCharArray();
        char[] b = queryCharacters.toCharArray();

        tree = new Node[4 * n];

        // Build the tree once
        build(a, 1, 0, n - 1);

        for (int i = 0; i < k; i++) {
            a[queryIndices[i]] = b[i];
            update(a, 1, 0, n - 1, queryIndices[i]);

 
            ans[i] = tree[1].maxLen;
        }

        return ans;
    }
}