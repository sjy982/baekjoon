import java.io.*;
import java.util.*;

class GraphInfo {
    String s;
    int bInd;
    GraphInfo(String s, int bInd) {
        this.s = s;
        this.bInd = bInd;
    }
}

class TwoD {
    int x, y;
    TwoD(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static final int[] dx = {
        -1,
        0,
        1,
        0
    };
    static final int[] dy = {
        0,
        -1,
        0,
        1
    };
    static final String target = "123456780";
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int inputBInd = -1;
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                String str = st.nextToken();
                if (str.equals("0")) {
                    inputBInd = convertFrom2DTo1D(j, i);
                }
                sb.append(str);
            }
        }
        
        GraphInfo input = new GraphInfo(sb.toString(), inputBInd);
        System.out.println(bfs(input));
    }

    static int bfs(GraphInfo input) {
        Queue < GraphInfo > que = new LinkedList < > ();
        HashMap < String, Boolean > visited = new HashMap < > ();
        visited.put(input.s, true);
        que.add(input);

        int cnt = 0;
        while (!que.isEmpty()) {
            int sz = que.size();

            for (int t = 0; t < sz; t++) {
                GraphInfo node = que.poll();

                if (node.s.equals(target)) {
                    //같다면
                    return cnt;
                }

                TwoD td = convertFrom1DTo2D(node.bInd);
                for (int i = 0; i < 4; i++) {
                    int nx = td.x + dx[i];
                    int ny = td.y + dy[i];

                    if ((0 <= nx && nx <= 2) && (0 <= ny && ny <= 2)) {
                        char[] charArr = node.s.toCharArray();
                        int cp = convertFrom2DTo1D(nx, ny); //바꿀 위치
                        
                        char temp = charArr[cp];
                        charArr[cp] = '0';
                        charArr[node.bInd] = temp;
                        
                        String newStr = new String(charArr);
                        if(visited.get(newStr) == null) {
                            //방문하지 않았다면
                            que.add(new GraphInfo(newStr, cp));
                            visited.put(newStr, true);
                        }
                    }
                }
            }
            cnt += 1;
        }

        return -1;
    }

    static int convertFrom2DTo1D(int x, int y) {
        return y * 3 + x;
    }

    static TwoD convertFrom1DTo2D(int ind) {
        return new TwoD(ind % 3, ind / 3);
    }
}