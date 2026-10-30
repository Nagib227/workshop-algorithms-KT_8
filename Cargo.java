import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// Задача 4. Ровно масса C. Сначала максимальная прибыль, потом минимум грузов.
public class Cargo {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int cap = Integer.parseInt(st.nextToken());

        int[] weight = new int[n];
        int[] profit = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            profit[i] = Integer.parseInt(st.nextToken());
        }

        long[] best = new long[cap + 1];
        int[] count = new int[cap + 1];
        boolean[] can = new boolean[cap + 1];
        can[0] = true;
        Arrays.fill(count, Integer.MAX_VALUE);
        count[0] = 0;

        for (int i = 0; i < n; i++) {
            for (int w = weight[i]; w <= cap; w++) {
                if (!can[w - weight[i]])
                    continue;
                long nextProfit = best[w - weight[i]] + profit[i];
                int nextCount = count[w - weight[i]] + 1;
                if (!can[w] || nextProfit > best[w] || (nextProfit == best[w] && nextCount < count[w])) {
                    can[w] = true;
                    best[w] = nextProfit;
                    count[w] = nextCount;
                }
            }
        }

        if (!can[cap])
            out.println(-1);
        else
            out.println(best[cap] + " " + count[cap]);
        out.flush();
    }
}
