# [Gold I] 문자열 거리 - 1230 

[문제 링크](https://www.acmicpc.net/problem/1230) 

### 성능 요약

메모리: 16048 KB, 시간: 96 ms

### 분류

다이나믹 프로그래밍, 문자열

### 제출 일자

2025년 1월 13일 17:37:24

### 문제 설명

<p>문자열 O에서 문자열 N까지 문자열 거리는 O를 N과 같게 만들기 위해 필요한 문자열 삽입의 최솟값이다. 문자열 삽입은 O의 어느 위치에서건 가능하다. 예를 들어, O가 “gosrl"일 때, ”sip gi"을 r이전에 삽입한다면 "gossip girl“이 된다.</p>

<p>문자열 O와 문자열 N이 주어질 때, 두 문자열의 거리를 출력하는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에 문자열 O, 둘째 줄에 문자열 N이 주어진다. 문자열의 길이는 최대 1,000이다. 문자열은 아스키 코드의 값이 32보다 크거나 같고, 126보다 작거나 같은 문자로만 이루어져 있다.</p>

### 출력 

 <p>첫째 줄에 문자열 O와 문자열 N의 문자열 거리를 출력한다. 만약 O를 N으로 만들 수 없다면 -1을 출력한다.</p>

