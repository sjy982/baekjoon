class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        int GCD_A = arrayA[0];
        int GCD_B = arrayB[0];
        for(int i=1; i<arrayA.length; i++) GCD_A = GCD(GCD_A, arrayA[i]);
        for(int i=1; i<arrayB.length; i++) GCD_B = GCD(GCD_B, arrayB[i]);
        answer = Math.max(check(arrayA, GCD_B), check(arrayB, GCD_A));
        return answer;
    }
    static int GCD(int x, int y) {
        if(y == 0) return x;
        else return GCD(y, x % y);
    }
    static int check(int[] array, int GCD) {
        for(int i=0 ;i<array.length; i++) {
            if(array[i] % GCD == 0) return 0;
        }
        return GCD;
    }
}