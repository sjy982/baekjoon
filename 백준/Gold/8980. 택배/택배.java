import java.io.*;
import java.util.*;

class Box implements Comparable<Box> {
    int rInd, amount;
    
    Box(int rInd, int amount) {
        this.rInd = rInd;
        this.amount = amount;
    }
    
    @Override
    public int compareTo(Box o) {
        if(this.rInd < o.rInd) {
            return -1;
        } else if(this.rInd > o.rInd) {
            return 1;
        }
        return 0;
    }
}

public class Main {
    static int N, V, M;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      V = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(br.readLine());
      
      ArrayList<Box>[] vList = new ArrayList[N + 1];
      for(int i=1; i<=N; i++) {
          vList[i] = new ArrayList<>();
      }
      
      for(int i=0; i<M; i++) {
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          int send = Integer.parseInt(st2.nextToken());
          int reiv = Integer.parseInt(st2.nextToken());
          int amount = Integer.parseInt(st2.nextToken());
          vList[send].add(new Box(reiv, amount));
      }
      
      for(int i=1; i<=N; i++) {
          Collections.sort(vList[i]);
      }
      
      int[] arr = new int[N + 1];
      int curV = 0;
      int answer = 0;
      for(int i=1; i<=N; i++) {
          answer += arr[i];//택배 내리고
          curV -= arr[i]; //용량 감소
          arr[i] = 0;
          
          //택배 실는다.
          for(int j=0; j<vList[i].size(); j++) {
              int rInd = vList[i].get(j).rInd;
              int v = vList[i].get(j).amount;
              
              int sum = 0;
              for(int k=i+1; k<=rInd; k++) {
                  sum += arr[k];
              }
              if(sum < V) {
                  //실을 수 있다면
                  int max = V - sum; //최대 이 만큼 실을 수 있다.
                  int realMax = Math.min(max, v); //실제로 실을 수 있는 택배양
                  arr[rInd] += realMax;
                  curV += realMax;
              }
              
              if(curV > V) {
                  //실제로는 택배 용량을 초과했을 수 있음. 그만큼 우선 순위가 낮은 뒤에서부터 -해준다.
                  int subV = curV - V;
                  for(int k=N; k >= rInd + 1; k--) {
                      //N ~ rInd + 1까지 검사
                      if(arr[k] >= subV) {
                          arr[k] -= subV;
                          curV -= subV;
                          break;
                      } else {
                          subV -= arr[k];
                          curV -= arr[k];
                          arr[k] = 0;
                      }
                  }
              }
          }
      }
      System.out.println(answer);
  }
}