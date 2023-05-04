import java.util.*;
class Solution {
    static ArrayList<Integer> graph = new ArrayList<>();
    static int end_x;
    static double[][] area;
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];
        graph.add(k);
        while(k != 1) {
            if(k % 2 == 0) k = k/2;
            else if(k % 2 == 1) k = k * 3 + 1;
            graph.add(k);
        }
        area = new double[graph.size()][graph.size()];
        end_x = graph.size() - 1;
        for(int i=0; i<graph.size()-1; i++) {
            double tri = Math.abs(graph.get(i+1) - graph.get(i)) / 2.0;
            double rec = Math.min(graph.get(i), graph.get(i+1));
            area[i][i+1] = tri + rec;
        }
        for(int i=0; i<ranges.length; i++) {
            int s = 0 + ranges[i][0];
            int e = end_x + ranges[i][1];
            if(s <= end_x && e >= 0) {
                if(s == e) answer[i] = 0.0;
                else if(s > e) answer[i] = -1.0;
                else answer[i] = find_area(s, e);
            } else answer[i] = -1.0;
        }
        return answer;
    }
    static double find_area(int s, int e) {
        double sum = 0;
        for(int i=s; i<e; i++) sum += area[i][i+1];
        return sum;
    }
}