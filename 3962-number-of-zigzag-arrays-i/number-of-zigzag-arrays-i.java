class Solution {
    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        long[] up = new long[m + 1];
        long[] down = new long[m + 1];

        // Length = 2
        for (int v = 1; v <= m; v++) {
            up[v] = v - 1;      // previous value < v
            down[v] = m - v;    // previous value > v
        }

        for (int len = 3; len <= n; len++) {
            long[] newUp = new long[m + 1];
            long[] newDown = new long[m + 1];

            long[] prefixDown = new long[m + 1];
            for (int i = 1; i <= m; i++) {
                prefixDown[i] = (prefixDown[i - 1] + down[i]) % MOD;
            }

            long[] suffixUp = new long[m + 2];
            for (int i = m; i >= 1; i--) {
                suffixUp[i] = (suffixUp[i + 1] + up[i]) % MOD;
            }

            for (int v = 1; v <= m; v++) {
                newUp[v] = prefixDown[v - 1];
                newDown[v] = suffixUp[v + 1];
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;
        for (int v = 1; v <= m; v++) {
            ans = (ans + up[v] + down[v]) % MOD;
        }

        return (int) ans;
    }
}