import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] boxs;
    static int jokerBox;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        boxs = new int[N][M];
        for(int i=0; i<N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                boxs[i][j] = Integer.parseInt(st2.nextToken());
            }
        }
        if(!findJokerBox()) {
            System.out.println(0);
        } else {
            int answer = 0;
            boolean[] visited = new boolean[M];
            for(int i=0; i<N; i++) {
                if(jokerBox != i) {
                    int cout = 0;
                    ArrayList<Integer> cardNum = new ArrayList<>();
                    for(int j=0; j<M; j++) {
                        if(boxs[i][j] != 0) {
                            cout += 1;
                            cardNum.add(j);
                            if(cout >= 2) {
                                answer += 1;
                                break;
                            }
                        }
                    }
                    if(cout == 1) {
                        if(visited[cardNum.get(0)]) {
                            answer += 1;
                        } else {
                            visited[cardNum.get(0)] = true;
                        }
                    }
                }
            }
            System.out.println(answer);
        }
    }
    
    static boolean findJokerBox() {
        boolean[] visited = new boolean[M];
        for(int i=0; i<N; i++) {
            int cout = 0;
            for(int j=0; j<M; j++) {
                if(boxs[i][j] != 0) {
                    cout += 1;
                    if(visited[j] || cout >= 2) {
                        jokerBox = i;
                        return true;
                    } else {
                        visited[j] = true;
                    }
                }
            }
        }
        return false;
    }
}