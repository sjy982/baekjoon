class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i=0; i<numbers.length; i++) {
            String binary = Long.toBinaryString(numbers[i]);
            int maxLen = findMaxLen(binary.length());
            binary = "0".repeat(maxLen - binary.length()) + binary;
            if(verify(binary, '1')) {
                answer[i] = 1;
            }
        }
        return answer;
    }
    
    static int findMaxLen(int len) {
        int child = 1;
        int cnt = 1;
        while(true) {
            if(len <= cnt) {
                break;
            }
            cnt += (child * 2);
            child *= 2;
        }
        return cnt;
    }
    
    static boolean verify(String binary, char parent) {
        int nodeInd = binary.length() / 2;
        if(parent == '0' && (binary.charAt(nodeInd) == '1')) {
            return false;
        }
        if(binary.length() == 1) {
            return true;
        }
        
        //자식 검사
        if(!verify(binary.substring(0, nodeInd), binary.charAt(nodeInd))) {
            return false;
        }
        if(!verify(binary.substring(nodeInd + 1, binary.length()), binary.charAt(nodeInd))) {
            return false;
        }
        return true;
    }
}