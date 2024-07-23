# Card
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