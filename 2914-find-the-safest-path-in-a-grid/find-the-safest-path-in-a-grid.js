/**
 * @param {number[][]} grid
 * @return {number}
 */
var maximumSafenessFactor = function(grid) {
    const n = grid.length;
    const dirs = [[1,0],[-1,0],[0,1],[0,-1]];

    // Step 1: Distance from nearest thief
    const dist = Array.from({length:n}, () => Array(n).fill(-1));
    const q = [];
    let head = 0;

    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j] === 1) {
                dist[i][j] = 0;
                q.push([i, j]);
            }
        }
    }

    while (head < q.length) {
        const [x, y] = q[head++];

        for (const [dx, dy] of dirs) {
            const nx = x + dx;
            const ny = y + dy;

            if (
                nx >= 0 &&
                ny >= 0 &&
                nx < n &&
                ny < n &&
                dist[nx][ny] === -1
            ) {
                dist[nx][ny] = dist[x][y] + 1;
                q.push([nx, ny]);
            }
        }
    }

    // ---------- Max Heap ----------
    class MaxHeap {
        constructor() {
            this.heap = [];
        }

        push(item) {
            const h = this.heap;
            h.push(item);
            let i = h.length - 1;

            while (i > 0) {
                let p = (i - 1) >> 1;
                if (h[p][0] >= h[i][0]) break;
                [h[p], h[i]] = [h[i], h[p]];
                i = p;
            }
        }

        pop() {
            const h = this.heap;
            const top = h[0];
            const last = h.pop();

            if (h.length) {
                h[0] = last;
                let i = 0;

                while (true) {
                    let l = i * 2 + 1;
                    let r = l + 1;
                    let largest = i;

                    if (l < h.length && h[l][0] > h[largest][0]) largest = l;
                    if (r < h.length && h[r][0] > h[largest][0]) largest = r;

                    if (largest === i) break;

                    [h[i], h[largest]] = [h[largest], h[i]];
                    i = largest;
                }
            }

            return top;
        }

        size() {
            return this.heap.length;
        }
    }

    const heap = new MaxHeap();
    const vis = Array.from({length:n}, () => Array(n).fill(false));

    heap.push([dist[0][0], 0, 0]);
    vis[0][0] = true;

    while (heap.size()) {
        const [safe, x, y] = heap.pop();

        if (x === n - 1 && y === n - 1) return safe;

        for (const [dx, dy] of dirs) {
            const nx = x + dx;
            const ny = y + dy;

            if (
                nx >= 0 &&
                ny >= 0 &&
                nx < n &&
                ny < n &&
                !vis[nx][ny]
            ) {
                vis[nx][ny] = true;
                heap.push([
                    Math.min(safe, dist[nx][ny]),
                    nx,
                    ny
                ]);
            }
        }
    }

    return 0;
};