import java.util.*;
class Solution {
    static ArrayList<Integer> S_x =new ArrayList<>();
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        Arrays.sort(data, (a,b) -> {
            int dif = a[col-1] - b[col-1];
            if(dif>0) return 1;
            else if(dif<0) return -1;
            else if(dif==0) {
                int dif2 = a[0] - b[0];
                if(dif2>0) return -1;
                else if(dif2<0) return 1;
            }
            return 0;
        });
        for(int i=row_begin-1; i<row_end; i++) {
            int sum = 0;
            for(int j=0; j<data[i].length; j++) {
                sum += data[i][j] % (i+1);
            }
            S_x.add(sum);
        }
        answer = S_x.get(0);
        for(int i=1; i<S_x.size(); i++) answer = answer ^ S_x.get(i);
        return answer;
    }
}