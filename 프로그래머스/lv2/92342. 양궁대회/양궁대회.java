import java.util.*;
class Solution {
    static int[] result = new int[11];
    static int[] answer;
    static int answer_dif = -1;
    public int[] solution(int n, int[] info) {
        DFS(0, 0, n, info);
        if (answer_dif == -1) {
            answer = new int[1];
            answer[0] = -1;
        }
        return answer;
    }
    static void DFS(int sum, int depth, int n, int[] info) {
        if (sum == n) {
            int dif = find_dif(info);
            if (answer_dif < dif) {
                answer_dif = dif;
                answer = result.clone();
            } else if (answer_dif != -1 && answer_dif == dif) {
                for (int i = 10; i >= 0; i--) {
                    if (result[i] > answer[i]) {
                        answer = result.clone();
                        break;
                    } else if (result[i] < answer[i]) break;
                }
            }
            return;
        }
        if (depth == 11 || sum > n) return;
        for (int i = 0; i <= n; i++) {
            result[depth] = i;
            DFS(sum + i, depth + 1, n, info);
            result[depth] = 0;
        }
    }
    static int find_dif(int[] info) {
        int rion = 0, apeach = 0;
        for (int i = 0; i < info.length - 1; i++) {
            if (result[i] != 0 || info[i] != 0) {
                if (result[i] > info[i]) rion += (10 - i);
                else apeach += (10 - i);
            }
        }
        if (rion <= apeach) return -1;
        return rion - apeach;
    }
}