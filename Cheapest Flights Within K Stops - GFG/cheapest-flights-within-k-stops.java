//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int edge=sc.nextInt();
            int[][] adj = new int[edge][3];
            for(int i=0;i<edge;i++){
                adj[i][0]=sc.nextInt();
                adj[i][1]=sc.nextInt();
                adj[i][2]=sc.nextInt();
            }
            int src=sc.nextInt();
            int dst=sc.nextInt();
            int k=sc.nextInt();
            Solution obj = new Solution();
            int res = obj.CheapestFLight(n,adj,src,dst,k);
            System.out.println(res);
        }
    }
}

// } Driver Code Ends


class Solution {
    
    public int CheapestFLight(int n,int flights[][],int src,int dst,int k) {
        List<List<int[]>> adjList = new ArrayList<>();
        for(int i = 0; i<n; i++) adjList.add(new ArrayList<>());
        for(int[] flight: flights){
            int u = flight[0], v = flight[1], price = flight[2];
            adjList.get(u).add(new int[]{v, price});
        }
        
        int[][] cheapest = new int[n][n]; //cheapest[i][j] = price for going to j with i stops
        for(int []arr: cheapest) Arrays.fill(arr, Integer.MAX_VALUE);
        //[] -> {node, stops, cost} 
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{src, 0, 0});
        cheapest[0][src] = 0;
        
        while(!q.isEmpty()){
            int size = q.size();
            boolean flag = false;
            while(size-- > 0){
                int[] cur = q.poll();
                int node = cur[0], stops = cur[1], amount = cur[2];
                
                if(node == dst){
                    if(stops == k+1){
                        flag = true; //search for this level only
                    }
                    continue;
                }
                
                if(stops == k+1) continue; //skip, not applicable
                
                for(int[] adjPair: adjList.get(node)){
                    int adj = adjPair[0], price = adjPair[1];
                    
                    if(amount + price <= cheapest[stops+1][adj]){
                        q.add(new int[]{adj, stops+1, amount+price});
                        cheapest[stops+1][adj] = amount+price;
                    }
                    
                }
            }
            
            if(flag) break;
        }
        
        int ans = Integer.MAX_VALUE;
        //k + 1, for 1 destination added
        for(int i = 0; i<n; i++){
            ans = Math.min(ans, cheapest[i][dst]);
        }
        
        return ans == Integer.MAX_VALUE? -1: ans;
    }
}