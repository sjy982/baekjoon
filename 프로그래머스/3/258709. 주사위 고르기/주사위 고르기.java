import java.io.*;
import java.util.*;

class Solution {
    static int N;
    static ArrayList<Integer> result = new ArrayList<>();
    static int[] answer;
    static int maxRate = -1;
    public int[] solution(int[][] dice) {
        N = dice.length;
        answer = new int[N/2];
        dfs(0, dice);
        return answer;
    }
    
    static int findAWinningRate(ArrayList<Integer> aList, ArrayList<Integer> bList, int[][] dice) {
        int[] memo = new int[100 * (N/2) + 1];
        for(int i=1; i<memo.length; i++) {
            memo[i] = -1;
        }
        return startGame(aList, bList, dice, 0, 0, memo);
    }
    
    static int startGame(ArrayList<Integer> aList, ArrayList<Integer> bList, int[][] dice, int aScore, int depth, int[] memo) {
        if(depth == N/2) {
            //주사위마다 하나의 수를 다 골랐다면.
            if(memo[aScore] != -1) {
                //이미 한 번 구했다면.
                return memo[aScore];
            }
            
            int win = findWinCompareB(aScore, 0, bList, dice, 0);
            memo[aScore] = win;
            return win;
        }
        int sum = 0;
        
        int aDiceInd = aList.get(depth);
        for(int i=0; i<=5; i++) {
            sum += startGame(aList, bList, dice, aScore + dice[aDiceInd][i], depth + 1, memo);
        }
        
        return sum;
    }
    
    static int findWinCompareB(int aScore, int bScore, ArrayList<Integer> bList, int[][] dice, int depth) {
        if(depth == N/2) {
            if(aScore > bScore) {
                return 1;
            }
            return 0;
        }
        
        int sum = 0;
        int bDiceInd = bList.get(depth);
        for(int i=0; i<=5; i++) {
            sum += findWinCompareB(aScore, bScore + dice[bDiceInd][i], bList, dice, depth + 1);
        }
        return sum;
    }
    
    static void dfs(int start, int[][] dice) {
        if(result.size() == N/2) {
            //result에는 A가 고를 주사위가 담겨져 있음.
            ArrayList<Integer> bList = new ArrayList<>();
            fillBList(bList, result);
            int winRate = findAWinningRate(result, bList, dice);
            if(maxRate < winRate) {
                maxRate = winRate;
                for(int i=0; i<result.size(); i++) {
                    answer[i] = result.get(i) + 1;
                }
            }
            return;
        }
        
        for(int i=start; i<=N-1; i++) {
            result.add(i);
            dfs(i + 1, dice);
            result.remove(result.size() - 1);
        }
    }
    
    static void fillBList(ArrayList<Integer> bList, ArrayList<Integer> aList) {
        boolean[] visited = new boolean[N];
            for(int i=0; i<aList.size(); i++) {
                visited[aList.get(i)] = true;
            }
            for(int i=0; i<N; i++) {
                if(!visited[i]) {
                    bList.add(i);
                }
            }
    }
}