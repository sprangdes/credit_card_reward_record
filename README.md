# Credit Card Reward Record API
提供有關信用卡資訊的CRUD操作，並可將信用卡和使用者綁定以提供更多的操作，如：提供適合的刷卡建議或可得到最高回饋的刷卡方式。

## 目錄
- [安裝與設定](#安裝與設定)
- [使用說明](#使用說明)
- [錯誤處理](#錯誤處理)
- [範例](#範例)
- [貢獻指南](#貢獻指南)
- [授權](#授權)
- [聯絡信息](#聯絡信息)

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

## 使用說明
### 啟動服務
```bash  
mvn spring-boot:run
```
http://localhost:8080/api/v1/

### API路徑與方法
#### User
`GET /api/v1/users`
- 描述：取得所有使用者資料
- 回應範例：
 ```json
[
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
]
```

`GET /api/v1/users/{userId}`
- 描述：以userId取得使用者資料
- 請求參數：
  - (Path) userId：integer
- 回應範例：
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

`GET /api/v1/users/{userId}/recommend`
- 描述：提供userId及consumptionId，回傳使用者所擁有的信用卡中，有對應消費方式回饋的信用卡列表
- 請求參數：
  - (Path) userId：integer
  - (Query) consumptionId：integer
- 回應範例：
```json
[
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
```

`GET /api/v1/users/{userId}/recommend-consumption`
- 描述：提供userId及consumptionId，回傳使用者所擁有的信用卡中，有對應消費方式最高回饋的信用卡列表
- 請求參數：
  - (Path) userId：integer
  - (Query) consumptionId：integer
- 回應範例：
```json
[
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
```

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

`DELETE /api/v1/users/{userId}`
- 描述：依userId刪除一筆使用者資料
- 請求參數：
  - (Path) userId：integer
- 回應範例：
`User deleted successfully`

#### Card
`GET /api/v1/cards`
- 描述：取得所有信用卡資料
- 回應範例：
```json
[
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
```

`GET /api/v1/cards/{cardId}`
- 描述：以cardId取得信用卡資料
- 請求參數：
  - (Path) cardId：integer
- 回應範例：
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

`GET /api/v1/cards/name/{cardName}`
- 描述：以cardName取得信用卡資料
- 請求參數：
  - (Path) cardName：string
- 回應範例：
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

`GET /api/v1/cards/bank/{bankId}`
- 描述：以bankId取得信用卡資料列表
- 請求參數：
  - (Path) bankId：integer
- 回應範例：
```json
[
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
```

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

`PUT /api/v1/cards/{cardId}/reward-way`
- 描述：提供cardId及rewardWayIds以將多個回饋方式新增至指定信用卡
- 請求參數：
  - (Path) cardId：integer
  - (Query) rewardWayIds：array[integer]
- 回應範例：
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

