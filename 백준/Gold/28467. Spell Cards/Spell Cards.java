import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        
        System.out.println(answer(list));
    }
    
    static long answer(ArrayList<Integer> list) {
        long result = 0;
        
        while(list.size() != 1) {
            int min = Integer.MAX_VALUE;
            int cInd = -1;
            
            for(int i=0; i<list.size() - 1; i++) {
                if(list.get(i) <= list.get(i + 1)) {
                    //i + 1이 더 큼
                    if(min > list.get(i + 1)) {
                        min = list.get(i + 1);
                        cInd = i;
                        
                    }
                } else {
                    //i가 더 큼
                    if(min > list.get(i)) {
                        min = list.get(i);
                        cInd = i;
                        
                    }
                }
            }
            
            //합친다.
            int newCard = Math.max(list.get(cInd), list.get(cInd + 1));
            result += list.get(cInd) + list.get(cInd + 1);
            //지운다
            list.remove(cInd);
            list.remove(cInd);
            list.add(cInd, newCard);
        }
        return result;
    }
}