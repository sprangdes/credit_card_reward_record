# Credit Card Reward Record API
提供有關信用卡資訊的CRUD操作，並可將信用卡和使用者綁定以提供更多的操作，如：提供適合的刷卡建議或可得到最高回饋的刷卡方式。

## 目錄
- [專案簡介](#專案簡介)
- [安裝與設定](#安裝與設定)
- [使用說明](#使用說明)
- [API路徑與方法](#API路徑與方法)
  - [User](#User)
  - [Bank](#Bank)
  - [Card](#Card)
  - [RewardWay](#RewardWay)
  - [Consumption](#Consumption)
- [錯誤處理](#錯誤處理)

## 專案簡介
信用卡回饋記錄API旨在幫助使用者管理信用卡資訊、消費回饋方式，並提供最佳的刷卡建議。主要功能包括：
- 使用者管理（新增、查詢、更新、刪除）
- 信用卡管理（新增、查詢、更新、刪除）
- 消費回饋管理（新增、查詢、更新、刪除）
- 提供刷卡建議及最高回饋的信用卡

## 安裝與設定
### 先決條件
- Java 17
- MySQL 8.0
- Maven 3.6+

### 安裝步驟
1. 克隆儲存庫：`git clone https://github.com/sprangdes/credit_card_reward_record.git`
2. 進入專案目錄：`cd credit_card_reward_record`
3. 安裝依賴項：`mvn install`

### 設定
複製範例配置檔案並進行修改：
```bash  
cp src/main/resources/application.properties.example src/main/resources/application.properties
```
application.properties文件中設置數據庫連接：
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/credit_card_reward_record
spring.datasource.username=your_username # your username
spring.datasource.password=your_password # your password
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

## 使用說明
### 啟動服務
```bash  
mvn spring-boot:run
```
http://localhost:8080/api/v1/

### API路徑與方法
#### 資料庫

##### User
```json
{
  "userId": 0,
  "userName": "string",
  "password": "string",
  "cards": [
    {
      "cardId": 0,
      "bank": {
        "bankId": 0,
        "bankName": "string",
        "cards": [
          "string"
        ]
      },
      "cardName": "string",
      "rewardWays": [
        {
          "rewardWayId": 0,
          "rewardWayName": "string",
          "rewardLimit": 0,
          "rewardRate": 0,
          "card": "string",
          "consumptions": [
            {
              "consumptionId": 0,
              "consumptionName": "string",
              "rewardWays": [
                "string"
              ]
            }
          ]
        }
      ]
    }
  ]
}
```

##### Bank
```json
{
  "bankId": 0,
  "bankName": "string",
  "cards": [
    "string"
  ]
}
```

##### Card
```json
{
  "cardId": 0,
  "bank": {
    "bankId": 0,
    "bankName": "string",
    "cards": [
      "string"
    ]
  },
  "cardName": "string",
  "rewardWays": [
    {
      "rewardWayId": 0,
      "rewardWayName": "string",
      "rewardLimit": 0,
      "rewardRate": 0,
      "card": "string",
      "consumptions": [
        {
          "consumptionId": 0,
          "consumptionName": "string",
          "rewardWays": [
            "string"
          ]
        }
      ]
    }
  ]
}
```

##### RewardWay
```json
{
  "rewardWayId": 0,
  "rewardWayName": "string",
  "rewardLimit": 0,
  "rewardRate": 0,
  "card": "string",
  "consumptions": [
    {
      "consumptionId": 0,
      "consumptionName": "string",
      "rewardWays": [
        "string"
      ]
    }
  ]
}
```

##### Consumption
```json
{
  "consumptionId": 0,
  "consumptionName": "string",
  "rewardWays": [
    "string"
  ]
}
```

#### User
`GET /api/v1/users`
- 描述：取得所有使用者資料
- 回應範例：
List<User>

`GET /api/v1/users/{userId}`
- 描述：以userId取得使用者資料
- 請求參數：
  - (Path) userId：integer
- 回應範例：
User

`GET /api/v1/users/{userId}/recommend`
- 描述：提供userId及consumptionId，回傳使用者所擁有的信用卡中，有對應消費方式回饋的信用卡列表
- 請求參數：
  - (Path) userId：integer
  - (Query) consumptionId：integer
- 回應範例：
List<Card>

`GET /api/v1/users/{userId}/recommend-consumption`
- 描述：提供userId及consumptionId，回傳使用者所擁有的信用卡中，有對應消費方式最高回饋的信用卡列表
- 請求參數：
  - (Path) userId：integer
  - (Query) consumptionId：integer
- 回應範例：
List<Card>

`POST /api/v1/users`
- 描述：有提供userId時為更新；沒有提供userId時為新增
- 請求參數：
```json
{
  "userId": 0,
  "userName": "string",
  "password": "string"
}
```
- 回應範例：
User

`PUT /api/v1/users/{userId}/record`
- 描述：提供userId、rewardWayId及price，以新增一筆消費回饋紀錄至指定使用者
- 請求參數：
  - (Path) userId：integer
  - (Query) rewardWayId：integer
  - (Query) price：integer
- 回應範例：
`Record added successfully`

`PUT /api/v1/users/{userId}/card`
- 描述：提供userId及cardIds以將多張信用卡新增至指定使用者
- 請求參數：
  - (Path) userId：integer
  - (Query) cardIds：array[integer]
- 回應範例：
User

`DELETE /api/v1/users/{userId}`
- 描述：依userId刪除一筆使用者資料
- 請求參數：
  - (Path) userId：integer
- 回應範例：
`User deleted successfully`

#### Bank
`GET /api/v1/banks`
- 描述：取得所有發卡銀行資料
- 回應範例：
  List<Bank>

`GET /api/v1/banks/{bankId}`
- 描述：以bankId取得發卡銀行資料
- 請求參數：
  - (Path) bankId：integer
- 回應範例：
  Bank

`GET /api/v1/banks/name/{bankName}`
- 描述：以bankName取得發卡銀行資料
- 請求參數：
  - (Path) bankName：string
- 回應範例：
  Card

`POST /api/v1/banks`
- 描述：有提供bankId時為更新；沒有提供bankId時為新增
- 請求參數：
```json
{
  "bankId": 0,
  "bankName": "string"
}
```
- 回應範例：
  Bank

`DELETE /api/v1/banks/{bankId}`
- 描述：依bankId刪除一筆發卡銀行資料
- 請求參數：
  - (Path) bankId：integer
- 回應範例：
  `Bank deleted successfully`

#### Card
`GET /api/v1/cards`
- 描述：取得所有信用卡資料
- 回應範例：
List<Card>

`GET /api/v1/cards/{cardId}`
- 描述：以cardId取得信用卡資料
- 請求參數：
  - (Path) cardId：integer
- 回應範例：
Card

`GET /api/v1/cards/name/{cardName}`
- 描述：以cardName取得信用卡資料
- 請求參數：
  - (Path) cardName：string
- 回應範例：
Card

`GET /api/v1/cards/bank/{bankId}`
- 描述：以bankId取得信用卡資料列表
- 請求參數：
  - (Path) bankId：integer
- 回應範例：
List<Card>

`POST /api/v1/cards`
- 描述：有提供cardId時為更新；沒有提供cardId時為新增
- 請求參數：
```json
{
  "cardId": 0,
  "bankId": 0,
  "cardName": "string"
}
```
- 回應範例：
Card

`PUT /api/v1/cards/{cardId}/reward-way`
- 描述：提供cardId及rewardWayIds以將多個回饋方式新增至指定信用卡
- 請求參數：
  - (Path) cardId：integer
  - (Query) rewardWayIds：array[integer]
- 回應範例：
Card

`DELETE /api/v1/cards/{cardId}`
- 描述：依cardId刪除一筆信用卡資料
- 請求參數：
  - (Path) cardId：integer
- 回應範例：
`Card deleted successfully`

`DELETE /api/v1/cards/{cardId}/reward-way/{rewardWayId}`
- 描述：提供cardId及rewardWayId刪除一筆信用卡中的回饋方式
- 請求參數：
  - (Path) cardId：integer
  - (Path) rewardWayId：integer
- 回應範例：
`Reward Way in Card deleted successfully`

#### RewardWay
`GET /api/v1/reward-ways`
- 描述：取得所有回饋方式
- 回應範例：
  List<RewardWay>

`GET /api/v1/reward-ways/{rewardWayId}`
- 描述：以rewardWayId取得回饋方式
- 請求參數：
  - (Path) rewardWayId：integer
- 回應範例：
  RewardWay

`GET /api/v1/reward-ways/name/{rewardWayName}`
- 描述：以rewardWayName取得回饋方式
- 請求參數：
  - (Path) rewardWayName：string
- 回應範例：
  RewardWay

`GET /api/v1/reward-ways/card/{cardId}`
- 描述：以cardId取得回饋方式列表
- 請求參數：
  - (Path) cardId：integer
- 回應範例：
  List<RewardWay>

`POST /api/v1/reward-ways`
- 描述：有提供rewardWayId時為更新；沒有提供rewardWayId時為新增
- 請求參數：
```json
{
  "rewardWayId": 0,
  "rewardWayName": "string",
  "rewardLimit": 0,
  "rewardRate": 0
}
```
- 回應範例：
  RewardWay

`PUT /api/v1/reward-ways/{rewardWayId}/consumption`
- 描述：提供rewardWayId及consumptionIds以將多個消費通路新增至指定回饋方式
- 請求參數：
  - (Path) rewardWayId：integer
  - (Query) consumptionIds：array[integer]
- 回應範例：
  RewardWay

`DELETE /api/v1/reward-ways/{rewardWayId}`
- 描述：依rewardWayId刪除一筆回饋方式
- 請求參數：
  - (Path) rewardWayId：integer
- 回應範例：
  `Reward Way deleted successfully`

`DELETE /api/v1/reward-ways/{rewardWayId}/consumption/{consumptionId}`
- 描述：提供rewardWayId及consumptionId刪除一筆回饋方式中的消費通路
- 請求參數：
  - (Path) rewardWayId：integer
  - (Path) consumptionId：integer
- 回應範例：
  `Consumption in Reward Way deleted successfully`

#### Consumption
`GET /api/v1/consumptions`
- 描述：取得所有消費通路
- 回應範例：
  List<Consumption>

`GET /api/v1/consumptions/{consumptionId}`
- 描述：以consumptionId取得消費通路
- 請求參數：
  - (Path) consumptionId：integer
- 回應範例：
  Consumption

`POST /api/v1/consumptions`
- 描述：有提供consumptionId時為更新；沒有提供consumptionId時為新增
- 請求參數：
```json
{
  "consumptionId": 0,
  "consumptionName": "string"
}
```
- 回應範例：
  Consumption

`DELETE /api/v1/consumptions/{consumptionId}`
- 描述：依consumptionId刪除一筆消費通路
- 請求參數：
  - (Path) consumptionId：integer
- 回應範例：
  `Consumption deleted successfully`

### 錯誤處理
| 錯誤代碼 | 描述                    |
|----------|-------------------------|
| 404      | 資源未找到              |
| 500      | 內部伺服器錯誤          |

