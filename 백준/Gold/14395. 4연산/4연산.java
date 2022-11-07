import java.io.*;
import java.util.*;

class Node {
    long s;
    String op;
    Node(long s, String op) {
        this.s = s;
        this.op = op;
    }
}

public class Main {
    static final char op_arr[] = {'*','+','-','/'};
    static final long MAX_RANGE = 1000000000;
    static Node start;
    static String ans;
    static long S,T;
    static Map<Long, Boolean> map_visited = new HashMap<Long, Boolean>();
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      S = Integer.parseInt(st.nextToken());
      T = Integer.parseInt(st.nextToken());
      start = new Node(S, "");
      if(S==T) {
          System.out.println(0);
      } else {
          BFS();
          if(ans == null) {
              System.out.println(-1);
          } else {
              System.out.println(ans);
          }
      }
    }
    
    static void BFS() {
        Queue<Node> que = new LinkedList<>();
        que.add(start);
        map_visited.put(start.s, true);
        while(!que.isEmpty()) {
            Node n = que.poll();
            if(n.s == T) {
                ans = n.op;
                return;
            }
            for(int i=0; i<op_arr.length; i++) {
                long ns = calculate(n.s, op_arr[i]);
                if(ns>=1 && ns <= MAX_RANGE) {
                    if(map_visited.get(ns) == null) {
                        map_visited.put(ns, true);
                        que.add(new Node(ns, n.op+op_arr[i]));
                    }
                }
            }
        }
        return;
    }
    static long calculate(long v, char op) {
        if(op == '*') {
            return v*v;
        } else if(op == '+') {
            return v+v;
        } else if(op == '-') {
            return v-v;
        } else {
            return v/v;
        }
    }
    
}