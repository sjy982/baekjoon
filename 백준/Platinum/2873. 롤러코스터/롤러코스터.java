import java.io.*;
import java.util.*;

class Coordinate {
    int x,y;
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int R,C;
    static Coordinate min = new Coordinate(-1,-1);
    static int map[][];
    static StringBuilder ans = new StringBuilder();
    static boolean last_ex = false;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      R = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());
      map = new int[R][C];
      for(int i=0; i<R; i++) {
          StringTokenizer sti = new StringTokenizer(br.readLine());
          for(int j=0; j<C; j++) {
              map[i][j] = Integer.parseInt(sti.nextToken());
              if((i%2==1 && j%2==0) || (i%2==0 && j%2==1)) {
                  if(min.x == -1) {
                      min = new Coordinate(j,i);
                  } else {
                      if(map[min.y][min.x] > map[i][j]) {
                          min = new Coordinate(j,i);
                      }
                  }
              }
          }
      }
      
      if(min.y == R-1) {
          min = new Coordinate(min.x+1, min.y);
          last_ex = true;
      } else {
          min = new Coordinate(min.x+1, min.y+1);
      }
      
      if(R%2==1) {
          for(int i=1; i<=R; i++) {
              for(int j=1; j<=C; j++) {
                  if(i!=R || j!=C) {
                      if(i%2==1) {
                          if(j==C) {
                              ans.append("D");
                          } else {
                              ans.append("R");
                          }
                      } else {
                          if(j==C) {
                              ans.append("D");
                          } else {
                              ans.append("L");
                          }
                      }
                  }
              }
          }
      } else if(C%2==1) {
          for(int i=1; i<=C; i++) {
              for(int j=1; j<=R; j++) {
                  if(i!=C || j!=R) {
                      if(i%2==1) {
                          if(j==R) {
                              ans.append("R");
                          } else {
                              ans.append("D");
                          }
                      } else {
                          if(j==R) {
                              ans.append("R");
                          } else {
                              ans.append("U");
                          }
                      }
                  }
              }
          }
      } else {
          //둘다 짝수면
          String zigzag_1,zigzag_2,c1_zigzag,c2_zigzag;
          char zad; //zigzag after direction
          char d = 'R';
          boolean toggle = true;
          if(min.y % 2 == 1) {
              zad = 'L';
              zigzag_1 = "DR";
              zigzag_2 = "UR";
              if(last_ex) {
                c1_zigzag = "RD";
                c2_zigzag = "RU";
              } else {
                c1_zigzag = "RU";
                c2_zigzag = "RD";
              }
          } else {
              min.x = C+1-min.x;
              zad = 'R';
              zigzag_1 = "DL";
              zigzag_2 = "UL";
              c1_zigzag = "LU";
              c2_zigzag = "LD";
          }
          //반대 세팅
          for(int i=1; i<=R-1; i++) {
              for(int j=1; j<=C; j++) {
                  if(i!=R-1 || j!=C) {
                      if(i==min.y) {
                          if(j==min.x) {
                              if(j==C) {
                                  ans.append("D");
                                  d = zad;
                              } else {
                                  zigzag_1 = c1_zigzag;
                                  zigzag_2 = c2_zigzag;
                                  ans.append(zigzag_1);
                                  toggle = false;
                              }
                          } else {
                              if(j==C) {
                                  ans.append("D");
                                  d = zad;
                              } else {
                                  if(toggle) {
                                      ans.append(zigzag_1);
                                      toggle = false;
                                  } else {
                                      ans.append(zigzag_2);
                                      toggle = true;
                                  }
                              }
                          }
                      } else {
                          if(d=='R') {
                              if(j==C) {
                                  ans.append("D");
                                  d='L';
                              } else {
                                  ans.append("R");
                              }
                          } else if(d=='L') {
                              if(j==C) {
                                  ans.append("D");
                                  d='R';
                              } else {
                                  ans.append("L");
                              }
                          }
                      }
                  }
              }
          }
      }
      System.out.println(ans);
    }
}