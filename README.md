# toy_project

## Requirements list

### 4. 마이 페이지

- 본인의 정보를 조회할 수 있다 : 프사(URL), 닉네임, 등급, 전화번호, 학년, 학과
- 본인의 정보를 수정할 수 있다: 프사, 닉네임, 전화번호
- 프사 용량 제한 필요
- 내가 작성한 모든 글과 댓글을 확인 가능하다.

### 5. 어드민 페이지

#### 5.1 회장 등급

- '회장'은 회원 정보를 확인하고, 수정할 수 있다.
- '회장'은 회원을 탈퇴 시킬 수 있다.
- 자기 자신은 탈퇴가 불가능 하다.

#### 5.2 회장 + 임원진 등급

- 회원 가입 승인 대기 목록을 확인 가능하다
- 회원 가입 승인을 할 수 있다.
- '우산, 사물함 대여/반납을 처리 할 수 있다.

## Requirements Specification

### 4. 마이페이지

| No  | 기능             | 상세                             | Actor | 
|-----|----------------|--------------------------------|-------|
| 1   | 본인의 정보를 조회     | 프사(URL), 닉네임, 등급, 전화번호, 학년, 학과 | 회원    |
| 2   | 본인의 정보를 수정     | 프사, 닉네임, 전화번호                  | 회원    |
| 3   | 내가 작성한 글 조회    |                                |  회원   |
| 4   | 내가 작성한 댓글 조회   |                                | 회원    |

- 본인의 정보를 조회할 수 있다 : 프사(URL), 닉네임, 등급, 전화번호, 학년, 학과
    - `Member` 테이블의 존재
    - `Member` 테이블은 프로필사진, 닉네임, 등급, 전화번호, 학년, 학과 필드 포함
    - `Member` 중 자신의 레코드에 대한 R 기능
- 본인의 정보를 수정할 수 있다: 프사, 닉네임, 전화번호
    - `Member` 의 프로필사진, 닉네임, 전화번호 대한 U 기능
- 프사 용량 제한 필요
    - 아직 미구상
- 내가 작성한 모든 글과 댓글을 확인 가능하다.
    - `Member`와 관계를 갖는 `BOARD` 테이블의 존재
    - `Member`와 `BOARD`와 관계를 갖는 `COMMENT` 테이블의 존재
    - `BOARD`와 `COMMENT`는 R이 가능

### 5. 어드민 페이지

#### 5.1 회장 등급

| No   | 기능             | 상세                     | Actor    |
|------|----------------|------------------------|----------|
| 1    | 회원 정보 조회       |                        | 회장       |
| 2    | 회원 정보 수정       | 회장 자기 자신은 U 불가능        | 회장       |
| 3    | 회원 제명          | 회장 자기 자신은 D 불가능        | 회장       |
| 4    | 가입 승인 대기 목록 조회 | 비회원 등급의 사용자 R          | 회장, 임원진  |
| 5    | 회원 가입 승인       | 졸업 / 재학생 구분 , 가입 반려 문제 | 회장, 임원진  |
| 6    | 우산, 사물함 신청 조회  |                        | 회장, 임원진  |
| 7    | 우산, 사물함 대여 처리  |                        | 회장, 임원진  |
| 8    | 우산, 사물함 반납 처리  |                        | 회장, 임원진  |


- '회장'은 회원 정보를 확인하고, 수정할 수 있다.
    - `Member`의 등급에는 `회장`이 존재
    - `회장`등급의 사용자는 `Member`테이블의 모든 레코드에 대해 R,U 가능
      > 회장 자기 자신은 U 불가능
- '회장'은 회원을 탈퇴 시킬 수 있다.
    - `회장`등급의 사용자는 `Member`테이블의 레코드에 대해 D가능
      > 회장 자기 자신은 D 불가능
- 자기 자신은 탈퇴가 불가능 하다.

#### 5.2 회장 + 임원진 등급

- 회원 가입 승인 대기 목록을 확인 가능하다
    - `Member`의 등급에는 `임원진`이 존재
    - `Member`의 등급에는 `비회원`이 존재
    - `Member`의 등급에는 `일반`이 존재
    - `회장`등급과 `임원진`등급의 사용자는 `Member` 중 `비회원` 등급의 사용자의 등급을 R가능
