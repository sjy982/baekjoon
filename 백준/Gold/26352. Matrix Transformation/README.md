# [Gold II] Matrix Transformation - 26352 

[문제 링크](https://www.acmicpc.net/problem/26352) 

### 성능 요약

메모리: 12468 KB, 시간: 80 ms

### 분류

애드 혹, 그리디 알고리즘, 수학

### 제출 일자

2024년 9월 12일 14:25:23

### 문제 설명

<p>You have an integer matrix A, with R rows and C columns. That means it has R rows with each row containing C integers. Two integers are adjacent if their container cells share an edge. For example, in the following grid</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/540f4b54-c625-4a6c-b6c2-253a1e1456d1/-/preview/" style="width: 142px; height: 117px;"></p>

<p>(0, 1), (4, 5), (1, 4), (5, 2) are adjacent but (0, 4), (2, 6), (5, 7) are not adjacent.</p>

<p>You are allowed to do only one kind of operation in the matrix. In each step you will select two adjacent cells and increase or decrease those two adjacent values by 1, i.e., both values are increased by 1 or both values are decreased by 1.</p>

<p>Given a matrix, determine whether it is possible to transform it to a zero matrix by applying the allowed operations. A zero matrix is the one where each of its entries is zero.</p>

### 입력 

 <p>The first input line contains a positive integer, n, indicating the number of matrices. Each matrix starts with a line containing R (2 ≤ R ≤ 30) and C (2 ≤ C ≤ 30) separated by a single space. Each of the next R lines contains C integers. Each of these integers is between -20 and +20 inclusive. Assume that each input matrix will have at least one non-zero value.</p>

### 출력 

 <p>For each matrix (test case), first output “Case #i:” where i is the test case number, starting with 1. Then output “YES” if you can transform it to a zero matrix or “NO” otherwise. Leave a blank line after the output for each test case. Follow the format illustrated in Sample Output.</p>

