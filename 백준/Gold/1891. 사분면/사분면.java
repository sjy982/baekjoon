import java.io.*;
import java.util.*;

class Coordinate {
    long x,y;
    Coordinate(long x, long y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int d;
    static int num[];
    static String str_num;
    static Coordinate target = new Coordinate(0,0);
    static Coordinate w_cdn;
    static long max_side;
    static StringBuilder ans = new StringBuilder();
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      d = Integer.parseInt(st.nextToken());
      max_side = (long) Math.pow(2, d);
      num = new int[d];
      str_num = st.nextToken();
      for(int i=0; i<str_num.length(); i++) {
          num[i] = str_num.charAt(i) - '0';
      }
      StringTokenizer sti = new StringTokenizer(br.readLine());
      w_cdn = new Coordinate(Long.parseLong(sti.nextToken()), Long.parseLong(sti.nextToken()) * -1);
      find_coordinate(0, max_side);
      target = new Coordinate(target.x + w_cdn.x, target.y + w_cdn.y);
      if((target.x >=0 && target.x <= max_side - 1) && (target.y >=0 && target.y <= max_side -1)) {
          find_num(max_side);
          System.out.println(ans.toString());
      } else System.out.println(-1);

    }
    
    static void find_coordinate(int ind, long side) {
        if(side/2 == 0) return;
        
        if(num[ind] == 1) {
            target = new Coordinate(target.x + side/2, target.y);
            find_coordinate(ind+1, side/2);
        } else if(num[ind] == 2) {
            target = new Coordinate(target.x, target.y);
            find_coordinate(ind+1, side/2);
        } else if(num[ind] == 3) {
            target = new Coordinate(target.x, target.y + side/2);
            find_coordinate(ind+1, side/2);
        } else {
            target = new Coordinate(target.x + side/2, target.y + side/2);
            find_coordinate(ind+1, side/2);
        }
    }
    
    static void find_num(long side) {
        if(side/2 == 0) return;
        Coordinate s_cdn = new Coordinate(target.x/(side/2), target.y/(side/2));
        if(s_cdn.x == 0 && s_cdn.y == 0) {
            ans.append('2');
            target = new Coordinate(target.x, target.y);
            find_num(side/2);
        } else if(s_cdn.x == 0 && s_cdn.y == 1) {
            ans.append('3');
            target = new Coordinate(target.x, target.y - side/2);
            find_num(side/2);
        } else if(s_cdn.x == 1 && s_cdn.y == 0) {
            ans.append('1');
            target = new Coordinate(target.x - side/2, target.y);
            find_num(side/2);
        } else if(s_cdn.x == 1 && s_cdn.y == 1) {
            ans.append('4');
            target = new Coordinate(target.x - side/2, target.y - side/2);
            find_num(side/2);
        }
    }
}