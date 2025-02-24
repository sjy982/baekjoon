import java.io.*;
import java.util.*;

class Problem implements Comparable<Problem> {
    int algo, d;
    Problem(int algo, int d) {
        this.algo = algo;
        this.d = d;
    }
    
    @Override
    public int compareTo(Problem o) {
        if(this.d < o.d) {
            return -1;
        } else if(this.d > o.d) {
            return 1;
        }
        return 0;
    }
}

public class Main {
    static int N, K;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      
      if(N == 1) {
          System.out.println(0);
          return;
      }
      
      ArrayList<Problem> list = new ArrayList<>();
      for(int i=1; i<=N; i++) {
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          for(int j=0; j<K; j++) {
              int d = Integer.parseInt(st2.nextToken());
              list.add(new Problem(i, d));
          }
      }
      Collections.sort(list);
      int[] visited = new int[N + 1];
      int left = 0;
      
      for(int i=0; i<list.size(); i++) {
          if(visited[list.get(i).algo] + 1 == K) {
              left = i;
              break;
          }
          visited[list.get(i).algo] += 1;
      }
      
      int right = moveRightCursor(list.size() - 1, visited, list); //초기에는 left와 right는 절대 같을 수 없음.
      int answer = list.get(right).d - list.get(left).d;
      for(int i=left - 1; i>=0; i--) {
          //left를 왼쪽으로 한 칸씩 움직여준다.
          //그로인해 right가 왼쪽으로 움직이는 경우를 체크
          visited[list.get(i).algo] -= 1;
          for(int j=right; j>=1; j--) {
              //right가 왼쪽으로 움직였을 때
              if(visited[list.get(j).algo] + 1 == K) {
                  //불가능한 경우는 움직일 수 없음
                  right = j;
                  break;
              }
              //가능하다면
              visited[list.get(j).algo] += 1;
              if(list.get(i).algo != list.get(j - 1).algo) {
                answer = Math.min(answer, list.get(j - 1).d - list.get(i).d);
              }
          }
      }
      System.out.println(answer);
  }
  
  static int moveRightCursor(int cur, int[] visited, ArrayList<Problem> list) {
      int result = cur;
      for(int i=cur; i>=0; i--) {
          if(visited[list.get(i).algo] + 1 == K) {
              return i;
          }
          visited[list.get(i).algo] += 1;
      }
      return result;
  }
}