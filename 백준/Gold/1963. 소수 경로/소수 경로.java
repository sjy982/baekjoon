import java.io.*;
import java.util.*;

class Node {
    int v,c;
    Node(int v, int c) {
        this.v = v;
        this.c = c;
    }
}

public class Main {
    static boolean nDecimal[] = new boolean[10000];
    static int T;
    static String ans;
    public static void main(String args[]) throws IOException {
      nDecimal[0] = true;
      nDecimal[1] = true;
      for(int i=2; i*i<=9999; i++) {
          for(int j=i*i; j<=9999; j+=i) {
              nDecimal[j] = true;
          }
      }
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      T = Integer.parseInt(br.readLine());
      ans = "";
      for(int i=0; i<T; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          int s = Integer.parseInt(st.nextToken());
          int e = Integer.parseInt(st.nextToken());
          boolean clone_nDecimal[] = nDecimal.clone();
          int result = BFS(s,e,clone_nDecimal);
          if(result == -1) {
              ans += "Impossible\n";
          } else {
              ans += Integer.toString(result) + "\n";
          }
      }
      System.out.println(ans.trim());
    }
    static int BFS(int start,int end, boolean nDec[]) {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(start, 0));
        nDec[start] = true;
        while(!que.isEmpty()) {
            Node n = que.poll();
            if(n.v == end) {
                return n.c;
            }
            for(int i=0; i<4; i++) {
                for(int j=0; j<=9; j++) {
                    //첫째 자리는 0빼고
                    int nv = 0;
                    if(i==0) {
                        if(j!=0) {
                            nv = j * 1000 + n.v % 1000;
                        }
                    } else if(i==1) {
                        nv = n.v/1000 * 1000 + j * 100 + n.v % 100;
                    } else if(i==2) {
                        nv = n.v/100 * 100 + j * 10 + n.v % 10;
                    } else {
                        nv = n.v/10 * 10 + j;
                    }
                    if(!nDec[nv]) {
                        nDec[nv] = true;
                        que.add(new Node(nv, n.c+1));
                    }
                }
            }
            
        }
        return -1;
    }
}