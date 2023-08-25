//{ Driver Code Starts
// Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;

// Position this line where user code will be pasted.

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] grid = new int[n][m];

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < m; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }
            int[] source = new int[2];
            for (int i = 0; i < 2; i++) {
                int x = sc.nextInt();
                source[i] = x;
            }
            int[] dest = new int[2];
            for (int i = 0; i < 2; i++) {
                int x = sc.nextInt();
                dest[i] = x;
            }
            Solution ob = new Solution();
            int ans = ob.shortestPath(grid, source, dest);
            System.out.println(ans);
        }
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution {

    int shortestPath(int[][] grid, int[] source, int[] destination) {
        int n = grid.length, m = grid[0].length;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        // [] -> {dist, row, col}
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> Integer.compare(a[0], b[0]));
        int[][] dist = new int[n][m]; //this will store, dist from source to a particular cell
        for(int[] row: dist) Arrays.fill(row, Integer.MAX_VALUE); //initializing with max. value

        q.add(new int[]{0, source[0], source[1]});
        dist[source[0]][source[1]] = 0;
        
        while(!q.isEmpty()){
            int size = q.size();
            boolean found = false;
            while(size-- > 0){
                int[] cur = q.poll();
                int d = cur[0], i = cur[1], j = cur[2];
                
                if(i == destination[0] && j == destination[1]){
                    found = true;
                    break;
                };
                
                for(int[] dir: dirs){
                    int r = i+dir[0], c = j+dir[1];
                    if(r < 0 || c < 0 || r >= n || c >= m || grid[r][c] == 0) {
                        //invalid indices, continue to next one
                        continue;
                    }
                    if(dist[r][c] > d+1){
                        q.add(new int[]{d+1, r, c});
                        dist[r][c] = d+1;
                    }
                }
            }
            
            if(found) break;
        }
        
        int dr = destination[0], dc = destination[1];
        return dist[dr][dc] == Integer.MAX_VALUE? -1: dist[dr][dc];
    }
}
