/**
 * @param {number[][]} grid
 * @return {number}
 */
var maximumSafenessFactor = function(grid) {
    const n = grid.length;
    const dirs = [[1,0],[-1,0],[0,1],[0,-1]];

    // ---------- Multi-source BFS ----------
    const dist = Array.from({length:n}, () => Array(n).fill(-1));
    const queue = [];
    let head = 0;

    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j] === 1) {
                dist[i][j] = 0;
                queue.push([i, j]);
            }
        }
    }

    while (head < queue.length) {
        const [x, y] = queue[head++];

        for (const [dx, dy] of dirs) {
            const nx = x + dx;
            const ny = y + dy;

            if (
                nx >= 0 && nx < n &&
                ny >= 0 && ny < n &&
                dist[nx][ny] === -1
            ) {
                dist[nx][ny] = dist[x][y] + 1;
                queue.push([nx, ny]);
            }
        }
    }

    // ---------- Check function ----------
    function can(limit) {
        if (dist[0][0] < limit) return false;

        const vis = Array.from({length:n}, () => Array(n).fill(false));
        const q = [[0,0]];
        vis[0][0] = true;
        let idx = 0;

        while (idx < q.length) {
            const [x, y] = q[idx++];

            if (x === n-1 && y === n-1) return true;

            for (const [dx, dy] of dirs) {
                const nx = x + dx;
                const ny = y + dy;

                if (
                    nx >= 0 && nx < n &&
                    ny >= 0 && ny < n &&
                    !vis[nx][ny] &&
                    dist[nx][ny] >= limit
                ) {
                    vis[nx][ny] = true;
                    q.push([nx, ny]);
                }
            }
        }

        return false;
    }

    // ---------- Binary Search ----------
    let left = 0;
    let right = 2 * (n - 1);
    let ans = 0;

    while (left <= right) {
        const mid = Math.floor((left + right) / 2);

        if (can(mid)) {
            ans = mid;
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return ans;
};