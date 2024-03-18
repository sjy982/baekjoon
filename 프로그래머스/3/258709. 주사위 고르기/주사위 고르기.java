import java.io.*;
import java.util.*;

class Node {
    int w;
    ArrayList<Integer> list;
    Node(int w, ArrayList<Integer> list) {
        this.w = w;
        this.list = list;
    }
}

class Solution {
    static int N;
    static ArrayList<Integer> aList = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> combiScore = new ArrayList<>();
    static Node max = new Node(-1, new ArrayList<>());
    public int[] solution(int[][] dice) {
        N = dice.length;
        scoreDfs();
        diceDfs(0, dice);
        int[] answer = new int[N/2];
        Collections.sort(max.list);
        for(int i=0; i<max.list.size(); i++) {
            answer[i] = max.list.get(i) + 1;
        }
        return answer;
    }
    
    static void diceDfs(int ind, int[][] dice) {
        //주사위를 N/2개 고른다. index
        if(aList.size() == N/2) {
            //고른 주사위에서 나올 수 있는 모든 경우의 수를 구한다.
            ArrayList<Integer> aScore = calScore(aList, dice);
            ArrayList<Integer> bScore = calScore(leftList(aList), dice);
            Collections.sort(aScore);
            Collections.sort(bScore);
            int win = 0;
            for(int i=0; i<aScore.size(); i++) {
                win += binarySearch(aScore.get(i), bScore);
            }
            if(win > max.w) {
                ArrayList<Integer> updateList = new ArrayList<>();
                for(int i=0; i<aList.size(); i++) {
                    updateList.add(aList.get(i));
                }
                max = new Node(win, updateList);
            } 
            return;
        }
        for(int i=ind; i<N; i++) {
            aList.add(i);
            diceDfs(i+1, dice);
            aList.remove(aList.size() - 1);
        }
    }
    
    static int binarySearch(int v, ArrayList<Integer> bScore) {
        int minInd = 0;
        int maxInd = bScore.size() - 1;
        while(minInd <= maxInd) {
            int midInd = (minInd + maxInd) / 2;
            if(v <= bScore.get(midInd)) {
                maxInd = midInd - 1;
            } else {
                minInd = midInd + 1;
            }
        }
        return maxInd + 1;
    }
    
    static void scoreDfs() {
        if(aList.size() == N/2) {
            combiScore.add(new ArrayList<>());
            for(int i=0; i<aList.size(); i++) {
                combiScore.get(combiScore.size() - 1).add(aList.get(i));
            }
            return;
        }
        for(int i=0; i<6; i++) {
            aList.add(i);
            scoreDfs();
            aList.remove(aList.size() - 1);
        }
    }
    
    static ArrayList<Integer> calScore(ArrayList<Integer> list, int[][] dice) {
        ArrayList<Integer> result = new ArrayList<>();
        for(int i=0; i<combiScore.size(); i++) {
            int score = 0;
            for(int j=0; j<combiScore.get(i).size(); j++) {
                score += dice[list.get(j)][combiScore.get(i).get(j)];
            }
            result.add(score);
        }
        return result;
    }
    
    static ArrayList<Integer> leftList(ArrayList<Integer> a) {
        ArrayList<Integer> result = new ArrayList<>();
        for(int i=0; i<N; i++) {
            if(!checkValue(a, i)) {
                result.add(i);
            }
        }
        return result;
    }
    
    static boolean checkValue(ArrayList<Integer> a, int v) {
        for(int i=0; i<a.size(); i++) {
            if(a.get(i) == v) {
                return true;
            }
        }
        return false;
    }
}