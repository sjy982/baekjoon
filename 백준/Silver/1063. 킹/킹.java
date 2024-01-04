import java.io.*;
import java.util.*;

class Node {
    int x, y;
    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static Node king;
    static Node rock;
    static int N;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        king = convertStrToNode(st.nextToken());
        rock = convertStrToNode(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        for(int i=0; i<N; i++) {
            Node move = covertCommandStrToNode(br.readLine());
            Node rbKing = new Node(king.x, king.y);
            Node rbRock = new Node(rock.x, rock.y);
            
            king = new Node(king.x + move.x, king.y + move.y);
            if(king.x == rock.x && king.y == rock.y) {
                rock = new Node(rock.x + move.x, rock.y + move.y);
            }
            if(!checkRange(king.x, king.y) || !checkRange(rock.x, rock.y)) {
                king = new Node(rbKing.x, rbKing.y);
                rock = new Node(rbRock.x, rbRock.y);
            }
        }
        System.out.println(convertNodeToStr(king));
        System.out.println(convertNodeToStr(rock));
    }
    
    static boolean checkRange(int x, int y) {
        if((1 <= x && x <= 8) && (1 <= y && y <= 8)) {
            return true;
        }
        return false;
    }
    
    static Node convertStrToNode(String str) {
        Node result = new Node(-1, -1);
        switch(str.charAt(0)) {
            case 'A':
                result = new Node(1, str.charAt(1) - '0');
                break;
            case 'B':
                result = new Node(2, str.charAt(1) - '0');
                break;
            case 'C':
                result = new Node(3, str.charAt(1) - '0');
                break;
            case 'D':
                result = new Node(4, str.charAt(1) - '0');
                break;
            case 'E':
                result = new Node(5, str.charAt(1) - '0');
                break;
            case 'F':
                result = new Node(6, str.charAt(1) - '0');
                break;
            case 'G':
                result = new Node(7, str.charAt(1) - '0');
                break;
            case 'H':
                result = new Node(8, str.charAt(1) - '0');
                break;
        }
        return result;
    }
    
    static String convertNodeToStr(Node node) {
        StringBuilder sb = new StringBuilder();
        switch(node.x) {
            case 1:
                sb.append('A').append(node.y);
                break;
            case 2:
                sb.append('B').append(node.y);
                break;
            case 3:
                sb.append('C').append(node.y);
                break;
            case 4:
                sb.append('D').append(node.y);
                break;
            case 5:
               sb.append('E').append(node.y);
                break;
            case 6:
                sb.append('F').append(node.y);
               break;
            case 7:
                sb.append('G').append(node.y);
                break;
            case 8:
                sb.append('H').append(node.y);
                break;
        }
        return sb.toString();
    }
    
    static Node covertCommandStrToNode(String str) {
        Node result = new Node(-1, -1);
        switch(str) {
            case "R":
                result = new Node(1, 0);
                break;
            case "L":
                result = new Node(-1, 0);
                break;
            case "B":
                result = new Node(0, -1);
                break;
            case "T":
                result = new Node(0, 1);
                break;
            case "RT":
                result = new Node(1, 1);
                break;
            case "LT":
                result = new Node(-1, 1);
                break;
            case "RB":
                result = new Node(1, -1);
                break;
            case "LB":
                result = new Node(-1, -1);
                break;
        }
        return result;
    }
}