import java.io.*;
import java.util.*;

class Faucet implements Comparable<Faucet> {
    long t, a, b;
    Faucet(long t, long a, long b) {
        this.t = t;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public int compareTo(Faucet o) {
        if(this.t < o.t) {
            return -1;
        } else if(this.t > o.t) {
            return 1;
        }
        return 0;
    }
}

public class Main {
    static int N, K;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      ArrayList<Faucet> list = new ArrayList<>();
      
      for(int i=0; i<N; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          list.add(new Faucet(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())));
      }
      
      K = Integer.parseInt(br.readLine());
      StringBuilder sb = new StringBuilder();
      
      if(N == 1) {
          for(int e = 0; e<K; e++) {
              StringTokenizer st = new StringTokenizer(br.readLine());
              long T = Long.parseLong(st.nextToken());
              long Q = Long.parseLong(st.nextToken());
              if(list.get(0).a > Q || list.get(0).b < Q || list.get(0).t != T) {
                 sb.append("no").append("\n");
              } else {
                 sb.append("yes").append("\n");
              }
          }
          System.out.println(sb.toString().trim());
          return;
      }
      
      Collections.sort(list);
      Faucet minF = list.get(0);
      list.remove(0);
      
      for(int i=0; i<list.size(); i++) {
          list.get(i).t -= minF.t;
      }
      Collections.sort(list);
      
      
      for(int e=0; e<K; e++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          long T = Long.parseLong(st.nextToken());
          long Q = Long.parseLong(st.nextToken());
          
          long V = T * Q - (minF.t * Q); //최종 값
          
          long curV = 0;
          for(int i=0; i<list.size(); i++) {
              curV += list.get(i).t * list.get(i).a;
          }
          
          if(V < curV) {
              sb.append("no").append("\n");
              continue;
          }
          
          //curV <= V인 상태
          long left = V - curV;
          
          double max = 0; //t가 작은 값을 우선적으로 채워주기 때문에 가장 큰 값이 됨 
          for(int i=0; i<list.size(); i++) {
              long leftF = (list.get(i).b - list.get(i).a) *list.get(i).t;
              if(leftF <= left) {
                  max += list.get(i).b;
                  left -= leftF;
              } else {
                  max += list.get(i).a + (double) left/list.get(i).t;
                  left = 0;
              }
          }
          
          if(left != 0) {
              sb.append("no").append("\n");
              continue;
          }
          
          if(minF.b < Q - max) {
              //가장 작은 값이 b보다 크다면
              sb.append("no").append("\n");
              continue;
          }
          
          left = V - curV;
          double min = 0; //t가 큰 값을 우선적으로 채워주기 때문에 가장 작은 값이 됨.
          for(int i=list.size() - 1; i>=0; i--) {
              long leftF = (list.get(i).b - list.get(i).a) *list.get(i).t;
              if(leftF <= left) {
                  min += list.get(i).b;
                  left -= leftF;
              } else {
                  min += list.get(i).a + (double) left/list.get(i).t;
                  left = 0;
              }
          }
          
          if(Q - min < minF.a) {
              //가장 큰 값이 a보다 작다면
              sb.append("no").append("\n");
              continue;
          }
          
          sb.append("yes").append("\n");
      }
      System.out.println(sb.toString().trim());
  }
}