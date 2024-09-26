# [Gold II] Nizin - 13719 

[문제 링크](https://www.acmicpc.net/problem/13719) 

### 성능 요약

메모리: 89576 KB, 시간: 304 ms

### 분류

애드 혹, 그리디 알고리즘, 두 포인터

### 제출 일자

2024년 9월 26일 15:48:52

### 문제 설명

<p>Do Geese See God? Or, Was It A Rat I Saw? Nevermind the geese or rats, this is just an unnecessary introduction to showcase Mislav’s love of palindromes. Help him solve the following task!</p>

<p>Let A be an array of N integers. We say that A is palindromic if for each i it holds A[i] = A[N-i+1], where A[i] ​represents the ith element of array A, and the index of the first element in the array is 1.</p>

<p>Mislav can modify the array in the following way: in a single move, he chooses two adjacent elements of that array and replaces them with their sum. Notice that the number of elements in the array is going to decrease by 1 after each move. Mislav wants to know what is the least number of moves he must make in order for the original array to become palindromic. </p>

### 입력 

 <p>The first line of input contains the integer N (1 ≤ N ≤ 10<sup>6</sup>) that represents the number of elements in the array.<br>
The following line contains N space-separated positive integers that represent the elements in Mislav’s array. The numbers in the input will be at most 10<sup>9</sup>. </p>

### 출력 

 <p>Output the minimal number of moves it takes to transform the original array to a palindromic one, given the rules from the task. </p>

