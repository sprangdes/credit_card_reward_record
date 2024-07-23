# User
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