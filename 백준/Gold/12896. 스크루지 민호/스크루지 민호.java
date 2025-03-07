import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int b, max;
    Node(int b, int max) {
        this.b = b;
        this.max = max;
    }
    
    @Override
    public int compareTo(Node o) {
        if(this.max < o.max) {
            return 1;
        } else if(this.max > o.max) {
            return -1;
        }
        return 0;
    }
}

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
      for(int i=0; i<=N; i++) {
          graph.add(new ArrayList<>());
      }
      
      for(int i=0; i<N - 1; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          int a = Integer.parseInt(st.nextToken());
          int b = Integer.parseInt(st.nextToken());
          graph.get(a).add(b);
          graph.get(b).add(a);
      }
      
      HashMap<Integer, Boolean>[] visited = new HashMap[N + 1];
      PriorityQueue<Node>[] nodePqs = new PriorityQueue[N + 1];
      for(int i=1; i<=N; i++) {
          visited[i] = new HashMap<>();
          nodePqs[i] = new PriorityQueue<>();
      }
      
      int answer = Integer.MAX_VALUE;
      for(int i=1; i<=N; i++) {
          int result = dfs(0, i, graph, visited, nodePqs);
          answer = Math.min(answer, result);
      }
      System.out.println(answer);
  }
  
  static int dfs(int bA, int a, ArrayList<ArrayList<Integer>> graph, HashMap<Integer, Boolean>[] visited, PriorityQueue<Node>[] nodePqs) {
      int fm = findMax(bA, a, visited, nodePqs);
      if(fm != -1) {
          return fm;
      }
      //a에서 전체 노드를 방문하지 않음.
      for(int i=0; i<graph.get(a).size(); i++) {
          int b = graph.get(a).get(i);
          if(b != bA && visited[a].get(b) == null) {
              //방문하지 않았다면.
              int v = dfs(a, b, graph, visited, nodePqs) + 1;
              visited[a].put(b, true); //방문 체크
              nodePqs[a].add(new Node(b, v));
          }
      }
      
      if(bA == 0 || visited[a].get(bA) != null) {
          visited[a].put(a, true);
      }
      
      if(nodePqs[a].size() == 0) {
          return 0;
      }
      
      Node n1 = nodePqs[a].peek();
      if(n1.b != bA) {
          return n1.max;
      }
      
      if(nodePqs[a].size() == 1) {
          return 0;
      }
      
      nodePqs[a].poll();
      Node n2 = nodePqs[a].peek();
      nodePqs[a].add(n1);
      return n2.max;
  }
  
  static int findMax(int bA, int a, HashMap<Integer, Boolean>[] visited, PriorityQueue<Node>[] nodePqs) {
      if(visited[a].get(a) != null) {
          //a에서 전체 노드를 방문했음을 의미한다.
          if(bA == 0) {
              return nodePqs[a].peek().max;
          }
          
          if(nodePqs[a].size() == 1) {
              //bA에서 들어오는 경로밖에 없음.
              return 0;
          } else {
              //여러 경로가 있다는 의미.
              Node n1 = nodePqs[a].peek();
              if(n1.b != bA) {
                  return n1.max;
              }
              
              nodePqs[a].poll();
              Node n2 = nodePqs[a].peek();
              nodePqs[a].add(n1);
              return n2.max;
          }
      }
      return -1;
  }
}