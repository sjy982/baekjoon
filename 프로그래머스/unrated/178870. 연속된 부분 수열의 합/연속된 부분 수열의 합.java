class Solution {
    static int[] sum; //누적합 배열
    public int[] solution(int[] sequence, int k) {
        int[] answer = {0, sequence.length};
        sum = new int[sequence.length];
        for(int i=0; i<sequence.length; i++) {
            if(i==0) sum[i] = sequence[i];
            else sum[i] = sum[i-1] + sequence[i];
        }
        for(int i=0; i<sum.length; i++) {
            int search_value = sum[i] - k;
            if(search_value == 0) check_answer(0, i, answer);
            else if(search_value > 0) {
                int s_ind = binary_search(search_value, i-1);
                if(s_ind != -1) check_answer(s_ind + 1, i, answer);
            }
        }
        return answer;
    }
    static int binary_search(int value, int max) {
        int min = 0;
        while(min<=max) {
            int mid = (min + max) / 2;
            if(sum[mid] == value) return mid;
            if(sum[mid] < value) min = mid + 1;
            else if(sum[mid] > value) max = mid - 1;
        }
        return -1;
    }
    static void check_answer(int s, int e, int[] answer) {
        boolean change = false;
        if((e - s) < (answer[1] - answer[0])) change = true;
        else if(((e - s) == (answer[1] - answer[0])) && (s < answer[0])) change = true;
        if(change) {
            answer[0] = s;
            answer[1] = e;
        }
    }
}