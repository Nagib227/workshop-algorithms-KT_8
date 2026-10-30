import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// Задача 5. Длина самой длинной возрастающей последовательности и число таких наборов индексов.
public class IncreasingCount {
    static final int MOD = 1_000_000_007;
    static int[] len;
    static int[] ways;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.readLine().trim());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(st.nextToken());

        int[] sorted = Arrays.copyOf(a, n);
        Arrays.sort(sorted);
        int kinds = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1])
                sorted[kinds++] = sorted[i];
        }

        len = new int[4 * kinds];
        ways = new int[4 * kinds];

        for (int value : a) {
            int rank = Arrays.binarySearch(sorted, 0, kinds, value) + 1;
            int[] best = query(1, 1, kinds, 1, rank - 1);
            int curLen = best[0] + 1;
            int curWays = best[0] == 0 ? 1 : best[1];
            update(1, 1, kinds, rank, curLen, curWays);
        }

        int[] all = query(1, 1, kinds, 1, kinds);
        out.println(all[0] + " " + all[1]);
        out.flush();
    }

    static void update(int v, int left, int right, int pos, int addLen, int addWays) {
        if (left == right) {
            if (addLen > len[v]) {
                len[v] = addLen;
                ways[v] = addWays;
            } else if (addLen == len[v]) {
                ways[v] = (int) ((ways[v] + (long) addWays) % MOD);
            }
            return;
        }
        int mid = (left + right) / 2;
        if (pos <= mid)
            update(v * 2, left, mid, pos, addLen, addWays);
        else
            update(v * 2 + 1, mid + 1, right, pos, addLen, addWays);
        pull(v);
    }

    static int[] query(int v, int left, int right, int ql, int qr) {
        if (ql > qr)
            return new int[]{0, 0};
        if (ql == left && qr == right)
            return new int[]{len[v], ways[v]};
        int mid = (left + right) / 2;
        int[] a = query(v * 2, left, mid, ql, Math.min(qr, mid));
        int[] b = query(v * 2 + 1, mid + 1, right, Math.max(ql, mid + 1), qr);
        return merge(a, b);
    }

    static void pull(int v) {
        int[] merged = merge(new int[]{len[v * 2], ways[v * 2]}, new int[]{len[v * 2 + 1], ways[v * 2 + 1]});
        len[v] = merged[0];
        ways[v] = merged[1];
    }

    static int[] merge(int[] a, int[] b) {
        if (a[0] > b[0])
            return a;
        if (b[0] > a[0])
            return b;
        return new int[]{a[0], (int) ((a[1] + (long) b[1]) % MOD)};
    }
}
