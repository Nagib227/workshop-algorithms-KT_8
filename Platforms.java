import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Задача 1. Прыжок на следующую платформу или через одну. Цена — модуль разности высот.
public class Platforms {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.readLine().trim());
        int[] h = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++)
            h[i] = Integer.parseInt(st.nextToken());

        long prev2 = 0;
        long prev1 = 0;
        for (int i = 1; i < n; i++) {
            long best = prev1 + Math.abs((long) h[i] - h[i - 1]);
            if (i >= 2) {
                long jump = prev2 + Math.abs((long) h[i] - h[i - 2]);
                if (jump < best)
                    best = jump;
            }
            prev2 = prev1;
            prev1 = best;
        }

        out.println(prev1);
        out.flush();
    }
}
