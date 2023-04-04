import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static HashMap<String, Character> hashMap = new HashMap<>();
    static ArrayList<String> arrayList = new ArrayList<>();
    static StringBuilder ans = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String state = st.nextToken();
            hashMap.put(name, state.charAt(0)); //키 값이 이미 존재한다면 덮어쓴다. hashMap 특징
        }
        hashMap.forEach((key, value) -> {
           if(value == 'e') arrayList.add(key); 
        });
        Collections.sort(arrayList, Collections.reverseOrder());
        for(int i=0; i<arrayList.size(); i++) {
            ans.append(arrayList.get(i) + "\n");
        }
        System.out.println(ans.toString().trim());
    }
}