import java.io.*;
import java.util.*;

public class Main {
    static double x, y, c;
    static double ans;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      x = Double.parseDouble(st.nextToken());
      y = Double.parseDouble(st.nextToken());
      c = Double.parseDouble(st.nextToken());
      double d = 26.033749999999998;
      double min_width = 0;
      double max_width = Math.min(x,y);
      //이분 탐색
      while(true) {
          double mid_width = (min_width + max_width)/2;
          double l = Math.sqrt((Math.pow(x, 2) - Math.pow(mid_width, 2)));
          double r = Math.sqrt((Math.pow(y, 2) - Math.pow(mid_width, 2)));
          double a = (l/r * y)/(1 + l/r); //가운데 삼각형 왼쪽 변
          double h = (l/r * mid_width)/(1 + l/r); //왼쪽 삼각형 높이
          double n_c = Math.sqrt((Math.pow(a, 2) - Math.pow(h, 2)));
          if( ((c-0.001)<= n_c) && ((c+0.001)>=n_c) ) {
              ans = mid_width;
              break;
          } else {
              if(n_c < c) {
                  max_width = mid_width;
              } else if(n_c > c) {
                  min_width = mid_width;
              }
          }
      }
      System.out.println(Math.floor(ans*1000)/1000.0); 
    }
}