# output 함수 및 클래스
    init을 사용하여 기본 출력문 관리.
    

# input 함수 및 클래스
    Console.read사용
    사용자의 입력값을 viewmodel로 전달하여 잘못된 입력인지 제대로 된 입력인지 판단하기
# viewmodel 패키지
    역할: 아웃풋과 인풋을 이어주는 역할로 저번주에는 valid값에 대한 부붑을 주로 검사
## 할인 종류 정리
    이벤트의 적용 여부 확인 시 사용 가능 할 것-ex) 25일 넘었는가? 아니면 d-day이벤트 몇원 할인인가
    평일 할인: 디저트 메뉴 1개당 할인./ 주말 하인: 메인 메뉴 1개당 할인
    별이 달려있는 날->총 주문 금액에서 1,000할인(다른 할인과 중복 가능)
    증정 이벤트: 할인전 총금액이 12만원 이상인 경우 샴페인 1개 증정
    샴페인 증정 시 할인 가격에 샴페인 가격 더해서 출력해야
    크리스마스 디데이 이벤트 제외 나머지는 1일부터 말일까지 적용 가능
## 배지
    5천원 이상:별
    1만원 이상:트리
    2만원 이상:산타
## 이벤트 제외 조건
    음료만 주문시
    총 주문액이 1만원 미만일 경우
    메뉴는 한번에 20개까지 주문 가능(종류x,개수 o)
## 에러 발생 조건
    ⬛메뉴판에 없는 입력->"[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
    ⬛메뉴의 개수가 1개 미만 또는 한글 등의 입력이 주어질 경우->"[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.
    ⬛메뉴의 형식이 예시와 다른 경우: '메뉴명-개수'의 양식
    ->"[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
    ⬛중복된 메뉴 입력->"[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
## 에러 발생 시
    사용자의 입력이 잘못된 경우 에러 메시지 출력 이후 다시 입력받기.
