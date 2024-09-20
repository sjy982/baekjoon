import java.io.*;
import java.util.*;

public class Main {
    static int[] cube;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int l = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      int h = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(br.readLine());
      cube = new int[n];
      
      for(int i=0; i<n; i++) {
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          st2.nextToken();
          cube[i] = Integer.parseInt(st2.nextToken()); 
      }
      System.out.println(answer(l, w, h));
  }
  
  static int answer(int l, int w, int h) {
      int i = maxCube(l, w, h);
      if(i == -1) {
          return -1;
      }
      int cl = (int) Math.pow(2, i);
      int cnt = h / cl;
      cnt = cnt <= cube[i] ? cnt : cube[i];
      cube[i] -= cnt;
      
      if(h - (cl * cnt) > 0) {
          int r = answer(cl, cl, h - (cl * cnt));
          if(r == -1) {
              return -1;
          }
          cnt += r;
      }
      
      if(l - cl > 0) {
          int r = answer(l - cl, cl, h);
          if(r == -1) {
              return -1;
          }
          cnt += r;
      }
      
      if(w - cl > 0) {
          int r = answer(l, w - cl, h);
          if(r == -1){
              return -1;
          }
          cnt += r;
      }
      
      return cnt;
  }
  
  static int maxCube(int l, int w, int h) {
      for(int i = cube.length - 1; i>=0; i--) {
          int cl = (int) Math.pow(2, i);
          if(0 < cube[i] && l >= cl && w >= cl && h >= cl ) {
              return i;
          }
      }
      //-1이면 불가능한 경우가 됨.
      return -1;
  }
}