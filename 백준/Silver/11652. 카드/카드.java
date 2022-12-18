import java.io.*;
import java.util.*;

class Card {
    long i;
    int c;
    Card(long i, int c) {
        this.i = i;
        this.c = c;
    }
}

public class Main {
    static int N;
    static HashMap<Long, Integer> card_map = new HashMap<Long, Integer>();
    static ArrayList<Card> card_list = new ArrayList<>();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            long key = Long.parseLong(br.readLine());
            if(card_map.get(key) == null) {
                card_map.put(key, 1);
            } else {
                card_map.replace(key, card_map.get(key) + 1);
            }
        }
        card_map.forEach((key,value) -> {
            card_list.add(new Card(key, value));
        });
        
        Collections.sort(card_list, (a,b) -> {
           int c_dif = a.c - b.c;
           if(c_dif > 0) return -1;
           if(c_dif < 0) return 1;
           if(c_dif == 0) {
               if(a.i > b.i) return 1;
               if(a.i < b.i) return -1;
           }
           return 0;
        });
        
        System.out.println(card_list.get(0).i);
    }
}