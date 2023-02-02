import java.io.*;
import java.util.*;

class RotationInfo {
    int r, c, s;
    RotationInfo(int r, int c, int s) {
        this.r = r;
        this.c = c;
        this.s = s;
    }
}

class Coordinate {
    int x, y;
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N,M,K;
    static int A[][];
    static ArrayList<RotationInfo> rotation_list = new ArrayList<>();
    static Stack<RotationInfo> result = new Stack<>();
    static int ans = Integer.MAX_VALUE;
    static boolean visited[];
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      A = new int[N][M];
      visited = new boolean[K];
      for(int i=0; i<N; i++) {
          StringTokenizer n_st = new StringTokenizer(br.readLine());
          for(int j=0; j<M; j++) {
              A[i][j] = Integer.parseInt(n_st.nextToken());
          }
      }
      for(int i=0; i<K; i++) {
          StringTokenizer n_st = new StringTokenizer(br.readLine());
          rotation_list.add(new RotationInfo(Integer.parseInt(n_st.nextToken()), Integer.parseInt(n_st.nextToken()), Integer.parseInt(n_st.nextToken())));
      }
      DFS();
      System.out.println(ans);
    }
    static void DFS() {
        if(result.size() == K) {
            int clone_A[][] = new int[N][M];
            deep_copy(clone_A);
            for(int i=0; i<result.size(); i++) {
                rotation_operation(result.get(i), clone_A);
            }
            for(int i=0; i<clone_A.length; i++) {
                int sum = 0;
                for(int j=0; j<clone_A[i].length; j++) {
                    sum += clone_A[i][j];
                }
                ans = Math.min(ans, sum);
            }
            return;
        }
        for(int i=0; i<K; i++) {
            if(!visited[i]) {
                visited[i] = true;
                result.push(rotation_list.get(i));
                DFS();
                visited[i] = false;
                result.pop();
            }
        }
    }
    
    static void deep_copy(int arr[][]) {
        for(int i=0; i<arr.length; i++) {
            arr[i] = A[i].clone();
        }
    }
    
    static void rotation_operation(RotationInfo n, int n_A[][]) {
        int repeat = ((n.c + n.s) - (n.c - n.s) + 1)/2; //반복 횟수
        for(int i=0; i<repeat; i++) {
            Coordinate lt = new Coordinate(n.c - n.s - 1 + i, n.r - n.s - 1 + i); //left top
            Coordinate rb = new Coordinate(n.c + n.s - 1 - i, n.r + n.s - 1 - i); //right bottom
            //left top부터 시작
            Coordinate cur_cdn = new Coordinate(lt.x, lt.y);
            int cur_v = n_A[lt.y][lt.x];
            while(true) {
                if(cur_cdn.x == lt.x && cur_cdn.y == lt.y+1) {
                    n_A[lt.y][lt.x] = cur_v;
                    break;
                }
                int temp = 0;
                if(cur_cdn.x != rb.x && cur_cdn.y == lt.y) {
                    temp = n_A[cur_cdn.y][cur_cdn.x+1];
                    n_A[cur_cdn.y][cur_cdn.x+1] = cur_v;
                    cur_cdn = new Coordinate(cur_cdn.x+1, cur_cdn.y);
                } else if(cur_cdn.x == rb.x && cur_cdn.y != rb.y) {
                    temp = n_A[cur_cdn.y+1][cur_cdn.x];
                    n_A[cur_cdn.y+1][cur_cdn.x] = cur_v;
                    cur_cdn = new Coordinate(cur_cdn.x, cur_cdn.y+1);
                } else if(cur_cdn.x != lt.x && cur_cdn.y == rb.y) {
                    temp = n_A[cur_cdn.y][cur_cdn.x-1];
                    n_A[cur_cdn.y][cur_cdn.x-1] = cur_v;
                    cur_cdn = new Coordinate(cur_cdn.x-1, cur_cdn.y);
                } else if(cur_cdn.x == lt.x && cur_cdn.y != lt.y) {
                    temp = n_A[cur_cdn.y-1][cur_cdn.x];
                    n_A[cur_cdn.y-1][cur_cdn.x] = cur_v;
                    cur_cdn = new Coordinate(cur_cdn.x, cur_cdn.y-1);
                }
                cur_v = temp;
            }
        }
    }
}