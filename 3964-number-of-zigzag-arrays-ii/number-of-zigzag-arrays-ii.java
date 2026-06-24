class Solution {
    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int size = 2 * m;

        long[] vec = new long[size];

        // State for length = 2
        // up[y]   = number of pairs (a1, y) with a1 < y
        // down[y] = number of pairs (a1, y) with a1 > y
        for (int y = 0; y < m; y++) {
            vec[y] = y;                 // up
            vec[m + y] = m - 1 - y;     // down
        }

        long exp = n - 2L;

        long[][] trans = new long[size][size];

        // up'[z] = sum_{x < z} down[x]
        for (int z = 0; z < m; z++) {
            for (int x = 0; x < z; x++) {
                trans[z][m + x] = 1;
            }
        }

        // down'[z] = sum_{x > z} up[x]
        for (int z = 0; z < m; z++) {
            for (int x = z + 1; x < m; x++) {
                trans[m + z][x] = 1;
            }
        }

        long[][] power = trans;

        while (exp > 0) {
            if ((exp & 1L) != 0) {
                vec = multiply(power, vec);
            }
            exp >>= 1;
            if (exp > 0) {
                power = multiply(power, power);
            }
        }

        long ans = 0;
        for (long v : vec) {
            ans += v;
        }

        return (int) (ans % MOD);
    }

    private long[] multiply(long[][] mat, long[] vec) {
        int n = mat.length;
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int k = 0; k < n; k++) {
                if (mat[i][k] != 0) {
                    sum = (sum + mat[i][k] * vec[k]) % MOD;
                }
            }
            res[i] = sum;
        }
        return res;
    }

    private long[][] multiply(long[][] a, long[][] b) {
        int n = a.length;
        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (a[i][k] == 0) continue;

                long aik = a[i][k];
                for (int j = 0; j < n; j++) {
                    if (b[k][j] == 0) continue;

                    res[i][j] = (res[i][j] + aik * b[k][j]) % MOD;
                }
            }
        }
        return res;
    }
}