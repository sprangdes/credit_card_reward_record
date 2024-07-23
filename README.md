# Credit Card Reward Record API
提供有關信用卡資訊的CRUD操作，並可將信用卡和使用者綁定以提供更多的操作，如：提供適合的刷卡建議或可得到最高回饋的刷卡方式。

## 目錄
- [專案簡介](#專案簡介)
- [安裝與設定](#安裝與設定)
- [使用說明](#使用說明)
- [API路徑與方法](#API路徑與方法)
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
- MySQL 8.4.0
- MySQL WorkBench
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
- API：http://localhost:8080/api/v1/
- Swagger：http://localhost:8080/swagger-ui/index.html#/

### API路徑與方法

- [User](docs/user_api.md)
- [Bank](docs/bank_api.md)
- [Card](docs/card_api.md)
- [Reward Way](docs/reward_way_api.md)
- [Consumption](docs/consumption_api.md)

### 資料庫

#### User
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
#### Bank
```json
{
  "bankId": 0,
  "bankName": "string",
  "cards": [
    "string"
  ]
}
```
#### Card
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
#### RewardWay
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
#### Consumption
```json
{
  "consumptionId": 0,
  "consumptionName": "string",
  "rewardWays": [
    "string"
  ]
}
```

### 錯誤處理
| 錯誤代碼 | 描述                    |
|----------|-------------------------|
| 404      | 資源未找到              |
| 500      | 內部伺服器錯誤          |

