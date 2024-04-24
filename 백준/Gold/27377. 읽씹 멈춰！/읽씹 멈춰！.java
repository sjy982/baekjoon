import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    static int tc;
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        for(int i=0; i<tc; i++) {
            long n = Long.parseLong(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            
            sb.append(answer(BigInteger.valueOf(n), BigInteger.valueOf(s), BigInteger.valueOf(t))).append("\n");
        }
        System.out.println(sb.toString().trim());
    }
    
    static BigInteger answer(BigInteger val, BigInteger s, BigInteger t) {
        BigInteger result = new BigInteger("0"); //처음에는 할 말을 무조건 타이핑 해야됨
        result = result.add(s);
        while(!val.equals(BigInteger.valueOf(1))) {
            BigInteger[] dVal = val.divideAndRemainder(BigInteger.valueOf(2));
            BigInteger difVal = val.subtract(dVal[0]);
            if(dVal[1].equals(BigInteger.valueOf(1))) {
                //val이 홀수인 경우에는 타이핑해줘야됨
                result = result.add(dVal[1].multiply(s));
                difVal = difVal.subtract(BigInteger.valueOf(1));
            }
            if(difVal.multiply(s).compareTo(t) == -1) {
                // difVal * s (타이핑 시간)이 t (복/붙 시간)보다 작다면
                result = result.add(difVal.multiply(s));
            } else {
                //복/붙 시간이 같거나 작다면
                result = result.add(t);
            }
            val = dVal[0];
        }
        return result;
    }
}