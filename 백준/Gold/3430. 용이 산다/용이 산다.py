import sys
import heapq

input = sys.stdin.readline

# 테스트 케이스 수
Z = int(input().strip())

for _ in range(Z):
    n, m = map(int, input().split())
    weather = list(map(int, input().split()))
    
    # 편의상, Java 코드를 따라 1부터 m까지 인덱스를 사용하도록
    # 앞에 더미(0) 원소를 넣어준다.
    weather = [0] + weather  # weather[1] ~ weather[m] 사용
    
    # lakeDays: 각 호수에 대해 "비가 오는 날"을 역순으로 저장
    # 파이썬에서는 dict 사용. key: 호수 번호, value: list of days(역순)
    lakeDays = {}
    
    # m부터 1까지 역순 순회하며 비 오는 날을 저장
    for day in range(m, 0, -1):
        if weather[day] != 0:
            lakeId = weather[day]
            if lakeId not in lakeDays:
                lakeDays[lakeId] = []
            lakeDays[lakeId].append(day)
    
    # 우선순위 큐(힙): (날짜, 호수 번호) 형태
    pq = []
    
    # 각 호수에 대해 "가장 빠른 비 오는 날"을 하나씩 힙에 넣음
    for lakeId, days in lakeDays.items():
        if days:  # days는 이미 역순으로 들어있음
            earliest_day = days.pop()  # 가장 뒤의 원소가 "가장 빠른 날"
            heapq.heappush(pq, (earliest_day, lakeId))
    
    # dateFilled[i]: i번 호수가 "언제 다시 물로 찼는지"를 기록
    # 0이면 아직 물이 차지 않았다고 가정(문제에서 호수는 초기에 물이 참)
    # 여기서는 "호수가 다시 물이 찰 날짜"를 저장하는 식으로 활용
    dateFilled = [0] * (n + 1)
    
    ans_list = []
    isPossible = True
    
    # m일 동안 시뮬레이션
    for day in range(1, m + 1):
        if weather[day] == 0:
            # 비가 오지 않는 날(건조 가능)
            if pq:
                d, lakeId = heapq.heappop(pq)
                ans_list.append(lakeId)
                # ls.d 날짜에 물이 찰 호수를 day일에 건조 -> 비어 있음
                dateFilled[lakeId] = d
            else:
                ans_list.append(0)
        else:
            # 비가 오는 날
            lakeId = weather[day]
            # 만약 이 호수가 이미 물로 차있다면(dateFilled[lakeId] != day),
            # 즉 이전에 'lakeId' 호수를 day에 차도록 예약(건조 해제)해둔 상태가 아니라면 재앙
            if dateFilled[lakeId] != day:
                # 이미 채워져 있는데 또 비가 온 경우 -> 재앙
                isPossible = False
                break
            # 비가 와서 호수가 다시 물로 차므로, dateFilled를 0으로 리셋
            dateFilled[lakeId] = 0
            
            # 해당 호수의 다음 비 오는 날이 있다면 우선순위 큐에 삽입
            if lakeId in lakeDays and lakeDays[lakeId]:
                next_day = lakeDays[lakeId].pop()
                heapq.heappush(pq, (next_day, lakeId))
    
    if not isPossible:
        print("NO")
    else:
        print("YES")
        print(" ".join(map(str, ans_list)))
