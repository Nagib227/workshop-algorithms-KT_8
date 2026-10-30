import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

// Задача 6. Самая короткая строка, где s и t — подпоследовательности. При равенстве длины — меньшая по алфавиту.
public class ShortestSuper {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String s = in.readLine();
        String t = in.readLine();
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = n; i >= 0; i--) {
            for (int j = m; j >= 0; j--) {
                if (i == n && j == m)
                    dp[i][j] = 0;
                else if (i == n)
                    dp[i][j] = m - j;
                else if (j == m)
                    dp[i][j] = n - i;
                else if (s.charAt(i) == t.charAt(j))
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                else
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = 0;
        while (i < n || j < m) {
            if (i == n) {
                sb.append(t.charAt(j));
                j++;
            } else if (j == m) {
                sb.append(s.charAt(i));
                i++;
            } else if (s.charAt(i) == t.charAt(j)) {
                sb.append(s.charAt(i));
                i++;
                j++;
            } else if (dp[i + 1][j] < dp[i][j + 1] || (dp[i + 1][j] == dp[i][j + 1] && s.charAt(i) < t.charAt(j))) {
                sb.append(s.charAt(i));
                i++;
            } else {
                sb.append(t.charAt(j));
                j++;
            }
        }

        out.println(sb);
        out.flush();
    }
}
