import java.io.*;
import java.util.*;

class HomeWork implements Comparable<HomeWork> {
    int deadline, cup;
    
    HomeWork(int deadline, int cup) {
        this.deadline = deadline;
        this.cup = cup;
    }
    
    @Override
    public int compareTo(HomeWork o) {
        if(this.cup < o.cup) {
            return -1;
        } else if(this.cup > o.cup) {
            return 1;
        }
        return 0;
    }
}

public class Main {
    static int N;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        ArrayList<HomeWork> list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new HomeWork(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        
        Collections.sort(list, new Comparator<HomeWork>() {
           @Override
           public int compare(HomeWork h1, HomeWork h2) {
               if(h1.deadline < h2.deadline) {
                   return -1;
               } else if(h1.deadline > h2.deadline) {
                   return 1;
               }
               return 0;
           }
        });
        
        PriorityQueue<HomeWork> pQue = new PriorityQueue<>();
        for(int i=0; i<N; i++) {
            int cp = list.get(i).deadline - pQue.size(); //현재 que에 넣을 수 있는 개수
            if(cp > 0) {
                pQue.add(list.get(i));
            } else if(cp == 0) {
                if(pQue.peek().cup < list.get(i).cup) {
                    pQue.poll();
                    pQue.add(list.get(i));
                }
            }
        }
        
        int answer = 0;
        while(!pQue.isEmpty()) {
            answer += pQue.poll().cup;
        }
        System.out.println(answer);
    }
}