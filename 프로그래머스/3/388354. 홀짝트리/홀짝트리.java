import java.io.*;
import java.util.*;

class Solution {
    static ArrayList < Integer > result;
    public int[] solution(int[] nodes, int[][] edges) {
        //인접리스트로 변환
        ArrayList < Integer > [] graph = new ArrayList[1000001];
        for (int i = 0; i < nodes.length; i++) {
            graph[nodes[i]] = new ArrayList < > ();
        }

        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            graph[a].add(b);
            graph[b].add(a);
        }

        //트리 그룹
        ArrayList < ArrayList < Integer >> treeGroup = new ArrayList < > ();
        boolean[] visited = new boolean[1000001];

        //dfs 탐색
        for (int i = 0; i < nodes.length; i++) {
            if (!visited[nodes[i]]) {
                //방문하지 않았다면.
                result = new ArrayList < > ();
                dfs(nodes[i], graph, visited);
                //result에 그룹 노드가 있음.
                treeGroup.add(result);
            }
        }

        int[] answer = new int[2]; //0은 홀짝 트리, 1은 역홀짝 트리
        boolean[] nodeState = new boolean[1000001]; //false -> 홀짝 트리, true -> 역홀짝 트리.
        //각 트리를 탐색한다.
        for (int i = 0; i < treeGroup.size(); i++) {
            ArrayList < Integer > nodeList = treeGroup.get(i);
            int[] nodeType = new int[2]; //0은 홀짝 노드, 2는 역홀짝 노드
            for (int j = 0; j < nodeList.size(); j++) {
                int rootNode = nodeList.get(j);
                if (j == 0) {
                    //0인 경우 세팅.
                    setting(graph, nodeList, nodeType, nodeState);
                } else {
                    //루트 노드를 체인지
                    int beforeRootNode = nodeList.get(j - 1);
                    int curRootNode = nodeList.get(j);

                    changeRootNode(beforeRootNode, nodeType, nodeState);
                    changeRootNode(curRootNode, nodeType, nodeState);
                }

                //여기서 홀짝 트리인지 역홀짝 트리인지 체크
                if (nodeType[0] != 0 && nodeType[1] == 0) {
                    //홀짝 트리죠.
                    answer[0] += 1;
                } else if (nodeType[1] != 0 && nodeType[0] == 0) {
                    //역홀짝 트리
                    answer[1] += 1;
                }
            }
        }

        return answer;
    }

    static void changeRootNode(int node, int[] nodeType, boolean[] nodeState) {
        if (!nodeState[node]) {
            //홀짝 노드였다면 -> 역홀짝 노드로
            nodeType[0] -= 1;
            nodeType[1] += 1;
        } else {
            //역홀짝 노드였다면 -> 홀짝 노드로
            nodeType[1] -= 1;
            nodeType[0] += 1;
        }
        nodeState[node] = !nodeState[node];
    }

    static void dfs(int curNode, ArrayList < Integer > [] graph, boolean[] visited) {
        //curNode는 방문하지 않은 노드임.
        visited[curNode] = true;
        result.add(curNode);

        for (int i = 0; i < graph[curNode].size(); i++) {
            int nextNode = graph[curNode].get(i);
            if (!visited[nextNode]) {
                //방문하지 않았다면.
                dfs(nextNode, graph, visited);
            }
        }
    }

    static void setting(ArrayList<Integer>[] graph, ArrayList < Integer > nodeList, int[] nodeType, boolean[] nodeState) {
        int rootNode = nodeList.get(0);
        for (int k = 0; k < nodeList.size(); k++) {
            int node = nodeList.get(k);
            int cntChild = graph[node].size(); //간선 개수.
            if (node != rootNode) {
                //rootNode가 아니라면 -1씩.
                cntChild -= 1;
            }
            if (node % 2 == 0) {
                //짝수인 경우.
                if (cntChild % 2 == 0) {
                    //짝수라면 홀짝노드
                    nodeType[0] += 1;
                } else {
                    //홀수라면 역홀짝노드
                    nodeState[node] = true;
                    nodeType[1] += 1;
                }
            } else {
                //홀수인 경우
                if (cntChild % 2 == 0) {
                    //짝수라면 역홀작
                    nodeState[node] = true;
                    nodeType[1] += 1;
                } else {
                    //홀수라면 홀짝노드
                    nodeType[0] += 1;
                }
            }
        }
    }
}