# 프로젝트 요구사항

## Customer

### 명세서


고객 속성
- 고객 이름
- 고객 아이디
- 고객 스마트스토어를 이용시간
- 고객 스마트스토어에서 구매한 총 결제금액서
- Group 속성 추가

### 요구사항 

- Customers - Refresh() 메서드 구현  
- Customers - Summary sort 메서드 구현
- Customers - 정렬 로직 구현

### 해결책 및 코드 로직

1. 속성 만들기
2. Getter, Setter 구현

## Group

### 요구 사항

- Groups - find() 구현

### 명세서

- 분류 기준 : 시간 - 금액
  - General
  - VIP
  - VVIP  
  - NONE

### 해결책 및 코드 로직


1. Parameter (minTime, minPay)

## Menu

### 요구 사항

분류기준
- 고객의 분류기준을 입력할 수 있다.
- 고객의 분류기준을 설정할 수 있다.
- 고객의 분류기준을 수정할 수 있다.
  

고객정보
- 고객의 정보를 입력할 수 있다.
- 고객의 정보를 추가할 수 있다.
- 고객의 정보를 삭제할 수 있다.


고객 분류기능
- 분류기준에 의해 고객을 분류할 수 있다.
- 분류기준에 의해 분류된 고객의 정보를 출력할 수 있다.
- 분류기준에 의해 분류된 고객의 정보를 이름순으로 정렬할 수 있다.
- 분류기준에 의해 분류된 고객의 정보를 총 이용시간 순으로 정렬할 수 있다.
- 분류기준에 의해 분류된 고객의 정보를 총 결제금액 순으로 정렬할 수 있다.

### 해결책 및 코드 로직

1. GroupMenu
   - Set Parameter
   - View Parameter
   - Update Parameter

2. CustomerMenu
   - Add Customer Data
   - View Customer Data
   - Update Customer Data
   - Delete Customer Data

3. SummaryMenu
    - Summary view 
    - By Name Sort
    - By Time Sort
    - By Pay Sort


## TODO - Refactoring

- [ ] Summary sort 반복적인 코드 일관성 있게 리펙토링 필요
- [ ] TEST코드 구현 필요
- [ ] 반복되는 코드 객체지향적으로 풀이 필요
