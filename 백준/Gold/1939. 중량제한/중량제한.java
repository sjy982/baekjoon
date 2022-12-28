import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean visited[];
    static HashMap<Integer, HashMap<Integer, Long>> weight_map = new HashMap<>();
    static int start,end;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      for(int i=0; i<=N; i++) {
          graph.add(new ArrayList<>());
          weight_map.put(i, new HashMap<>());
      }
      for(int i=0; i<M; i++) {
          StringTokenizer n_st = new StringTokenizer(br.readLine());
          int A = Integer.parseInt(n_st.nextToken());
          int B = Integer.parseInt(n_st.nextToken());
          long C = Long.parseLong(n_st.nextToken());
          graph.get(A).add(B);
          graph.get(B).add(A);
          if(weight_map.get(A).get(B) == null) {
              weight_map.get(A).put(B, C);
              weight_map.get(B).put(A, C);
          } else {
              if(weight_map.get(A).get(B) < C) {
                  weight_map.get(A).put(B, C);
                  weight_map.get(B).put(A, C);
              }
          }
      }
      StringTokenizer n_st = new StringTokenizer(br.readLine());
      start = Integer.parseInt(n_st.nextToken());
      end = Integer.parseInt(n_st.nextToken());
      //최소 중량, 최대 중량 구하기
      long min_weight = 1;
      long max_weight = -1;
      for(int i=0; i<graph.get(start).size(); i++) {
          int b = graph.get(start).get(i);
          if(max_weight < weight_map.get(start).get(b)) max_weight = weight_map.get(start).get(b);
      }
      //이분 탐색
      while(min_weight != max_weight) {
          long mid_weight;
          if((min_weight + max_weight)%2 == 0) mid_weight = (min_weight + max_weight)/2;
          else mid_weight = (min_weight + max_weight)/2 + 1;
          //최소 중량, 최대 중량 업데이트
          visited = new boolean[N+1];
          if(BFS(mid_weight)) {
              min_weight = mid_weight;
          } else {
              max_weight = mid_weight - 1;
          }
      }
      System.out.println(min_weight);
    }
    
    static boolean BFS(long w) {
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        visited[start] = true;
        while(que.size() != 0) {
            int land = que.poll();
            if(land == end) return true; //탐색 성공
            for(int i=0; i<graph.get(land).size(); i++) {
                int d_land = graph.get(land).get(i);
                if(!visited[d_land] && (w <=weight_map.get(land).get(d_land))) {
                    que.add(d_land);
                    visited[d_land] = true;
                }
            }
        }
        return false; //실패
    }
}