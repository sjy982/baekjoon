-- 코드를 입력하세요
SELECT A.YEAR, A.MONTH, COUNT(*) AS PUCHASED_USERS, ROUND(COUNT(*)/(SELECT COUNT(*) FROM USER_INFO WHERE YEAR(JOINED) = 2021), 1) AS PUCHASED_RATIO
FROM (SELECT DISTINCT YEAR(SALES_DATE) AS YEAR, MONTH(SALES_DATE) AS MONTH, USER_ID
      FROM ONLINE_SALE) AS A JOIN (SELECT USER_ID
                                   FROM USER_INFO 
                                   WHERE YEAR(JOINED) = 2021) AS B
ON A.USER_ID = B.USER_ID
GROUP BY A.YEAR, A.MONTH
ORDER BY A.YEAR, A.MONTH