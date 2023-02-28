import java.io.*;
import java.util.*;

class Coordinate {
    int x, y;
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int R,C;
    static char pasture[][];
    static ArrayList<Coordinate> wolf = new ArrayList<>();
    static final int dx[] = {-1, 0, 1, 0};
    static final int dy[] = {0, -1, 0, 1};
    static boolean isPosi = true;
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        pasture = new char[R][C];
        for(int i=0; i<R; i++) {
            String input = br.readLine();
            for(int j=0; j<C; j++) {
                pasture[i][j] = input.charAt(j);
                if(pasture[i][j]=='W') wolf.add(new Coordinate(j,i));
                else if(pasture[i][j]=='.') pasture[i][j] = 'D';
            }
        }
        for(int i=0; i<wolf.size(); i++) {
            Coordinate wo_lc = wolf.get(i);
            for(int j=0; j<4; j++) {
                int nx = wo_lc.x + dx[j];
                int ny = wo_lc.y + dy[j];
                if((nx>=0 && nx<=C-1) && (ny>=0 && ny<=R-1)) {
                    if(pasture[ny][nx] == 'S') {
                        isPosi = false;
                        break;
                    }
                }
            }
            if(!isPosi) break;
        }
        if(!isPosi) System.out.println(0);
        else {
            sb.append(1 + "\n");
            for(int i=0; i<R; i++) {
                for(int j=0; j<C; j++) {
                    sb.append(pasture[i][j]);
                }
                sb.append("\n");
            }
            System.out.println(sb.toString().trim());
        }
    } 
}