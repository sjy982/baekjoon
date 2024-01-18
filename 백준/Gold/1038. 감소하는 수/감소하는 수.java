import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Long> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dfs(9, 0);
        Collections.sort(list);
        if(N > list.size() - 1) {
            System.out.println(-1);
        } else {
            System.out.println(list.get(N));
        }
    }
    
    static void dfs(int v, int depth) {
        if(depth == 10) {
            return;
        }
        for(int i=v; i>=0; i--) {
            sb.append(i);
            list.add(Long.parseLong(sb.toString()));
            dfs(i - 1, depth + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}