- 회원 가입 승인을 할 수 있다.
    - `회장`등급과 `임원진`등급의 사용자는 `비회원`등급의 사용자의 등급을 `일반`으로 바꿀 수 있다.
        - > 비회원 등급의 승인 대기자가 졸업생인 경우는?
        - > 가입을 반려할 경우 처리는 D?
- '우산, 사물함 대여/반납을 처리 할 수 있다.
    - `UMBRELLA`테이블은 우산 번호(PK)
    - `LOCKER`테이블은 사물함 번호(PK)
    - `UMBRELLA`와 `Member`와 관계를 갖는 `APPLY_UMBRELLA`테이블 존재
    - `LOCKER`와 `Member`와 관계를 갖는 `APPLY_LOCKER`테이블 존재
    - `APPLY_UMBRELLA`는 우산번호(PK, FK), 사용자(PK, FK), 신청일자(PK), 처리 여부 필드 포함
    - `APPLY_LOCKER`는 사물함 번호(PK, FK), 사용자(PK, FK), 신청일자(PK), 처리 여부 필드 포함
    - 처리 여부 필드는 `신청`, `허가`, `거부` 상태가 존재
    - 하나의 우산(사물함)에 대해 허가는 동시에 하나만 존재
    - `회장` 또는 `임원진`등급의 사용자는 `APPLY_UMBRELLA`와 `APPLY_LOCKER`를 R할 수 있음
    - `회장` 또는 `임원진`등급의 사용자는 `APPLY_UMBRELLA`와 `APPLY_LOCKER`의 처리 여부 필드를 U할 수 있음

## DB
#### **Member**

| Member      | 자료형    | 비고  |
|-------------|--------|-----|
| id          | String | PK  |
| role        | Role   |     |
| nickName    | String |     |
| phoneNumber | String |     |
| major       | String |     |
| photo       | String |     |


| Role      | 설명   |
|-----------|------|
| PRESIDENT | 회장   |
| EXECUTIVE | 임원진  |
| GENERAL   | 부원   |
| GRADUATE  | 졸업생  |
| GUEST     | 비회원  |
- 이름, 학번은?

#### **DeletedMember**

| Deleted_Member | 자료형            | 비고  |
|----------------|----------------|-----|
| id             | String         | PK  |
| role           | Role           |     |
| nickName       | String         |     |
| phoneNumber    | String         |     |
| major          | String         |     |
| photo          | String         |     |
| expelledDate   | LocalDateTime  |     |


#### **BOARD**

| Post        | 자료형            | 비고  |
|-------------|----------------|-----|
| postId      | Long           | PK  |
| postType    | PostType       |     |
| member      | Member         | FK  |
| title       | String         |     |
| content     | String         |     |
| writtenDate | LocalDateTime  |     |

| PostType      | 설명      |
|---------------|---------|
| NOTICE        | 공지게시판   |
| FREE          | 자유게시판   |
| ACTIVITY      | 활동사진게시판 |
| PREVIOUS_TEST | 족표게시판   |
| EMPLOYMENT    | 취업정보게시판 |
| GRADUATE      | 졸업생게시판  |
| MUST_EAT      | 맛집게시판   |


#### **COMMENT**

| COMMENT     | 자료형           | 비고  |
|-------------|---------------|-----|
| commentId   | long          | PK  |
| postId      | Post          | FK  |
| Member      | Member        | FK  |
| content     | String        |     |
| writtenDate | LocalDateTime |     |


#### **UMBRELLA**
| Umbrella   | 자료형    | 비고  |
|------------|--------|-----|
| umbrellaId | String | PK  |
| rent       | Rent   |     |

| Rent         | 설명    |
|--------------|-------|
| USABLE       | 사용가능  |
| OCCUPIED     | 사용중   |
| UNAVAILABLE  | 사용불가  |


- **LOCKER**
    - 사물함 id **(PK)**


- **APPLY_UMBRELLA**
    - 신청내역 id **(PK)**
    - 우산 id (FK)
    - 신청인 id (FK)
    - 신청일
    - 처리 여부


- **APPLY_LOCKER**
    - 신청내역 id **(PK)**
    - 사물함 id (FK)
    - 신청인 id (FK)
    - 신청일
    - 처리 여부
