import java.util.*;

class Solution {

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int startR = 0;
        int startC = 0;

        // Give every litter an ID: 0, 1, 2, ...
        int[][] litterId = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(litterId[i], -1);
        }

        int litterCount = 0;

        // Find S and assign IDs to L
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (classroom[i].charAt(j) == 'S') {
                    startR = i;
                    startC = j;
                }

                if (classroom[i].charAt(j) == 'L') {
                    litterId[i][j] = litterCount;
                    litterCount++;
                }
            }
        }

        // If there is no litter
        if (litterCount == 0) {
            return 0;
        }

        // If we have k litter pieces,
        // all collected = (1 << k) - 1
        int fullMask = (1 << litterCount) - 1;

        /*
            State:
            row
            col
            energy
            mask
            moves
        */
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{
            startR,
            startC,
            energy,
            0,
            0
        });

        // visited[row][col][mask][energy]
        boolean[][][][] visited =
                new boolean[m][n][1 << litterCount][energy + 1];

        visited[startR][startC][0][energy] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int r = current[0];
            int c = current[1];
            int currEnergy = current[2];
            int mask = current[3];
            int moves = current[4];

            // All litter collected
            if (mask == fullMask) {
                return moves;
            }

            // No energy left -> cannot make another move
            if (currEnergy == 0) {
                continue;
            }

            // Try 4 directions
            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                // Outside the classroom
                if (nr < 0 || nr >= m ||
                    nc < 0 || nc >= n) {
                    continue;
                }

                // Obstacle
                if (classroom[nr].charAt(nc) == 'X') {
                    continue;
                }

                // One move costs one energy
                int newEnergy = currEnergy - 1;

                // Copy current collected-litter information
                int newMask = mask;

                // If we reach litter
                if (classroom[nr].charAt(nc) == 'L') {

                    int id = litterId[nr][nc];

                    newMask = newMask | (1 << id);
                }

                // If we reach reset area
                if (classroom[nr].charAt(nc) == 'R') {
                    newEnergy = energy;
                }

                // Have we already visited this exact state?
                if (!visited[nr][nc][newMask][newEnergy]) {

                    visited[nr][nc][newMask][newEnergy] = true;

                    queue.add(new int[]{
                        nr,
                        nc,
                        newEnergy,
                        newMask,
                        moves + 1
                    });
                }
            }
        }

        return -1;
    }
}
