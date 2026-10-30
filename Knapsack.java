import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Задача 2. Каждый предмет не больше одного раза, масса не больше W.
public class Knapsack {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int cap = Integer.parseInt(st.nextToken());

        int[] weight = new int[n];
        int[] value = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        long[] best = new long[cap + 1];
        for (int i = 0; i < n; i++) {
            for (int w = cap; w >= weight[i]; w--) {
                long next = best[w - weight[i]] + value[i];
                if (next > best[w])
                    best[w] = next;
            }
        }

        out.println(best[cap]);
        out.flush();
    }
}
