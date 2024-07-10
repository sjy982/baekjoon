import java.io.*;
import java.util.*;

public class Main {
    static int G, P;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        
        int[] gate = new int[G + 1];
        int answer = 0;
        for(int i=0; i<P; i++) {
            int maxGate = Integer.parseInt(br.readLine());
            if(!docking(gate, maxGate)) {
                break;
            }
            answer += 1;
        }
        System.out.println(answer);
    }
    
    static boolean docking(int[] gate, int maxGate) {
        int cursor = maxGate;
        while(gate[cursor] != 0) {
            if(gate[cursor] == -1) {
                return false;
            }
            cursor = gate[cursor];
        }
        int newGate = cursor == 1 ? -1 : cursor - 1;
        
        for(int i=cursor; i<=maxGate; i++) {
            gate[i] = newGate;
        }
        
        return true;
    }
}