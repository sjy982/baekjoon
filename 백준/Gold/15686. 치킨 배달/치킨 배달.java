import java.io.*;
import java.util.*;

class Coordinate {
    int x,y;
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N,M;
    static ArrayList<Coordinate> home = new ArrayList<>();
    static ArrayList<Coordinate> chk_home = new ArrayList<>();
    static Stack<Coordinate> result = new Stack<>();
    static ArrayList<Integer> ans = new ArrayList<>();
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      for(int i=1; i<=N; i++) {
          StringTokenizer n_st = new StringTokenizer(br.readLine());
          for(int j=1; j<=N; j++) {
              int v = Integer.parseInt(n_st.nextToken());
              if(v == 1) home.add(new Coordinate(j, i));
              else if(v == 2) chk_home.add(new Coordinate(j, i));
          }
      }
      DFS(0);
      System.out.println(Collections.min(ans));
    }
    
    static void DFS(int ind) {
        if(result.size()==M) {
            ans.add(find_chk_distance(result));
            return;
        } 
        for(int i=ind; i<chk_home.size(); i++) {
            result.push(chk_home.get(i));
            DFS(i+1);
            result.pop();
        }
    }
    
    static int find_chk_distance(Stack<Coordinate> ch) {
        int sum_distance = 0;
        for(int i=0; i<home.size(); i++) {
            int distance = Integer.MAX_VALUE;
            Coordinate h = home.get(i);
            for(int j=0; j<ch.size(); j++) {
                Coordinate c = ch.get(j);
                distance = Math.min(distance, Math.abs(h.x - c.x) + Math.abs(h.y - c.y));
            }
            sum_distance += distance;
        }
        return sum_distance;
    }
}