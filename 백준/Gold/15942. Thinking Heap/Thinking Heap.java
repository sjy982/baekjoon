import java.io.*;
import java.util.*;

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());
      int k = Integer.parseInt(st.nextToken());
      int p = Integer.parseInt(st.nextToken());
      
      int[] heap = new int[N + 1];
      boolean[] visited = new boolean[N + 1];
      heap[p] = k;
      visited[k] = true;
      boolean isPosible = true;
      
      //부모 구성
      ArrayList<Integer> parent = fillOrderParent(p); //reverse
      int cursorK = 1;
      for(int i=parent.size() - 1; i>=0; i--) {
          if(cursorK >= k) {
              isPosible = false;
              break;
          }
          heap[parent.get(i)] = cursorK;
          visited[cursorK] = true;
          cursorK += 1;
      }
      
      if(!isPosible) {
          System.out.println(-1);
          return;
      }
      
      //자식 구성
      ArrayList<Integer> child = fillOrderChild(p);
      cursorK = k + 1;
      for(int i=0; i<child.size(); i++) {
          if(cursorK > N) {
              isPosible = false;
              break;
          }
          heap[child.get(i)] = cursorK;
          visited[cursorK] = true;
          cursorK += 1;
      }
      
      if(!isPosible) {
          System.out.println(-1);
          return;
      }
      
      //나머지 구성
      cursorK = 1;
      for(int i=1; i<=N; i++) {
          if(heap[i] == 0) {
              while(visited[cursorK]) {
                  cursorK += 1;
              }
              heap[i] = cursorK;
              visited[cursorK] = true;
          }
      }
      
      StringBuilder sb = new StringBuilder();
      for(int i=1; i<=N; i++) {
          sb.append(heap[i]).append("\n");
      }
      System.out.println(sb.toString().trim());
  }
  
  static ArrayList<Integer> fillOrderChild(int p) {
      ArrayList<Integer> result = new ArrayList<>();
      Queue<Integer> que = new LinkedList<>();
      que.add(p);
      
      while(!que.isEmpty()) {
          int parent = que.poll();
          
          int leftChild = parent * 2;
          
          if(leftChild <= N) {
              result.add(leftChild);
              que.add(leftChild);
              int rightChild = leftChild + 1;
              if(rightChild <= N) {
                  result.add(rightChild);
                  que.add(rightChild);
              }
          }
      }
      
      return result;
  }
  
  static ArrayList<Integer> fillOrderParent(int p) {
      ArrayList<Integer> result = new ArrayList<>();
      if(p == 1) {
          return result;
      }
      int nextParent = p / 2;
      result.add(nextParent);
      while(nextParent != 1) {
          nextParent = nextParent / 2;
          result.add(nextParent);
      }
      return result;
  }
}