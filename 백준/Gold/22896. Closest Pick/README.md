# [Gold II] Closest Pick - 22896 

[문제 링크](https://www.acmicpc.net/problem/22896) 

### 성능 요약

메모리: 12300 KB, 시간: 80 ms

### 분류

그리디 알고리즘, 수학, 확률론, 정렬

### 제출 일자

2024년 9월 19일 18:28:41

### 문제 설명

<p>You are entering a raffle for a lifetime supply of pancakes. N tickets have already been sold. Each ticket contains a single integer between 1 and K, inclusive. Different tickets are allowed to contain the same integer. You know exactly which numbers are on all of the tickets already sold and would like to maximize your odds of winning by purchasing two tickets (possibly with the same integer on them). You are allowed to choose which integers between 1 and K, inclusive, are on the two tickets.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/16bff1a3-2ea5-437c-a489-93e882b5958f/-/preview/" style="width: 300px; height: 84px;"></p>

<p>You know you are the last customer, so after you purchase your tickets, no more tickets will be purchased. Then, an integer c between 1 and K, inclusive, is chosen uniformly at random. If one of your tickets is strictly closer to c than all other tickets or if both of your tickets are the same distance to c and strictly closer than all other tickets, then you win the raffle. Otherwise, you do not win the raffle.</p>

<p>Given the integers on the N tickets purchased so far, what is the maximum probability of winning the raffle you can achieve by choosing the integers on your two tickets optimally?</p>

### 입력 

 <p>The first line of the input gives the number of test cases, T. T test cases follow. Each test case consists of two lines. The first line of a test case contains two integers N and K: the number of tickets already sold and the limit of the range of integers to pick from, respectively. The second line contains N integers P<sub>1</sub>, P<sub>2</sub>, …, P<sub>N</sub>, representing the integers on the tickets that have already been purchased.</p>

### 출력 

 <p>For each test case, output one line containing <code>Case #x: y</code>, where <code>x</code> is the test case number (starting from 1) and <code>y</code> is the maximum win probability you can achieve if you choose your tickets optimally.</p>

<p><code>y</code> will be considered correct if it is within an absolute or relative error of 10<sup>−6</sup> of the correct answer.</p>

