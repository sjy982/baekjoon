# [Gold II] The Strange Sequence - 7111 

[문제 링크](https://www.acmicpc.net/problem/7111) 

### 성능 요약

메모리: 16076 KB, 시간: 128 ms

### 분류

그리디 알고리즘, 수학

### 제출 일자

2024년 9월 9일 15:08:19

### 문제 설명

<p>There is a sequence of positive integers {a<sub>i</sub>}. For each i (i>1) a<sub>i</sub> is the least possible integer with the following features:</p>

<ol>
	<li>a<sub>i</sub> > a<sub>i-1</sub>,</li>
	<li>the sum of a<sub>i</sub> digits equals the sum of 4 * a<sub>i-1</sub> digits.</li>
</ol>

<p>For the given values of the first sequence member a<sub>1</sub> and the index n, you must find and output the value of the a<sub>n</sub>.</p>

### 입력 

 <p>The values of integers a<sub>1</sub> (0 < a<sub>1</sub> < 20) and n (0 < n < 10000) are input from the keyboard.</p>

### 출력 

 <p>You must write one integer on the screen - the value of the a<sub>n</sub>. For the testing, only data where the corresponding a<sub>n</sub> value does not increase 10<sup>9</sup> are to be used.</p>

