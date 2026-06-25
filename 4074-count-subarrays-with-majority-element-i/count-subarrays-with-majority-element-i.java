import java.util.*;

class Solution {
    
    static class Fenwick {
        int[] bit;
        
        Fenwick(int n) {
            bit = new int[n + 2];
        }
        
        void update(int idx, int delta) {
            while (idx < bit.length) {
                bit[idx] += delta;
                idx += idx & -idx;
            }
        }
        
        int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += bit[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }

    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (nums[i] == target ? 1 : -1);
        }

        // Coordinate compression
        int[] sorted = prefix.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> rank = new HashMap<>();
        int idx = 1;
        for (int v : sorted) {
            if (!rank.containsKey(v)) {
                rank.put(v, idx++);
            }
        }

        Fenwick bit = new Fenwick(idx + 2);

        long answer = 0;

        for (int p : prefix) {
            int r = rank.get(p);

            // Count previous prefix sums strictly smaller than current
            answer += bit.query(r - 1);

            bit.update(r, 1);
        }

        return (int) answer;
    }
}