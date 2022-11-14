import java.io.*;
import java.util.*;

class Multitap {
    int v,ns;
    Multitap(int v, int ns) {
        this.v = v;
        this.ns = ns;
    }
}

public class Main {
    static Multitap multitap[];
    static int schedule[];
    static ArrayList<Integer> ea_schedule[];
    static int N,K;
    static int ans = 0;
    static int socket = 0;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      multitap = new Multitap[N];
      schedule = new int[K];
      ea_schedule = new ArrayList[K+1];
      for(int i=0; i<K+1; i++) {
          ea_schedule[i] = new ArrayList<Integer>();
      }
      StringTokenizer sti = new StringTokenizer(br.readLine());
      for(int i=0; i<K; i++) {
          int n = Integer.parseInt(sti.nextToken());
          schedule[i] = n;
          ea_schedule[n].add(i);
      }
      
      for(int i=0; i<K; i++) {
          int ea = schedule[i];
          int ns = next_schedule(ea, i);
          int mi = check(ea);
          if(socket < N) {
              if(mi != -1) {
                  //이미 꽂혀있음.
                  multitap[mi] = new Multitap(ea, ns);
              } else {
                  multitap[socket] = new Multitap(ea, ns);
                  socket += 1;
              }
          } else {
              if(mi != -1) {
                  //이미 꽂혀있음.
                  multitap[mi] = new Multitap(ea, ns);
              } else {
                  int emi = s_compare();
                  multitap[emi] = new Multitap(ea, ns);
                  ans += 1;
              }
          }
      }
      System.out.println(ans);
    }
    
    static int check(int n) {
        for(int i=0; i<N; i++) {
            if(multitap[i] != null) {
                if(multitap[i].v == n) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    static int next_schedule(int n, int cs) {
        for(int i=0; i<ea_schedule[n].size(); i++) {
            if(ea_schedule[n].get(i) > cs) {
                return ea_schedule[n].get(i);
            }
        }
        return 101;
    }
    
    static int s_compare() {
        int max = multitap[0].ns; 
        int max_i = 0;
        for(int i=0; i<N; i++) {
           if(max < multitap[i].ns) {
               max = multitap[i].ns;
               max_i = i;
           }
        }
        return max_i;
    }
}