import java.io.*;
import java.util.*;

public class Main {
    static int[][] record = new int[6][3];
    static ArrayList < Integer > [][] perResult = new ArrayList[6][3];
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 18; j++) {
                record[j / 3][j % 3] = Integer.parseInt(st.nextToken());
            }
            if (isPosible()) {
                initPerResult();
                if (dfs(0, 0, 0, new ArrayList < > (Arrays.asList(1, 2, 3, 4, 5)))) {
                    sb.append(1);
                } else {
                    sb.append(0);
                }
            } else {
                sb.append(0);
            }
            sb.append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    static boolean isPosible() {
        for (int i = 0; i < 6; i++) {
            int sum = 0;
            for (int j = 0; j < 3; j++) {
                sum += record[i][j];
            }
            if (sum != 5) {
                return false;
            }
        }
        return true;
    }

    static void initPerResult() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                perResult[i][j] = new ArrayList < > ();
            }
        }
    }

    static boolean dfs(int country, int ind, int pc, ArrayList < Integer > list) {
        if (perResult[country][pc].size() == record[country][pc]) {
            if (pc == 2) {
                if (country == 5) {
                    return true;
                }
                ArrayList < Integer > nextCountryList = new ArrayList < > ();
                for (int i = country + 2; i < 6; i++) {
                    nextCountryList.add(i);
                }
                if (dfs(country + 1, 0, 0, nextCountryList)) {
                    return true;
                }
            } else {
                ArrayList < Integer > leftList = new ArrayList < > ();
                for (Integer item: list) {
                    if (!perResult[country][pc].contains(item)) {
                        leftList.add(item);
                    }
                }
                if (dfs(country, 0, pc + 1, leftList)) {
                    return true;
                }
            }
        } else {
            for (int i = ind; i < list.size(); i++) {
                if (commit(list.get(i), pc)) {
                    perResult[country][pc].add(list.get(i));
                    if (dfs(country, i + 1, pc, list)) {
                        return true;
                    }
                    rollback(list.get(i), pc);
                    perResult[country][pc].remove(perResult[country][pc].size() - 1);
                }
            }
        }
        return false;
    }

    static boolean commit(int country, int pc) {
        int ind = findInd(pc);
        int result = record[country][ind] - 1;
        if (result < 0) {
            return false;
        }
        record[country][ind] = result;
        return true;
    }

    static void rollback(int country, int pc) {
        int ind = findInd(pc);
        record[country][ind] += 1;
    }

    static int findInd(int pc) {
        if (pc == 0) {
            return 2;
        } else if (pc == 1) {
            return 1;
        }
        return 0;
    }
}