import java.io.*;
import java.util.*;

public class Main {
    static int T;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      T = Integer.parseInt(br.readLine());
      StringBuilder sb = new StringBuilder();
      for(int r = 0; r < T; r++) {
          int N = Integer.parseInt(br.readLine());
          int[] line = new int[N];
          StringTokenizer st = new StringTokenizer(br.readLine());
          int min = Integer.MAX_VALUE;
          for(int i=0; i<N; i++) {
              line[i] = Integer.parseInt(st.nextToken());
              min = Math.min(line[i], min);
          }
          
          if(N == 1) {
              sb.append(0).append("\n");
              continue;
          }
          
          long answer = 0;
          for(int i=0; i<N - 1; i++) {
              if(line[i] == min) {
                  continue;
              }
              
              if(line[i] < min) {
                  if(i % 2 == 1) {
                      answer = -1;
                      break;
                  }
                  min = line[i];
                  answer += (long) (line[i - 1] - min) * (long) i;
                  continue;
              }
              //현재 값이 min 값보다 큰 경우임
              int cnt = line[i] - min; //min 값으로 맞춰주기 위해서 필요한 횟수
              
              if(line[i + 1] - cnt < 0) {
                  //min을 못만드는 경우임
                  answer = -1;
                  break;
              }
              //만들 수 있는 경우
              line[i] -= cnt;
              line[i + 1] -= cnt;
              answer += (long) (cnt) * 2;
          }
          
          if(answer != -1) {
              if(line[N - 1] > min) {
                  answer = -1;
              } else if(line[N - 1] < min) {
                  if((N-1) % 2 == 1) {
                      answer = -1;
                  } else {
                      answer += (long) (min - line[N - 1]) * (long) (N -1);
                  }
              }
          }
          
          sb.append(answer).append("\n");
      }
      System.out.println(sb.toString().trim());
  }
}