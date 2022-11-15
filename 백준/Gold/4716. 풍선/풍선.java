import java.io.*;
import java.util.*;

class TeamInfo implements Comparable < TeamInfo > {
    int bc,
    a,
    b,
    dif;
    char pb; //우선순위 풍선
    public TeamInfo(int bc, int a, int b, int dif, char pb) {
        this.bc = bc;
        this.a = a;
        this.b = b;
        this.dif = dif;
        this.pb = pb;
    }

    public int compareTo(TeamInfo t) {
        int df = t.dif - this.dif;
        if (df > 0) return 1;
        if (df < 0) return -1;
        return 0;
    }
}

public class Main {
    static int N, A, B,ans;
    static TeamInfo team[];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            ans = 0;
            team = new TeamInfo[N];
            if(N+A+B == 0) {
                break;
            }
            for (int i = 0; i < N; i++) {
                StringTokenizer sti = new StringTokenizer(br.readLine());
                int tbc = Integer.parseInt(sti.nextToken());
                int ta = Integer.parseInt(sti.nextToken());
                int tb = Integer.parseInt(sti.nextToken());
                if (ta - tb >= 0) {
                    team[i] = new TeamInfo(tbc, ta, tb, ta - tb, 'B');
                } else {
                    team[i] = new TeamInfo(tbc, ta, tb, tb - ta, 'A');
                }
            }
            
            Arrays.sort(team);
            for (int i = 0; i < N; i++) {
                if (team[i].pb == 'B') {
                    if (B - team[i].bc >= 0) {
                        ans += team[i].b * team[i].bc;
                        B = B - team[i].bc;
                    } else {
                        int leftA = team[i].bc - B;
                        ans += B * team[i].b + leftA * team[i].a;
                        A = A - leftA;
                        B = 0;
                    }
                } else {
                    if (A - team[i].bc >= 0) {
                        ans += team[i].a * team[i].bc;
                        A = A - team[i].bc;
                    } else {
                        int leftB = team[i].bc - A;
                        ans += A * team[i].a + leftB * team[i].b;
                        B = B - leftB;
                        A = 0;
                    }
                }
            }
            System.out.println(ans);
        }
    }
}