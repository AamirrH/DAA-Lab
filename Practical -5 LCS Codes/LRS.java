package CWH;
public class LRS {
    public static void main(String[] args) {
        String S = "AABCBDC";
        int n = S.length();

        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (S.charAt(i - 1) == S.charAt(j - 1) && i != j) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int i = n, j = n;
        StringBuilder lrs = new StringBuilder();

        while (i > 0 && j > 0) {
            if (S.charAt(i - 1) == S.charAt(j - 1) && i != j) {
                lrs.append(S.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        System.out.println("Longest Repeating Subsequence: " + lrs.reverse());
        System.out.println("Length of LRS: " + dp[n][n]);
    }
}

