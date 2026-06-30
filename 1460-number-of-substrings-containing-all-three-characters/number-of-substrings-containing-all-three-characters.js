/**
 * @param {string} s
 * @return {number}
 */
var numberOfSubstrings = function(s) {
    let last = [-1, -1, -1]; // a, b, c
    let ans = 0;

    for (let i = 0; i < s.length; i++) {
        last[s.charCodeAt(i) - 97] = i;
        const minIndex = Math.min(last[0], last[1], last[2]);

        if (minIndex !== -1) {
            ans += minIndex + 1;
        }
    }

    return ans;
};