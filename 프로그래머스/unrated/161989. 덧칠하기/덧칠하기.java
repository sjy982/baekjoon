class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int paint_range = 0;
        for(int i=0; i<section.length; i++) {
            if(section[i] > paint_range) {
                answer += 1;
                paint_range = section[i] + m - 1;
                if(paint_range >= n) break;
            }
        }
        return answer;
    }
}