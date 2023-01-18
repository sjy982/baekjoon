import java.io.*;
import java.util.*;

class Size {
    int h,w;
    Size(int h, int w) {
        this.h = h;
        this.w = w;
    }
} 

public class Main {
    static Size graph_paper;
    static int N;
    static ArrayList<Size> sticker = new ArrayList<>();
    static ArrayList<Integer> ans = new ArrayList<>();
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      graph_paper = new Size(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
      N = Integer.parseInt(br.readLine());
      for(int i=0; i<N; i++) {
          StringTokenizer n_st = new StringTokenizer(br.readLine());
          sticker.add(new Size(Integer.parseInt(n_st.nextToken()), Integer.parseInt(n_st.nextToken())));
      }
      for(int i=0; i<N; i++) {
          int s1 = sticker.get(i).h;
          int s2 = sticker.get(i).w;
          if(s1<=graph_paper.h && s2<=graph_paper.w) {
              ArrayList<Size> left_gf = new ArrayList<>();
              left_gf.add(new Size(graph_paper.h, graph_paper.w - s2));
              left_gf.add(new Size(graph_paper.h - s1, graph_paper.w));
              for(int j=i+1; j<N; j++) {
                  if(check(sticker.get(j).h, sticker.get(j).w, left_gf)) {
                      ans.add(sticker.get(i).h * sticker.get(i).w + sticker.get(j).h * sticker.get(j).w);
                  }
              }
          }
          if(s2<=graph_paper.h && s1<=graph_paper.w) {
              ArrayList<Size> left_gf = new ArrayList<>();
              left_gf.add(new Size(graph_paper.h, graph_paper.w - s1));
              left_gf.add(new Size(graph_paper.h - s2, graph_paper.w));
              for(int j=i+1; j<N; j++) {
                  if(check(sticker.get(j).h, sticker.get(j).w, left_gf)) {
                      ans.add(sticker.get(i).h * sticker.get(i).w + sticker.get(j).h * sticker.get(j).w);
                  }
              }
          }
      }
      if(ans.size() == 0) {
          System.out.println(0);
      } else {
          System.out.println(Collections.max(ans));
      }
    }
    static boolean check(int s1, int s2, ArrayList<Size> l_gp) {
        for(int i=0; i<l_gp.size(); i++) {
            int g_h = l_gp.get(i).h;
            int g_w = l_gp.get(i).w;
            if((s1<=g_h && s2<=g_w) || (s2<=g_h && s1<=g_w)) return true;
        }
        return false;
    }
}