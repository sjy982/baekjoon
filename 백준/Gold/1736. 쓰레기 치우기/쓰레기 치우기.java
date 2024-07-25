import java.io.*;
import java.util.*;

class Trash implements Comparable<Trash> {
    int x, y;
    boolean processed = false;
    Trash(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public int compareTo(Trash o) {
        if(this.x < o.x) {
            return -1;
        } else if(this.x > o.x) {
            return 1;
        } else {
            if(this.y < o.y) {
                return -1;
            } else if(this.y > o.y) {
                return 1;
            }
        }
        return 0;
    }
}

public class Main {
    static int N,M;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      ArrayList<Trash>[] col = new ArrayList[M];
      ArrayList<Trash> trashList = new ArrayList<>();
      for(int i=0; i<M; i++) {
          col[i] = new ArrayList<>();
      }
      
      for(int i=0; i<N; i++) {
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          for(int j=0; j<M; j++) {
              if(Integer.parseInt(st2.nextToken()) == 1) {
                  Trash trash = new Trash(j, i);
                  col[j].add(trash);
                  trashList.add(trash);
              }
          }
      }
      Collections.sort(trashList);
      
      int answer = 0;
      for(int i=0; i<trashList.size(); i++) {
          if(!trashList.get(i).processed) {
              answer += 1;
              clean(trashList.get(i), col);
          }
      }
      System.out.println(answer);
  }
  
  static void clean(Trash start, ArrayList<Trash>[] col) {
      Trash cur = start;
      while(cur.x < M) {
          cur = clean(cur, col[cur.x]);
      }
  }
  
  static Trash clean(Trash cur, ArrayList<Trash> col) {
      Trash result = cur;
      if(col.size() != 0 && col.get(col.size() - 1).y >= cur.y) {
          result = col.get(col.size() - 1);
      }
      while(col.size() != 0 && col.get(col.size() - 1).y >= cur.y) {
          col.get(col.size() - 1).processed = true;
          col.remove(col.size() - 1);
      }
      result.x += 1;
      return result;
  }
  
}