import java.io.*;
import java.util.*;

public class Main {
    static int N,t,ans;
    static int line[];
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      ans = 0;
      t = 0;
      line = new int[N];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int i=0; i<N; i++) {
          line[i] = Integer.parseInt(st.nextToken());
      }
      Arrays.sort(line);
      for(int i=0; i<N; i++) {
          t += line[i];
          ans += t;
      }
      System.out.println(ans);
    }
}