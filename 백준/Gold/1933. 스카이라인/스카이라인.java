import java.io.*;
import java.util.*;

class Building implements Comparable<Building>{
    long l ,h, r;
    public Building(long l, long h, long r) {
        this.l = l;
        this.h = h;
        this.r = r;
    }
    
    public int compareTo(Building n) {
        long dif = this.l - n.l;
        if(dif > 0) return 1;
        else if(dif < 0) return -1;
        else return 0;
    }
}

public class Main {
    static int N;
    static PriorityQueue<Building> pq = new PriorityQueue<>();
    static Building hest_building;
    static ArrayList<Building> ans = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      for(int i=0; i<N; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          pq.add(new Building(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())));
      }
      hest_building = pq.poll();
      while(pq.size() != 0) {
          Building nb = pq.poll();
          if((hest_building.l <= nb.l) && (hest_building.r > nb.l)) {
              if(hest_building.h >= nb.h) {
                  if(hest_building.r < nb.r) {
                      pq.add(new Building(hest_building.r, nb.h, nb.r));
                  }
              } else {
                  if(hest_building.l == nb.l) {
                      if(hest_building.r > nb.r) {
                          pq.add(new Building(nb.r, hest_building.h, hest_building.r));
                      }
                  } else {
                      if(hest_building.r > nb.r) {
                          pq.add(new Building(nb.r, hest_building.h, hest_building.r));
                      }
                      ans.add(new Building(hest_building.l, hest_building.h, nb.l));
                  }
                  hest_building = nb;
              }
          } else if(hest_building.r == nb.l && hest_building.h == nb.h){
              hest_building.r = nb.r;
          } else {
              ans.add(hest_building);
              hest_building = nb;
          }
      }
      ans.add(hest_building);
      for(int i=0; i<ans.size(); i++) {
          str_append(ans.get(i).l, ans.get(i).h);
          if(i != ans.size() - 1) {
              if(ans.get(i).r != ans.get(i+1).l) str_append(ans.get(i).r, 0);
          } else {
              str_append(ans.get(i).r, 0);
          }
      }
      System.out.println(sb.toString());
    }
    
    static void str_append(long x, long h) {
        sb.append(x);
        sb.append(" ");
        sb.append(h);
        sb.append(" ");
    }
}