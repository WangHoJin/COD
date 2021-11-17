## **SSAFY 5기 자율 프로젝트**



### **1. Project Description**

직접 촬영한 옷장 속 옷, 혹은 캡쳐한 사진을 활용해 옷을 등록하고 코디를 만들 수 있습니다

다른 유저들과 코디를 공유하며 "옷은 많은데 입을게 없네", "어떻게 입고 나갈까?" "새로운 스타일은 없나" 문제를 해결 해줍니다.



### **2. Functions**

- 옷 등록하기

  유저가 자신의 옷장에서 + 버튼을 눌러 새 옷을 등록할 수 있습니다.

  갤러리에서 사진을 선택하면 배경을 자동 제거해 옷 이미지로 사용합니다.

  옷의 정보를 작성 할 수 있습니다. 카테고리와 색상은 옷 이미지 등록 시

  자동으로 인식되며, 유저가 수정할 수 있습니다.

- 옷으로 코디 만들기

  유저가 등록한 옷 중에서 선택하여 옷을 꾸밉니다.

  원하는 배경색을 선택하면, 코디 미리보기 구역에 반영됩니다.

  유저가 선택한 (코디에 사용한) 옷의 목록 또한 볼 수 있습니다.

  코디의 코디명, 태그, 설명을 입력할 수 있습니다.

- 코디 기록하기

  입거나 입을 옷들을 기록할 수 있습니다.

  등록해 두었던 코디에서 바로 불러오거나,

  새로운 조합을 시도한 날에는 옷장에서 옷을 조합해서 등록할 수 있습니다.

  캘린더 버튼을 클릭해서 이전 기록들을 모두 확인할 수 있습니다.

- 코디 공유하기

  유저가 팔로우한 다른 유저들이 새로 등록한 코디를 볼 수 있습니다.

  유저 아이디를 클릭하면 해당 유저 페이지로 들어가

  상세 정보(옷, 코디 등)를 확인할 수 있습니다.

  마음에 드는 코디는 하트 버튼을 눌러 좋아요를 표시할 수 있습니다.
  
  

### **3. Tech Stack**

- BE : SpringBoot/ Spring Data/ JPA
- FE: Vue.js
- DB: MySQL





### **Git convention**

- ***main branch에는 직접적으로 접근할 수 없다.\***

#### 1. Commit Message Format

- 모든 커밋 메시지는 다음과 같은 형식을 **반드시** 따르도록 한다.

```
<[BE] or [FE]> <type>: <message> (<issue number>)
```

- ex. [BE] feat: Add user login api (S05P21B205-0)

- ex. [FE] fix: Update bug to can't login using google login (S05P21B205-0)

- ex. docs: Update [README.md](http://README.md)

  

#### 2. BE / FE

|         type          |                 description                 |
| :-------------------: | :-----------------------------------------: |
|          BE           |         Backend 코드와 관련된 커밋          |
|          FE           |         Frontend 코드와 관련된 커밋         |
| X(아무것도 적지 않음) | Backend/Frontend와 관련없는 커밋 (ex. docs) |



#### 3. Type

|   type   |                         description                          |
| :------: | :----------------------------------------------------------: |
|   feat   |                 새로운 기능 구현에 대한 커밋                 |
|   fix    |             수정 사항에 대한 커밋(ex. bug, typo)             |
|   docs   |           문서 작성에 대한 커밋(ex. 주석, README)            |
|  style   | 간단한 수정 사항에 대한 커밋(ex. 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우) |
| refactor |                  코드 리펙토링에 대한 커밋                   |
|   test   |                  테스트 작성과 관련된 커밋                   |
|  chore   | 위의 상황에 해당하지 않는 커밋(ex. 빌드 업무 수정, 패키지 매니저 수정) |



#### **4. Message**

- 커밋 메시지는 명령문으로 작성한다.

- 첫 글자는 대문자가 되도록 한다.

  

#### **5. Issue number**

- 커밋과 관련된 이슈는 커밋 메시지 마지막에 **반드시** 연결하도록 한다.
- 지라에 등록된 이슈와 연동되도록 한다.