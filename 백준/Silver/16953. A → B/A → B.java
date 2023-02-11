import java.io.*;
import java.util.*;

class NumInfo {
    long n;
    int c;
    NumInfo(long n, int c) {
        this.n = n;
        this.c = c;
    }
}

public class Main {
    static long A,B;
    static Hashtable<Long, Boolean> visited = new Hashtable<>();
    static int ans = -1;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        BFS();
        System.out.println(ans);
    }
    static void BFS() {
        Queue<NumInfo> que = new LinkedList<>();
        que.add(new NumInfo(A, 0));
        visited.put(A, true);
        while(que.size()!=0) {
            NumInfo v = que.poll();
            if(v.n == B) {
                ans = v.c+1;
                break;
            }
            long v1 = v.n * 2;
            long v2 = v.n * 10 + 1;
            if(v1 <= B && visited.get(v1) == null) {
                que.add(new NumInfo(v1, v.c+1));
                visited.put(v1, true);
            }
            if(v2 <= B && visited.get(v2) == null) {
                que.add(new NumInfo(v2, v.c+1));
                visited.put(v2, true);
            }
        }
    }
}