class Solution {
    private int tryCombinations(int i, int j, int[] nums1, int[] nums2, int[][] dp){
        if(i >= nums1.length || j >= nums2.length || nums1[i] != nums2[j]){
            return 0;
        }
        
        if(dp[i][j] != -1)
            return dp[i][j];
        
        return dp[i][j] = 1 + tryCombinations(i+1, j+1, nums1, nums2, dp);
    }
    public int findLength(int[] nums1, int[] nums2) {
        int maxLen = 0;
        
        int len1 = nums1.length;
        int len2 = nums2.length;
        
        int[][] dp = new int[len1+1][len2+1];
        for(int i = 0; i<len1+1; i++){
            Arrays.fill(dp[i], -1);
        }
        
        for(int i = 0; i<len1; i++){
            for(int j = 0; j<len2; j++){
                int len = tryCombinations(i, j, nums1, nums2, dp);
                maxLen = Math.max(len, maxLen);
            }
        }    
        
        return maxLen;
    }
}